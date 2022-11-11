import { Component, OnInit } from '@angular/core';
import jwt_decode from "jwt-decode";
import { Ticket } from 'src/app/Models/ticket';
import { User } from 'src/app/Models/user';
import { TicketService } from 'src/app/Services/ticket.service';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import { UserService } from 'src/app/Services/user.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  	tickets!: Ticket[];
	user!: User;
	username!: string;

	constructor(private ticketService: TicketService,
				private userService: UserService,
				private localStorage: LocalStorageService) 
	{ }

	ngOnInit(): void {
		this.getLoggedInUserInfo();
	}

	getLoggedInUserInfo(): void {
		/* 	
			Since login is not working properly, I first check if there is a token that exists: 
			if there is, pick that user data from there. else, added a dummy user
		*/
		this.username = this.localStorage.get("current_user") as string;
		this.userService.getUserByUsername(this.username).subscribe(data => {
			this.user = data;
			console.log(this.user);
			this.getCurrentUserTickets();
		});
	}

	getCurrentUserTickets(): void {
		this.userService.getTicketsByUserId(this.user.userId).subscribe(data => {
			this.tickets = data;
			console.log(this.tickets);
		});
	}
}