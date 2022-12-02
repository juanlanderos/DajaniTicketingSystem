import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateTicketComponent } from './create-ticket.component';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './../../app.component';


describe('CreateTicketComponent', () => {
  let component: CreateTicketComponent;
  let fixture: ComponentFixture<CreateTicketComponent>;
  let router: Router;

  const today = new Date();
  const date = (today.getMonth()+1)+'/'+today.getDate()+'/'+today.getFullYear();
  const time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
  const fullTimeStamp = date + ' @ ' + time;

  beforeEach(async () => {
    router = jasmine.createSpyObj('Router', ['navigate'], {
      url: 'update-tickets/issued',
      routeReuseStrategy: { shouldReuseRoute: false },
    });

    await TestBed.configureTestingModule({
    declarations: [ CreateTicketComponent],
	  imports: [HttpClientModule, FormsModule, RouterTestingModule],
      providers: [{provide: AppComponent}] 
    })
    .compileComponents();

    router = TestBed.inject(Router);
    fixture = TestBed.createComponent(CreateTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('calls onSubmit check for status and date values', () => {

    component.onSubmit();
    expect(component.ticket.status).toEqual("Open");
    expect(component.ticket.createdAt).toEqual(fullTimeStamp);
    expect(component.ticket.updatedAt).toEqual(fullTimeStamp);
    expect(component.ticket.completedAt).toEqual("---");
  
  });


  it('should call view dashboard', () => {
    spyOn(router, 'navigate').and.resolveTo(true);
    component.goToTicketList();
    expect(router.navigate).toHaveBeenCalledOnceWith(['/dashboard']);
  });




});