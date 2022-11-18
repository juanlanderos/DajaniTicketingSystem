import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Comment } from 'src/app/Models/comment';
import { Ticket } from 'src/app/Models/ticket';
import { User } from 'src/app/Models/user';
import { TicketService } from 'src/app/Services/ticket.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-ticket-view',
  templateUrl: './ticket-view.component.html',
  styleUrls: ['./ticket-view.component.css']
})
export class TicketViewComponent implements OnInit 
{
	ticket!: Ticket;
	ticketId!: number;
	user!: User;
	selectedAgent!: User;
	agent!: User;
	users: User[] = [];
	agents: User[] = [];
	ticketSummary = new FormControl();
	agentAssigned: boolean = false;

	isAdmin!: boolean;
	isAgent!: boolean;
	isUser!: boolean;
	//used for styling and editing fields, buttons, & boxes in FC
	responseFC = new FormControl('', Validators.required); 
	showResponseField = false;
	content = '';
	comments: Comment[] = [];
  
	constructor(private ticketService: TicketService,
				private userService: UserService,
				private appComponent: AppComponent,
				private route: ActivatedRoute) 
	{ }

  ngOnInit(): void {
	this.isAdmin = this.appComponent.isAdmin;
	this.isAgent = this.appComponent.isAgent;
	this.isUser = this.appComponent.isUser;	
    /* 
		1. ActivatedRoute service used to get ticketId from route.
		2. The value returned from params object is of type string.
		3. Used parseInt function to convert it to integer.
	*/
  	this.ticketId = parseInt(this.route.snapshot.params['id'], 10);
	if(this.agent == null){
		this.getAgents();
	}
    this.getTicketInfo(this.ticketId);
    }

  //getTicketInfo method first gets the array of tickets and then finds the ticket with given ticketId.
	public getTicketInfo(ticketId: number): void { //!!!!!!!!!CHANGE BACK TO PRIVATE ONCE TESTING IS DONE!!!!!!!!!
	  this.ticketService.getTicketById(ticketId).subscribe(resp => {
		  this.ticket = resp;
		  // Once ticket info is received, get User associated with a ticket;
		  this.getUserInfo(ticketId);
		  //if the agent has been assigned, get the info from the backend
		  this.getAgentInfo();

	  });
	}
	/* Gets the user info associated with that ticket
	   Added code in Ticket-View html to display "no user assigned yet",
	   if there is no user assigned for that ticket
	   Also extracts comments associated with that userId and ticketId
	*/
  	public getUserInfo(ticketId: number): void { //!!!!!!!!!CHANGE BACK TO PRIVATE ONCE TESTING IS DONE!!!!!!!!!
	  this.ticketService.getUsersFromTicket(ticketId).subscribe({
			next: (resp: any) => {
				if (resp.length > 0) {
					this.user = resp[0];
					// Once user info is received, get comments associated with the user;
					this.getUserComments(ticketId);
				}
			}
	  });
	} 

	//get the list of Agents and Admins
	private getAgents(){
		this.userService.getUsersList().subscribe(data => {
			this.users = data;
			for(let i = 0; i < this.users.length; i++){
				//if the current user is an AGENT or ADMIN, add them to the list of agents
				if(this.users[i].roles[0].roleName == "AGENT" || this.users[i].roles[0].roleName == "ADMIN"){
					this.agents.push(this.users[i]);
				}
			}
			console.log(this.agents);
		});
	}

	private getAgentInfo(): void{
		this.userService.getUserById(this.ticket.agentId).subscribe(data => {
			console.log(data);
			this.agent = data;
		})
	}

	//add an agent to a ticket
	addAgentToTicket(): void{
		this.ticketService.addUserToTicket(this.selectedAgent.userId, this.ticketId).subscribe(data => {
			console.log("Agent added sucessfully");
			this.agentAssigned = true;
			this.getTicketInfo(this.ticketId);
		})
	}
  //extracts comments associated with that ticket for the user
	public getUserComments(ticketId: number): void { //!!!!!!!!!CHANGE BACK TO PRIVATE ONCE TESTING IS DONE!!!!!!!!!
		this.ticketService.getCommentsFromTicket(ticketId).subscribe(data => {
			console.log(data);
			this.comments = data;
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
			this.getUserComments(this.ticket.ticketId);
		}
	  });
	}

	onUpdate(): void {
		this.ticketService.updateTicketStatus(this.ticket.ticketId, this.ticket.status).subscribe(data => {
			console.log(data);
		});
	}
}