import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from '@app/_models';
import { ActivatedRoute,Router } from "@angular/router";
import { Kweet } from '@app/_models/kweet';
import { UserService, AuthenticationService } from '@app/_services';
import { KweetService } from '@app/_services/kweet.service';
import { first } from 'rxjs/operators';
@Component({ templateUrl: 'profile.component.html', selector: 'table-head-options' ,styleUrls: ['./profile.component.css'] })
export class ProfileComponent implements OnInit, OnDestroy {
    currentUser: User;
    currentUserSubscription: Subscription;
    profileUser: User;
    last10kweets: Kweet[]=[];
    allKweets:Kweet[]=[];
    name:string;
    following: User[]=[];
    followers: User[]=[];
    selectedKweet: Kweet;
    constructor(
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router,
        private kweetService: KweetService
    ) {
        this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
            this.currentUser = user;
        });
    }

    ngOnInit() {
       // Subscribed
      this.route.paramMap.subscribe(params => {
        this.name = params.get("username");
        this.loadUser(params.get("username"));
        this.loadAllKweetsFromUser(params.get("username"));
        this.loadKweetsCurrentUser(params.get("username"));
        this.loadFollowers(params.get("username"));
        this.loadFollowing(params.get("username"));
      });
  
    }

    ngOnDestroy() {
        // unsubscribe to ensure no memory leaks
        this.currentUserSubscription.unsubscribe();
    }



    private loadUser(username: string) {
    this.userService.getByUserName(username).pipe(first()).subscribe(user => {
        this.profileUser = user;
    });;
    }


    private loadAllKweetsFromUser(username:string) {
    this.kweetService.getLast10User(username).pipe(first()).subscribe(kweets => {
        this.last10kweets = kweets;
    });

    }

    goto(user: string): void {
        this.router.navigate(["username", user]);
      }

      private loadKweetsCurrentUser(username:string) {
        this.kweetService.getByUsername(username).pipe(first()).subscribe(kweetsUser => {
            this.allKweets = kweetsUser;
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
    onSelect(kweet: Kweet): void {
        this.selectedKweet = kweet;
      }
}
