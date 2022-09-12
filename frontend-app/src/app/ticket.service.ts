import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from './ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private baseUrl = "http://localhost:9000/api/tickets"

  constructor(private httpClient: HttpClient) { }

  getTicketsList(): Observable<Ticket[]>{
    return this.httpClient.get<Ticket[]>(this.baseUrl);
  }

  createTicket(ticket: Ticket): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`, ticket);
  }
  
}
