import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from '../../Models/ticket';
import { TicketService } from '../../Services/ticket.service';
import { TicketViewComponent } from '../ticket-view/ticket-view.component';

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

  //Matt's code
  //Ticket List for showing all tickets in database - DJB, 10/17/22
  private getTickets(){
    this.ticketService.getTicketsList().subscribe(data => {
      this.tickets = data;
    });
  }
  // Russell Code
  statuses: String[] =['Open', 'Solved','Pending', 'Spam'];
  selectedstatuses: String[] =['Open', 'Solved','Pending', 'Spam'];

  public defaultTrigger(selectedArr: any[], totalArr: any[]) {
    let selected = selectedArr.length;
    let total = totalArr.length;
    if (selected === total) {
      return 'All Selected';
    } else if (selected === 2) {
      return selectedArr.join(' & ');
    } else if (selected === 1) {
      return selectedArr[0];
    } else {
      return selected + ' of ' + total + ' Selected';
    }
  }

  public filterTickets (ticket: Ticket) {
    if (!this.selectedstatuses.includes(ticket.status)) {
      return false;
    }
    return true;
  }
  // Russell Code
  
  // Russell Code update tickets
  updateTickets(statusId: String){
    this.router.navigate(['update-tickets', statusId]);
  }
  // Russell Code update tickets

  assignAgent(ticketID: number){
    //route to a componenet for assigning an agent to a ticket
  }
  
//Matts code
onViewTicketDetails(ticket: Ticket): void {
  this.router.navigate(['ticket-view', ticket.ticketId]);
  }
//Matts code

}
