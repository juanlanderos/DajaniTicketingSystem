import { AppComponent } from 'src/app/app.component';
import { EMPTY, from } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TicketService } from 'src/app/Services/ticket.service';
import { TicketViewComponent } from './ticket-view.component';
import { NO_ERRORS_SCHEMA,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

fdescribe('TicketViewComponent', () => {
  let component: TicketViewComponent;
  let fixture: ComponentFixture<TicketViewComponent>;
  let service: TicketService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketViewComponent ],
	  imports: [ HttpClientModule, RouterTestingModule ],
      providers: [ AppComponent ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA,
        NO_ERRORS_SCHEMA
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketViewComponent);
    component = fixture.componentInstance;
	service = TestBed.inject(TicketService);
    fixture.detectChanges();
  });

  it('should create Ticket view app', () => {
    expect(component).toBeTruthy();
  });
  
  //This is where the issue is lying.... Somewhere here.
  it('should get Ticket by ticket id from server and set it in ticket variable', () => {
	const service = TestBed.inject(TicketService);
	const ticket: any = { ticketId: 1 };

	const ticketId = 1;
	spyOn(service, 'getTicketById').and.callFake((ticketId) => {
		return from([ ticket ]);
	}); 

	component.getTicketInfo(ticketId);

	expect(component.ticket).toBe(ticket);
  });

  it('should get User info from server base on userId obtain from ticket', () => {
	const users: any[] = [ { userId: 1 } ];
	const userId = 1;

	spyOn(service, 'getUsersFromTicket').and.callFake((userId) => {
		return from([ users ]);
	});

	component.getUserInfo(userId);

	expect(component.user).toBe(users[0]);
  });
  
  it('should get all the comments associated with the ticket from server', () => {
	const comments: any[] = [1, 2, 3];
	const ticketId = 1;

	spyOn(service, 'getCommentsFromTicket').and.callFake((ticketId) => {
		return from([ comments ]);
	});

	component.getUserComments(ticketId);

	expect(component.comments).toBe(comments);
  });
  it('should call the server to save comment', () => {
	const spy = spyOn(service, 'addCommentToTicket').and.callFake(c => {
		return EMPTY;
	});

	component.user = { userId: 1, username: '', commentEntityList: [], email: '', firstName: '', lastName: '', password: '', roles: [], ticketEntities: []};
	component.ticket = { agentId: 1, ticketId: 1, commentList: [], completedAt: '', createdAt: '', description: '', requesterId: 1, status: '', title: '', updatedAt: '', userList: []};
	component.onSubmit();

	expect(spy).toHaveBeenCalled();
  });
});