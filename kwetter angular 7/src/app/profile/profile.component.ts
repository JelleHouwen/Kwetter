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
    name:string;
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
        this.loadLast10FromUser(params.get("username"));
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


    private loadLast10FromUser(username:string) {
    this.kweetService.getLast10User(username).pipe(first()).subscribe(kweets => {
        this.last10kweets = kweets;
    });

    }

    goto(user: string): void {
        this.router.navigate(["username", user]);
      }

    onSelect(kweet: Kweet): void {
        this.selectedKweet = kweet;
      }
      follow(username:string){
         this.userService.follow(this.currentUser.username,username).subscribe();
          this.checkinfollowers();
          this.ngOnInit();
      }
      unfollow(username:string){
        this.userService.unfollow(this.currentUser.username,username).subscribe();
      this.checkinfollowers();
      this.ngOnInit();

    }
checkinfollowers(){
    let found = false;
    for(let key of this.profileUser.followers){        
            if(key.username ==this.currentUser.username){
                found = true;
            }
    }
     return found;
}

}
