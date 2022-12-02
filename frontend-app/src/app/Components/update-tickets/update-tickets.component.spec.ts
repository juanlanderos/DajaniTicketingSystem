import { AppComponent } from 'src/app/app.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UpdateTicketsComponent } from './update-tickets.component';

describe('UpdateTicketsComponent', () => {
  let component: UpdateTicketsComponent;
  let fixture: ComponentFixture<UpdateTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateTicketsComponent, AppComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
