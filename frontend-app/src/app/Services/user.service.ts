import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/Models/user';
import { Ticket } from '../Models/ticket';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:9000/api/users"

  constructor(private httpClient: HttpClient) { }

  //signup
  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`http://localhost:9000/api/users`, user);
  }

  getUsersList(): Observable<User[]>{
    return this.httpClient.get<User[]>(this.baseUrl);
  }

  getUserByEmail(email : string): Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/email/${email}`);
  }

  getUserByUsername(username : string): Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/username/${username}`);
  }

  getUserById(id : number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/${id}`);
  }

  updateUser(id: number, user: User): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, user);
  }

  getTicketsByUserId(id : number): Observable<Ticket[]>{
    return this.httpClient.get<Ticket[]>(`${this.baseUrl}/tickets/${id}`);
  }
}
