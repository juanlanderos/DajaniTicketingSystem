import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'; //added
import { HttpClientModule} from '@angular/common/http'; //added
import { HttpClient } from '@angular/common/http';
import { TicketViewComponent } from './Components/ticket-view/ticket-view.component'; //added
import { TicketService } from 'src/app/Services/ticket.service'; //added
import { NgModule } from '@angular/core';

describe('AppComponent', () => {
  let httpClient:HttpClient; //added
  let httpMock: HttpTestingController; //added
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        HttpClientModule, //added
        NgModule,
      ],
      declarations: [
        AppComponent,
        TicketViewComponent, //added
        HttpTestingController, //added
      ],
      providers: [
      TicketService,
    ],
    }).compileComponents();
    httpMock = TestBed.get(HttpClientTestingModule); //added
    httpClient = TestBed.inject(HttpClient); //added
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'frontend-app'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('frontend-app');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('.content span')?.textContent).toContain('frontend-app app is running!');
  });
});
