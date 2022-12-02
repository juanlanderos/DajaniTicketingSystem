import { AppComponent } from './../../app.component';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed, tick } from '@angular/core/testing';
import { from } from 'rxjs';
import { UserService } from 'src/app/Services/user.service';
import { DashboardComponent } from './dashboard.component';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardComponent ],
	  imports: [HttpClientModule],
      providers: [AppComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load user from the server based on username', () => {
	const user: any = { userId: 1 };
	const service = TestBed.inject(UserService);

	spyOn(service, 'getUserByUsername').and.returnValue(from([ user ]));

    fixture.detectChanges();

	expect(component.user).toBe(user);
  });

  it('set tickets by the userId return from the server', () => {
	const tickets: any[] = [1, 2, 3];
	const service = TestBed.inject(UserService);

	spyOn(service, 'getTicketsByUserId').and.returnValue(from([ tickets ]));

	component.user = { userId: 1, username: '', commentEntityList: [], email: '', firstName: '', lastName: '', password: '', roles: [], ticketEntities: []};
    component.getCurrentUserTickets();

	expect(component.tickets).toBe(tickets);
  });
});