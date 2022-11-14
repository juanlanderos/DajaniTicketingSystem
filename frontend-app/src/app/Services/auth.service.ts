import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtToken } from '../Models/jwt-token';
import { LoginForm } from '../Models/login-form';
import { User } from '../Models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = "http://localhost:9000";

  constructor(private http: HttpClient) { }

  //login
  login(loginForm: LoginForm): Observable<any>{
    return this.http.post(`${this.baseUrl}/api/login`, loginForm);
  }

  //new user registration
  signUp(user: User): Observable<any>{
    return this.http.post(`${this.baseUrl}/api/auth/register`, user);
  }

  //user forgot their password, begin the reset password process
  processForgotPassword(email : string){
    return this.http.post(`${this.baseUrl}/api/auth/forgotPassword/${email}`, null);
  }

  //user has received email to reset password, pass in the token and new password
  processResetPassword(token: string, password: string){
    return this.http.post(`${this.baseUrl}/api/auth/reset_password/${token}/${password}`, null);
  }

  //add another endpoint for changing the password when you are already logged in.
  //so no need to send emails or tokens, but find the user and set a new hashed password
}
