import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { from } from 'rxjs';
import { AuthService } from 'src/app/Services/auth.service';
import { SignUpComponent } from './sign-up.component';

class RouterStub {
	navigate(params: any) { }
}

fdescribe('SignUpComponent', () => {
  let component: SignUpComponent;
  let fixture: ComponentFixture<SignUpComponent>;
  let authService: AuthService;
	let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignUpComponent ],
			imports: [ HttpClientTestingModule, ReactiveFormsModule],
			providers: [{ provide: Router, useClass: RouterStub }],
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
		authService = TestBed.inject(AuthService);
		router = TestBed.inject(Router);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create a form with 5 fields', () => {
		expect(component.userForm.contains('userName')).toBeTruthy();
		expect(component.userForm.contains('email')).toBeTruthy();
		expect(component.userForm.contains('firstName')).toBeTruthy();
		expect(component.userForm.contains('lastName')).toBeTruthy();
		expect(component.userForm.contains('password')).toBeTruthy();
	});

	it('should make the username field required', () => {
		const control = component.userForm.get('userName');
		control?.setValue('');
		expect(control?.valid).toBeFalse();
	});

	it('should make the email field required', () => {
		const control = component.userForm.get('email');
		control?.setValue('');
		expect(control?.valid).toBeFalse();
	});

	it('should make the firstName field required', () => {
		const control = component.userForm.get('firstName');
		control?.setValue('');
		expect(control?.valid).toBeFalse();
	});

	it('should make the lastName field required', () => {
		const control = component.userForm.get('lastName');
		control?.setValue('');
		expect(control?.valid).toBeFalse();
	});

	it('should make the password field required', () => {
		const control = component.userForm.get('password');
		control?.setValue('');
		expect(control?.valid).toBeFalse();
	});

	it('should navigate user to login after successfully registering account.', () => {
		spyOn(authService, 'signUp').and.callFake(({}) => {
			return from([{}]);
		});

		const routerSpy = spyOn(router, 'navigate');

		component.signUp();

		expect(routerSpy).toHaveBeenCalledWith(['/login']);
	});
});
