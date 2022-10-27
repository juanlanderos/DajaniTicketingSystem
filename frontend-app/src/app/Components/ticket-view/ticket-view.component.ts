import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from 'src/app/Models/comment';
import { Ticket } from 'src/app/Models/ticket';
import { User } from 'src/app/Models/user';
import { TicketService } from 'src/app/Services/ticket.service';
//import { TicketView } from '../../Models/ticket-view';
//import { TicketViewService } from '../../Services/ticket-view.service';
//import { TicketListComponent } from 'src/app/Components/ticket-list/ticket-list.component';

@Component({
  selector: 'app-ticket-view',
  templateUrl: './ticket-view.component.html',
  styleUrls: ['./ticket-view.component.css']
})
export class TicketViewComponent implements OnInit 
{
	ticket!: Ticket;
	user!: User;
	ticketSummary = new FormControl();
	//used for styling and editing fields, buttons, & boxes in FC
	responseFC = new FormControl('', Validators.required); 
	showResponseField = false;
	content = '';
	comments: Comment[] = [];
  
	constructor(private ticketService: TicketService,
				private route: ActivatedRoute) 
	{ }

  ngOnInit(): void {	
    /* 
		1. ActivatedRoute service used to get ticketId from route.
		2. The value returned from params object is of type string.
		3. Used parseInt function to convert it to integer.
	*/
  const ticketId = parseInt(this.route.snapshot.params['id'], 10);
    this.getTicketInfo(ticketId);
    }

  //getTicketInfo method first gets the array of tickets and then finds the ticket with given ticketId.
	private getTicketInfo(ticketId: number): void {
	  this.ticketService.getTicketById(ticketId).subscribe(resp => {
		  this.ticket = resp;
		  // Once ticket info is received, get User associated with a ticket;
		  this.getUserInfo(ticketId);
	  });
	}
	/* Gets the user info associated with that ticket
	   Added code in Ticket-View html to display "no user assigned yet",
	   if there is no user assigned for that ticket
	   Also extracts comments associated with that userId and ticketId
	*/
  	private getUserInfo(ticketId: number): void {
	  this.ticketService.getUsersFromTicket(ticketId).subscribe({
			next: (resp: any) => {
				if (resp.length > 0) {
					this.user = resp[0];
					// Once user info is received, get comments associated with the user;
					this.getUserComments();
				}
			}
	  });
	}
  //extracts comments associated with that ticket for the user
	private getUserComments(): void {
		this.ticketService.getCommentsFromTicket(this.ticket.ticketId).subscribe({
			next: (resp: Comment[]) => {
				this.comments = resp;
			}
		});
	}
  
	onHtmlContentChange(htmlContent: string): void {
	  this.responseFC.setValue(htmlContent);
	}
  
	onSubmit(): void {
	  // Make required comment a payload so that the server can store it.
	  const payload = {
		content: this.responseFC.value,
		userId: this.user.userId,
		ticketId: this.ticket.ticketId
	  };

	  this.ticketService.addCommentToTicket(payload).subscribe({
		next: resp => {
			// Reset the html editor content;
			this.responseFC.reset();
		}
	  });
	}
}

