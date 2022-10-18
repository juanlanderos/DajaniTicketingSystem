import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from '../../Models/ticket';
import { TicketService } from '../../Services/ticket.service';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  tickets: Ticket[] = [];
  // Russell Code
  statuses: string[] =['Open', 'Solved','Pending', 'Spam'];
  selectedstatuses: string[] =['Open', 'Solved','Pending', 'Spam'];
  // Russell Code

  constructor(private ticketService: TicketService,
    private router: Router) { }

  ngOnInit(): void {
    this.getTickets();
  }

  //Matt's code
  /* //Uncomment once the db has been implemented. 
  private getTickets(){
    this.ticketService.getTicketsList().subscribe(data => {
      this.tickets = data;
    })
  }
  */ //REMOVE WHEN DB IS OFFICIALLY LINKED. TEMPORARY
  private getTickets(){
    this.ticketService.getTicketsList().subscribe(data => {
      this.tickets = data;
    });
  }

  // Russell Code
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
  // Russell Code
  
//Matts code
onViewTicketDetails(ticket: Ticket): void {
  this.router.navigate(['ticket-view', ticket.ticketId]);
  }
//Matts code

}
