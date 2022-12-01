import { AppComponent } from './../../app.component';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Services/auth.service';
import { from } from 'rxjs';
import { ResetPasswordComponent } from './reset-password.component';
import { By } from '@angular/platform-browser';

class RouterStub {
	navigate(params: any) { }
}

fdescribe('ResetPasswordComponent', () => {
  let component: ResetPasswordComponent;
  let fixture: ComponentFixture<ResetPasswordComponent>;
	let authService: AuthService;
	let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResetPasswordComponent ],
			imports: [HttpClientModule, FormsModule],
			providers: [AppComponent, { provide: Router, useClass: RouterStub }]
		})
    .compileComponents();

    fixture = TestBed.createComponent(ResetPasswordComponent);
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

		spyOn(authService, 'processResetPassword').and.callFake((email) => {
			return from([{}]);
		});

		const routerSpy = spyOn(router, 'navigate');

		component.resetPassword();

		expect(routerSpy).toHaveBeenCalledWith(['/login']);
	});
});
