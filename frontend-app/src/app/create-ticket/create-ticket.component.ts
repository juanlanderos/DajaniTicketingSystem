import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from '../ticket';
import { TicketService } from '../ticket.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {

  ticket: Ticket = new Ticket();
  constructor(private ticketService: TicketService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveTicket(){
    this.ticketService.createTicket(this.ticket).subscribe( data => {
      console.log(data);
      this.goToTicketList();
    }, error => console.log(error));
  }

  goToTicketList(){
    this.router.navigate(['/tickets']);
  }

  onSubmit(){
    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
    this.ticket.createdAt = date + ' :: ' + time;
    this.ticket.updatedAt = date + ' :: ' + time;
    this.ticket.completedAt = '---';
    //
    console.log(this.ticket);
    this.saveTicket();
  }

}
