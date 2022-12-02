import { AppComponent } from './../../app.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { TicketService } from 'src/app/Services/ticket.service';
import { TicketListComponent } from './ticket-list.component';
import { Router } from '@angular/router';
import { Comment } from "src/app/Models/comment";
import { Ticket } from 'src/app/Models/ticket';
import { User } from 'src/app/Models/user';
import { Role } from 'src/app/Models/role';
import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';

describe('TicketListComponent', () => {
  let component: TicketListComponent;
  let fixture: ComponentFixture<TicketListComponent>;
  let ticketService: jasmine.SpyObj<TicketService>;
  let router: Router;

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
      declarations: [TicketListComponent],
      imports: [AngularMaterialModule, HttpClientModule, RouterTestingModule ],
        providers: [{ provide: TicketService, AppComponent, useValue: ticketService },
        ]
    })
      .compileComponents();

    router = TestBed.inject(Router);
    fixture = TestBed.createComponent(TicketListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {

    expect(component).toBeTruthy();
    expect(component.tickets.length).toBe(1);

  });

  it('should call default trigger if selected and total array length is the same', () => {
    const selectedArray: String[] = ['Open', 'Solved', 'Pending', 'Spam'];
    const totalArray: String[] = ['123', '654', '258', '789'];
    const result = component.defaultTrigger(selectedArray, totalArray);

    expect(result).toEqual('All Selected');
  });

  it('should call default trigger if selected and total array length is not the same', () => {
    const selectedArray: String[] = ['Open', 'Solved'];
    const totalArray: String[] = ['123'];
    const result = component.defaultTrigger(selectedArray, totalArray);

    expect(result).toEqual('Open & Solved');
  });

  it('should call default trigger if total array is empty', () => {
    const selectedArray: String[] = ['Open'];
    const totalArray: String[] = [];
    const result = component.defaultTrigger(selectedArray, totalArray);

    expect(result).toEqual('Open');
  });

  it('should call default trigger if selected array is empty', () => {
    const selectedArray: String[] = [];
    const totalArray: String[] = ['123', '258'];
    const result = component.defaultTrigger(selectedArray, totalArray);

    expect(result).toEqual('0 of 2 Selected');
  });

  it('should call filter tickets and return true', () => {
    const selectedstatuses: String[] = ['Open', 'Solved', 'Pending', 'Spam'];
    const ticket = { ticketId: 123, status: 'Pending' } as Ticket;
    component.selectedstatuses = selectedstatuses;
    const result = component.filterTickets(ticket);

    expect(result).toBeTrue();
  });

  it('should call filter tickets and return false', () => {
    const selectedstatuses: String[] = ['Open', 'Solved', 'Pending', 'Spam'];
    const ticket = { ticketId: 123, status: 'Issued' } as Ticket;
    component.selectedstatuses = selectedstatuses;
    const result = component.filterTickets(ticket);

    expect(result).toBeFalse();
  });
/*
  it('should call update tickets', () => {
    spyOn(router, 'navigate').and.resolveTo(true);
    component.updateTickets('Pending');

    expect(router.navigate).toHaveBeenCalledOnceWith(['update-tickets', 'Pending']);
  });
*/
  it('should call view ticket details', () => {
    const ticket = { ticketId: 123 } as Ticket;
    spyOn(router, 'navigate').and.resolveTo(true);
    component.onViewTicketDetails(ticket);

    expect(router.navigate).toHaveBeenCalledOnceWith(['ticket-view', 123]);
  });
});
