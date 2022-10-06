import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketViewService {

  private baseUrl = "http://localhost:9000/api/ticket-view"

  constructor(private httpClient: HttpClient) { }

    //Used as 'dummy' db file. REMOVE/REPLACE WHEN DB IS CONNECTED AND RUNNING!
    getTicketsList(): Observable<any>{
      return this.httpClient.get('src/assets/ticket_data.json');
    }
}
