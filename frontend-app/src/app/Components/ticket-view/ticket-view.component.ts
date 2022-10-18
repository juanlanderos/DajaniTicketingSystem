import { ITicket } from './../../Models/ticket-interface';
import { Ticket } from './../../Models/ticket';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  ticket!: ITicket;
  tickets: TicketView[] = [];

  constructor(private ticketViewService: TicketViewService,
    //private router: Router) { }
    private route: ActivatedRoute) { }

  ///ngOnInit(): void {
  ///  this.getTickets();
  //}

  ngOnInit(): void {	
    /* 
		1. ActivatedRoute service used to get ticketId from route.
		2. The value returned from params object is of type string.
		3. Used parseInt function to convert it to integer.
	*/
  
  const ticketId = parseInt(this.route.snapshot.params['id'], 10);
    this.getTicketInfo(ticketId);
    }
  /*
  getTicketInfo method first gets the array of tickets and then finds the ticket with given ticketId.
  //REMOVE WHEN DB IS OFFICIALLY LINKED. TEMPORARY//
    private getTickets(){
    this.ticketViewService.getTicketsList().subscribe(data => {
      this.tickets = data.data
    });
  }
}
  getTicketInfo method first gets the array of tickets and then finds the ticket with given ticketId.
  */
  private getTicketInfo(ticketId: number): void {
    this.ticketViewService.getTicketsList().subscribe(resp => {
    const allTickets = resp.data;
    this.ticket = allTickets.find((ticket: Ticket) => ticket.ticketId === ticketId);
    console.log(this.ticket)
    });
  }
}

