import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TicketView } from '../../Models/ticket-view';
import { TicketViewService } from '../../Services/ticket-view.service';
import { TicketListComponent } from 'src/app/Components/ticket-list/ticket-list.component';
import { TicketService } from '../../Services/ticket.service';

@Component({
  selector: 'app-ticket-view',
  templateUrl: './ticket-view.component.html',
  styleUrls: ['./ticket-view.component.css']
})
export class TicketViewComponent implements OnInit {

  tickets: TicketView[] = [];

  constructor(private ticketViewService: TicketViewService,
    private router: Router) { }

  ngOnInit(): void {
    this.getTickets();
  }

  //REMOVE WHEN DB IS OFFICIALLY LINKED. TEMPORARY
  private getTickets(){
    this.ticketViewService.getTicketsList().subscribe(data => {
      this.tickets = data.data
    });
  }

}
