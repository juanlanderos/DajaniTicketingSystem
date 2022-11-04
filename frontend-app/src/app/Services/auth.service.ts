import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtToken } from '../Models/jwt-token';
import { LoginForm } from '../Models/login-form';

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
}
