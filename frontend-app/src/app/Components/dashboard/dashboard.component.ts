import { Component, OnInit } from '@angular/core';
import jwt_decode from "jwt-decode";
import { Ticket } from 'src/app/Models/ticket';
import { User } from 'src/app/Models/user';
import { TicketService } from 'src/app/Services/ticket.service';
import { LocalStorageService } from 'src/app/Services/local-storage.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  	tickets: Ticket[] = [];
	user!: User;

	constructor(private tickService: TicketService,
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

		const token = this.localStorage.get("access_token");

		if (token) {
			let decodeToken: any = jwt_decode(token);

			this.user = {
				userId: 1,
				username: decodeToken.sub,
				email: '',
				commentEntityList: [],
				firstName: '',
				lastName: '',
				roles: decodeToken.roles,
				ticketEntities: []
			}
		}

		// Get all the tickets associated with current logged in user.
		this.getCurrentUserTickets();
	}

	/*
		It wont return anything since the API is not there yet.
		That's why hard coded tickets array is added. It can be removed when the API is done.
	*/
	getCurrentUserTickets(): void {
		this.tickService.getTicketsByUserId(this.user.userId).subscribe({
			next: (resp: any) => {
				this.tickets = resp;
			}
		});

		// Remove this when above API starts working properly
		/*this.tickets = [
			{ 	ticketId: 1, assigneeId: 1, 
				commentList: [], requesterId: 1,
				statusId: 'Open', ticketDept: '',
				title: 'Test Ticket 01', userList: [],
				completedAt: new Date().toLocaleString(), 
				createdAt: new Date().toLocaleString(),
				updatedAt: new Date().toLocaleString()
			},
			{ 	ticketId: 2, assigneeId: 1, 
				commentList: [], requesterId: 1,
				statusId: 'Open', ticketDept: '',
				title: 'Test Ticket 02', userList: [],
				completedAt: new Date().toLocaleString(), 
				createdAt: new Date().toLocaleString(),
				updatedAt: new Date().toLocaleString()
			} 
		] */
	}
}