

import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import {Router} from "@angular/router";
import { User } from '@app/_models';
import { Kweet } from '@app/_models/kweet';
import { UserService, AuthenticationService } from '@app/_services';
import { KweetService } from '@app/_services/kweet.service';

@Component({ templateUrl: 'home.component.html',   styleUrls: ['./kweets.component.css'] })
export class HomeComponent implements OnInit, OnDestroy {
    router: Router
    currentUser: User;
    following: User[]=[];
    followers: User[]=[];
    currentUserSubscription: Subscription;
    kweets: Kweet[]=[];
    kweetsUser:Kweet[]=[];
    selectedKweet: Kweet;
    constructor(
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private kweetService: KweetService
    ) {
        
        this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
            this.currentUser = user;
        });
    }

    ngOnInit() {
    //    this.loadAllUsers();
        this.loadLast10Kweets();
        this.loadFollowing(this.currentUser.userName);
        this.loadFollowers(this.currentUser.userName);
        this.loadKweetsCurrentUser();
    }

    ngOnDestroy() {
        // unsubscribe to ensure no memory leaks
        this.currentUserSubscription.unsubscribe();
    }

   
 
    private loadLast10Kweets() {
        this.kweetService.getLast10User(this.currentUser.userName).pipe(first()).subscribe(kweets => {
            this.kweets = kweets;
        });
    }
    private loadKweetsCurrentUser() {
        this.kweetService.getByUsername(this.currentUser.userName).pipe(first()).subscribe(kweetsUser => {
            this.kweetsUser = kweetsUser;
        });
    }
    private loadFollowing(username:string) {
        this.userService.getFollowing(username).pipe(first()).subscribe(following => {
            this.following = following;
        });
    }
    private loadFollowers(username:string) {
        this.userService.getFollowers(username).pipe(first()).subscribe(followers => {
            this.followers = followers;
        });
    }

    redirectToProfile(name: string): void {
        this.router.navigateByUrl('/'+name);
      }
  onSelect(kweet: Kweet): void {
    this.selectedKweet = kweet;
  }
  deleteKweet(id:number){
     this.kweetService.deleteKweet(id).subscribe();
  }
}


