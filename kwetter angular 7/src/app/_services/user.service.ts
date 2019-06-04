import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/users/all`);
    }
    getFollowing(username:string) {
        return this.http.get<User[]>(`${environment.apiUrl}/users/following/${username}`);
    }
    getFollowers(username:string) {
        return this.http.get<User[]>(`${environment.apiUrl}/users/followers/${username}`);
    }

    getByUserName(username: string) {
        return this.http.get<User>(`${environment.apiUrl}/users/${username}`);
    }

    register(user: User) {
        return this.http.post(`${environment.apiUrl}/users/register`, user);
    }

    update(user: User) {
        return this.http.put(`${environment.apiUrl}/users/${user.id}`, user);
    }

    delete(id: number) {
        return this.http.delete(`${environment.apiUrl}/users/${id}`);
    }
    follow(yourusername: string,otherusername) {
        console.log(yourusername+" is following user: "+otherusername)
        return this.http.put(`${environment.apiUrl}/users/follow/${yourusername}/${otherusername}`,null);
    }
    unfollow(yourusername: string,otherusername) {
        console.log(yourusername+" is no longer following user: "+otherusername)
        return this.http.put(`${environment.apiUrl}/users/unfollow/${yourusername}/${otherusername}`,null);
    }

}