import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '@environments/environment';
import { User } from '@app/_models';
import { sha256 } from 'js-sha256';
@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }
    public setCurrentUserValue(user: User) {
        this.currentUserSubject.next(user);
        this.currentUser = this.currentUserSubject.asObservable();
     }

     login(username: string, password: string) {
        const hash = sha256.create();
        hash.update(password);
        return this.http.post<any>(`${environment.apiUrl}/auth/login/`,{  username, password : hash.hex()}, {observe: 'response'})
                 .pipe(map(user => {
            if (user) {
              // login successful if there's a jwt token in the response
              let token = user.headers.get('AUTHORIZATION');
              token = token.substring('Bearer '.length).trim();
              console.log(token);
              // store user details and jwt token in local storage to keep user logged in between page refreshes
              localStorage.setItem('currentUser', JSON.stringify(user.body));
              localStorage.setItem('authorizationToken', JSON.stringify(token));
              this.currentUserSubject.next(user.body);
  
              return user;
            }
          }));
  
      }
  

    logout() {
        // remove user from local storage to log user out
        localStorage.clear();
        this.currentUserSubject.next(null);
    }
}