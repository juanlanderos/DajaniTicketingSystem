import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './../../app.component';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AuthService } from 'src/app/Services/auth.service';
import { from } from 'rxjs';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/user.service';
import { User } from 'src/app/Models/user';

class RouterStub {
	navigate(params: any) { }
}

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
	let authService: AuthService;
	let userService: UserService;
	let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent, AppComponent ],
			imports: [HttpClientModule],
			providers: [AppComponent, { provide: Router, useClass: RouterStub }],
			schemas: [
				CUSTOM_ELEMENTS_SCHEMA,
				NO_ERRORS_SCHEMA,
			]
		})
    .compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
		authService = TestBed.inject(AuthService);
		userService = TestBed.inject(UserService);
		router = TestBed.inject(Router);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

	it('should create a form with 2 fields', () => {
		expect(component.loginForm.contains('username')).toBeTruthy();
		expect(component.loginForm.contains('password')).toBeTruthy();
	});

	it('should make the username field required', () => {
		const control = component.loginForm.get('username');
		control?.setValue('');
		expect(control?.valid).toBeFalse();
	});

	it('should make the password field required', () => {
		const control = component.loginForm.get('password');
		control?.setValue('');
		expect(control?.valid).toBeFalse();
	});

	it('should set access_token in local storage and navigate user to dashboard after successfully logged In', () => {
		const tokens = { access_token: 'abc' };
		const user: User = { firstName: 'farid', lastName: '', commentEntityList: [], email: '', password: '', roles: [{roleName: 'Admin', roleId: 1}], ticketEntities: [], userId: 1, username: '' };
		const username = 'farid';

		spyOn(authService, 'login').and.callFake(({}) => {
			return from([tokens]);
		});
		spyOn(userService, 'getUserByUsername').and.callFake((username) => {
			return from([user]);
		});
		const routerSpy = spyOn(router, 'navigate');

		component.login();

		expect(component.tokens.access_token).toBe(tokens.access_token);
		expect(component.currentUser).toBe(user);
		expect(routerSpy).toHaveBeenCalledWith(['/dashboard']);
	});
});