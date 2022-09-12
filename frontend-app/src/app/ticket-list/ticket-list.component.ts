import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from '../ticket';
import { TicketService } from '../ticket.service';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  tickets: Ticket[] = [];

  constructor(private ticketService: TicketService,
    private router: Router) { }

  ngOnInit(): void {
    this.getTickets();
  }

  private getTickets(){
    this.ticketService.getTicketsList().subscribe(data => {
      this.tickets = data;
    })
  }

}
