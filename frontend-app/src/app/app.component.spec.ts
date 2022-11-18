import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { HttpClientModule} from '@angular/common/http'; //added
import { TicketViewComponent } from './Components/ticket-view/ticket-view.component'; //added
import { TicketService } from 'src/app/Services/ticket.service'; //added

fdescribe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientModule, //added
      ],
      declarations: [
        AppComponent,
        TicketViewComponent, //added
      ],
      providers: [
      TicketService,
    ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'Dajani Ticketing System'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Dajani Ticketing System');
  });
});
