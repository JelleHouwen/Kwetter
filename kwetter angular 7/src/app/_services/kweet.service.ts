import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { HttpHeaders } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Kweet } from '@app/_models/kweet';

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };
@Injectable({ providedIn: 'root' })
export class KweetService {
  
    constructor(private http: HttpClient) {
        
     }

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

    postKweet(username: string,text:string) {
        return this.http.post(`${environment.apiUrl}/kweets/add/${username}`,`&${text}`);
    }
       deleteKweet (id: number) {
        return this.http.delete(`${environment.apiUrl}/kweets/remove/${id}`);
      }

}