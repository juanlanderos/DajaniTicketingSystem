import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateCommentComponent } from './create-comment.component';
import { NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';

//daniel added below here
import { Ticket } from 'src/app/Models/ticket';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TicketService } from 'src/app/Services/ticket.service';
import { of } from 'rxjs';
import { User } from 'src/app/Models/user';
import { Comment } from "src/app/Models/comment";
import { Role } from 'src/app/Models/role';
import { AppComponent } from './../../app.component';

fdescribe('CreateCommentComponent', () => {
  let component: CreateCommentComponent;
  let fixture: ComponentFixture<CreateCommentComponent>;
  let ticketService: jasmine.SpyObj<TicketService>;
  let router: Router;

  const today = new Date();
  const date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
  const time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
  const timeNDate = date + ' :: ' + time;


  const mockTickets = [
    {
      agentId: 1213,
      commentList: [
        {
          commentId: 123,
          content: 'test content',
          createdAt: '2022-11-14',
          ticketEntity: {} as Ticket,
          updatedAt: '2022-11-14',
          userEntity: {} as User,
        }
      ] as any [],
      completedAt: '2022-14-11',
      createdAt: '2022-11-11',
      description: 'test description',
      requesterId: 145,
      status: 'issued',
      ticketId: 12365,
      title: 'Creating New Ticket',
      updatedAt: '2022-14-11',
      userList: [
        {
          firstName: 'johnny',
          email: 'johnnya@gmail.com',
          lastName: 'appleseed',
          password: '4568',
          userId: 147,
          username: 'johnnyappleseed',
          commentEntityList: [] as Comment [],
          roles: [] as Role[],
          ticketEntities: [] as Ticket[]
        }
      ] as User[]
    }
  ] as Ticket[];

  beforeEach(async () => {

    ticketService = jasmine.createSpyObj<TicketService>('TicketService', ['getTicketsList']);
    ticketService.getTicketsList.and.returnValue(of(mockTickets));

    router = jasmine.createSpyObj('Router', ['navigate'], {
      url: 'update-tickets/issued',
      routeReuseStrategy: { shouldReuseRoute: false },
    });

    await TestBed.configureTestingModule({
      declarations: [ CreateCommentComponent ],
	  imports: [AngularMaterialModule, HttpClientModule, RouterTestingModule],
    schemas: [
     CUSTOM_ELEMENTS_SCHEMA,
     NO_ERRORS_SCHEMA
    ]
    })
    .compileComponents();

    router = TestBed.inject(Router);
    fixture = TestBed.createComponent(CreateCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('calls saveComment, should save comment', () => {

    component.onSubmit();
    expect(component.comment.createdAt).toEqual(timeNDate);
    expect(component.comment.updatedAt).toEqual(timeNDate);

  });
});