import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './_services';
import { User } from './_models';

@Component({ selector: 'app', templateUrl: 'app.component.html' })
export class AppComponent {
    currentUser: User;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }

    userHasRole(role: string) {
        let found = false;
        for (let i = 0; i < this.currentUser.roles.length; i++) {
          if (this.currentUser.roles[i].name === role) {
            found = true;
            break;
          }
        }
        return found;
      }
  

  
    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
}