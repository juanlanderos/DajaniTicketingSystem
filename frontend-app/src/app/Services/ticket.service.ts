import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from '../Models/ticket';
import { User } from '../Models/user';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private baseUrl = "http://localhost:9000/api/tickets"

  constructor(private httpClient: HttpClient) { }

  //get all tickets
  getTicketsList(): Observable<Ticket[]>{
    return this.httpClient.get<Ticket[]>(this.baseUrl);
  }

  //get a specific ticket by passing in a ticket Id
  getTicketById(id : number): Observable<Ticket>{
    return this.httpClient.get<Ticket>(`${this.baseUrl}/${id}`);
  }
  
  //get all of the users attached to a ticket. Pass in ticket ID
  getUsersFromTicket(id : number): Observable<User[]>{
    return this.httpClient.get<User[]>(`http://localhost:9000/api/ticketUsers/${id}`);
  }

  //get all of the comments attached to a ticket. Pass in ticket ID
  getCommentsFromTicket(id : number): Observable<User[]>{
    return this.httpClient.get<User[]>(`http://localhost:9000/api/ticket-comments/${id}`);
  }

  /*
    create a new ticket. Tickets do not exist without a User, so a user must
    exist before creating a ticket. Pass in a userID that corresponds to ticket.
    Also takes a Ticket entity
  */
  creatTicket(id: number, ticket: Ticket): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/${id}`, ticket);
  }

  /*
    Add a user to existing ticket. Use case: lets say an agent gets assigned
    to work on a certain ticket.
  */
  addUserToTicket(userId: number, ticketId: number): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/${userId}/${ticketId}`, null);
  }

  /*
    delete a ticket by passing in its ID. Backend handles decoupling 
    associated users and comments. Also, comments cannot exist without
    a Ticket, so all comments made under this ticket will be deleted as well.
  */
  deleteTicketById(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
