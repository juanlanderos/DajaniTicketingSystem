import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AssignTicketAgentComponent } from './assign-ticket-agent.component';
import { HttpClientModule } from '@angular/common/http';

describe('AssignTicketAgentComponent', () => {
  let component: AssignTicketAgentComponent;
  let fixture: ComponentFixture<AssignTicketAgentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignTicketAgentComponent ],
      imports: [HttpClientModule
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignTicketAgentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
