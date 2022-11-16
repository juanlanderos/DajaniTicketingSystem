import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import { UserService } from 'src/app/Services/user.service';
import { Ticket } from '../../Models/ticket';
import { TicketService } from '../../Services/ticket.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {

  ticket: Ticket = new Ticket();
  user: User = new User();
  username!: string;

  constructor(private ticketService: TicketService,
    private userService: UserService,
    private localStorage: LocalStorageService,
    private router: Router) { }

  ngOnInit(): void {
    this.username = this.localStorage.get("current_user") as string;
    this.userService.getUserByUsername(this.username).subscribe(data => {
      this.user = data;
      console.log(this.user.userId);
    })

  }

  saveTicket(){
    this.ticketService.createTicket(this.user.userId,this.ticket).subscribe( data => {
      console.log(data);
      this.goToTicketList();
    }, error => console.log(error));
  }

  goToTicketList(){
    this.router.navigate(['/dashboard']);
  }

  goToIndividualTicket(){
    this.router.navigate(['/tickets, ticket.ticketId']);
  }

  onSubmit(){
    var today = new Date();
    var date = (today.getMonth()+1)+'/'+today.getDate()+'/'+today.getFullYear();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
    this.ticket.createdAt = date + ' @ ' + time;
    this.ticket.updatedAt = date + ' @ ' + time;
    this.ticket.completedAt = '---';
    this.ticket.status = "Open";

    //
    console.log(this.ticket);
    this.saveTicket();
  }

}
