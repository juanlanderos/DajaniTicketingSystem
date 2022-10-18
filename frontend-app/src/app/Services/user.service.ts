import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/Models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:9000/api/users"

  constructor(private httpClient: HttpClient) { }

  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`, user);
  }

  getUsersList(): Observable<User[]>{
    return this.httpClient.get<User[]>(this.baseUrl);
  }

  getUserByEmail(email : String): Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/${email}`);
  }

  getUserById(id : number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/${id}`);
  }

  updateUser(id: number, user: User): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, user);
  }
}
