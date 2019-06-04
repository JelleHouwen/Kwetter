import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Kweet } from '@app/_models/kweet';
import { User } from '@app/_models';
import { Observable } from 'rxjs';


@Injectable({ providedIn: 'root' })
export class KweetService {
  
    constructor(private http: HttpClient) {}

    getLast20() {
        return this.http.get<Kweet[]>(`${environment.apiUrl}/kweets/last`);
    }
    getLast10User(username:string) {
        return this.http.get<Kweet[]>(`${environment.apiUrl}/kweets/user/${username}`);
    }

    getById(id: number) {
        return this.http.get<Kweet>(`${environment.apiUrl}/kweets/${id}`);
    }
    getByUsername(username: string) {
        return this.http.get<Kweet[]>(`${environment.apiUrl}/kweets/getAll/${username}`);
    }
    getAll() {
        return this.http.get<Kweet[]>(`${environment.apiUrl}/kweets/all`);
    }
    search(search: string):Observable<Kweet[]> {
        return this.http.get<Kweet[]>(`${environment.apiUrl}/kweets/search/${search}`);
    }
    getByFollowing(user: User) {
        return this.http.get<Kweet[]>(`${environment.apiUrl}/kweets/getAllFollowing/${user.id}`);
    }
    postKweet(kweet:Kweet) {
        return this.http.post<Kweet>(`${environment.apiUrl}/kweets/add`,kweet);
    }
     deleteKweet (id: number) {
        return this.http.delete(`${environment.apiUrl}/kweets/remove/${id}`);
      }

}