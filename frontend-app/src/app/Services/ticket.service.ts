import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from '../Models/ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private baseUrl = "http://localhost:9000/api/tickets"

  constructor(private httpClient: HttpClient) { }

  /* //Commented until db is up and running.
  getTicketsList(): Observable<Ticket[]>{
    return this.httpClient.get<Ticket[]>(this.baseUrl);
  }
  */
  //Used as 'dummy' db file. REMOVE/REPLACE WHEN DB IS CONNECTED AND RUNNING!
  getTicketsList(): Observable<any>{
    return this.httpClient.get('/assets/ticket_data.json');
  }

  createTicket(ticket: Ticket): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`, ticket);
  }
  
}
