import { AppComponent } from './../../app.component';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent, AppComponent ],
	  imports: [HttpClientModule],
	  providers: [AppComponent],
    schemas: [
      CUSTOM_ELEMENTS_SCHEMA,
      NO_ERRORS_SCHEMA,
      ]
 })
    .compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


/*
//IN PROGRESS......

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { By } from '@angular/platform-browser';
import { Location } from "@angular/common";
import { LoginComponent } from 'src/app/Components/login/login.component';
import { NgModule } from '@angular/core';
import { UserService } from 'src/app/Services/user.service';
 
describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let userService: jasmine.SpyObj<UserService>;
  let router: Router;

  beforeEach(async () => {
    userService = jasmine.createSpyObj<UserService>('UserService', ['getUserByUsername']);
    userService.getUserByUsername.and.returnValue(of());

    router = jasmine.createSpyObj('Router', ['navigate'], {
      url: './sign-up',
      routeReuseStrategy: { shouldReuseRoute: false },

    });
    await TestBed.configureTestingModule({
      imports: [FormsModule, Router, NgModule, ],
      declarations: [LoginComponent ],
      providers: [{ provide: UserService, useValue: userService },]
    })
    .compileComponents();

    router = TestBed.inject(Router);
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
 
  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
 
  it('should create', () => {
    expect(component).toBeTruthy();
  });
 
  it('should be able to login by entering email and password and submitting form', () => {
    component.currentUser['email'] = 'test@test.com';
    component.currentUser['password'] = '123456789';
    const loginBtn = fixture.debugElement.query(By.css('button.login-btn')).nativeElement;
    loginBtn.click();
    expect(component.onSubmit).toBeTrue;
  });
 
 
  it('should route to register page by clicking “Sign Up here” link', () => {
    const location: Location = TestBed.inject(Location);
    const link =fixture.debugElement.query(By.css('a')).nativeElement;
    link.click();
    expect(location.path()).toBe('./sign-up');
  });
});
*/