

import { Component, Output ,Input,EventEmitter,OnInit, OnDestroy , ViewChild, ElementRef } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import {Router} from "@angular/router";
import { User } from '@app/_models';
import { Kweet } from '@app/_models/kweet';
import { Topic } from '@app/_models/topic';
import { UserService, AuthenticationService } from '@app/_services';
import { KweetService } from '@app/_services/kweet.service';
import { webSocket } from "rxjs/webSocket";
import { TopicService } from '@app/_services/topic.service';

@Component({ templateUrl: 'home.component.html',   styleUrls: ['./kweets.component.css'] })
export class HomeComponent implements OnInit, OnDestroy {
    @ViewChild('nameInput') nameInput: ElementRef;
    router: Router
    regModel: string;
    post:string;
    postingKweet:Kweet;
    currentUser: User;
    currentUserSubscription: Subscription;
    kweetsUser:Kweet[]=[];
    selectedKweet: Kweet;
    kweet: string;
    user:User;
    insertkweet:Kweet;
    timeLeft: number = 60;
    interval;
    trendingTopics:Topic[];

    searchkweets:Kweet[];
    constructor(
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private topicService: TopicService,
        private kweetService: KweetService,
    ) {
        
        this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
            this.currentUser = user;
        });
    }

    ngOnInit() {
        this.connect();
        this.loadAllKweets();
        this.loadKweetsCurrentUser();
        this.loadUser(this.currentUser.username);
        this.loadTrendingTopics();
    }

    loadUser(username: string) {
        this.userService.getByUserName(username).pipe(first()).subscribe(user => {
            this.currentUser = user;
        });;
        }
    ngOnDestroy() {
        // unsubscribe to ensure no memory leaks
        this.currentUserSubscription.unsubscribe();
    }

   loadAllKweets(){
    this.kweetService.getAll().pipe(first()).subscribe(kweets => {
        this.searchkweets = kweets;
    });
   }
     loadKweetsCurrentUser() {
        this.kweetService.getByFollowing(this.currentUser).pipe(first()).subscribe(kweets => {
            this.kweetsUser = kweets;
        });
    }
    loadTrendingTopics() {
      this.topicService.getTrendingTopics().pipe(first()).subscribe(topics => {
        console.log(topics);
          this.trendingTopics = topics;
      });
  }
    redirectToProfile(name: string): void {
        this.router.navigateByUrl('/'+name);
      }

      
  onSelect(kweet: Kweet): void {
    this.selectedKweet = kweet;
    console.log(this.selectedKweet.ID);
  }
  deleteKweet(id:number){
     this.kweetService.deleteKweet(id).pipe(first()).subscribe(response => {
        console.log(response);
        this.loadKweetsCurrentUser();
     });
    
  }
  connect() {
    const subject = webSocket({
        url: "ws://localhost:8080/Kwetter_versie_5001_war_exploded/wskweet",
        deserializer: msg => msg.data
    });

    subject.subscribe(
        msg => this.loadAllKweets(),
        err => console.log(err), 
        () => console.log('complete') 
      ); }

  onPost(){

  this.postingKweet = new Kweet();
  var topics:Topic[]=[];
   let splitted = this.post.split(' ');

   splitted.forEach(word =>{
    let t=new Topic();
     if(word.startsWith('@')){
      t.title=word.substring(1);
      topics.push(t);
      this.topicService.addTopic(t).subscribe();
     }})


  this.postingKweet.topics=topics;
  this.postingKweet.placer=this.currentUser;
  this.postingKweet.text=this.post;
  console.log(this.postingKweet);
    this.kweetService.postKweet(this.postingKweet).subscribe();
     
     this.loadKweetsCurrentUser();
   } 
 
  search():Kweet[]{
    console.log(this.regModel);
    this.kweetService.search(this.regModel).pipe(first()).subscribe(kweets => {
        this.kweetsUser = kweets;
    });
    return this.kweetsUser;
  }
  

}


