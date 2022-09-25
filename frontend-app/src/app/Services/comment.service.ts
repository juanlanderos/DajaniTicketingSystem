import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../Models/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = "http://localhost:9000/api/comments"

  constructor(private httpClient: HttpClient) { }

  getCommentsList(): Observable<Comment[]>{
    return this.httpClient.get<Comment[]>(this.baseUrl);
  }

  createComment(comment: Comment): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`, comment);
  }

}
