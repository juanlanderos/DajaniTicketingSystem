import { AppComponent } from './../../app.component';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ForgotPasswordComponent } from './forgot-password.component';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Services/auth.service';
import { from } from 'rxjs';

class RouterStub {
	navigate(params: any) { }
}

fdescribe('ForgotPasswordComponent', () => {
  let component: ForgotPasswordComponent;
  let fixture: ComponentFixture<ForgotPasswordComponent>;
	let authService: AuthService;
	let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForgotPasswordComponent ],
			imports: [HttpClientModule, FormsModule],
			providers: [AppComponent, { provide: Router, useClass: RouterStub }]
		})
    .compileComponents();

    fixture = TestBed.createComponent(ForgotPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
		authService = TestBed.inject(AuthService);
		router = TestBed.inject(Router);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate user to login after changing password.', () => {
		const email = 'abc@gmail.com';

		spyOn(authService, 'processForgotPassword').and.callFake((email) => {
			return from([{}]);
		});

		const routerSpy = spyOn(router, 'navigate');

		component.forgotPassword();

		expect(routerSpy).toHaveBeenCalledWith(['/login']);
	});
});
