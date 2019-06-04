import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Topic } from '@app/_models/topic';


@Injectable({ providedIn: 'root' })
export class TopicService {
    constructor(private http: HttpClient) { }
    getTrendingTopics() {
        return this.http.get<Topic[]>(`${environment.apiUrl}/topics/getTrendingTopics`);
    }
  
    addTopic(topic:Topic) {
        return this.http.post<Topic>(`${environment.apiUrl}/topics/addTopic`,topic);
    }
}
  