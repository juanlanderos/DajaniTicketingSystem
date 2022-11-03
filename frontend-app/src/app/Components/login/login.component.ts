import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtToken } from 'src/app/Models/jwt-token';
import { LoginForm } from 'src/app/Models/login-form';
import { AuthService } from 'src/app/Services/auth.service';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: LoginForm = new LoginForm();
  tokens!: JwtToken;

  constructor(private authService: AuthService,
    private localStorage: LocalStorageService,
    private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.authService.login(this.loginForm).subscribe(data =>{
      //this runs if the login is valid
      this.tokens = data;
      console.log(this.tokens);
      this.localStorage.set("access_token", this.tokens.access_token);
      this.localStorage.set("refresh_token", this.tokens.refresh_token);
      this.localStorage.set("current_user", this.loginForm.username);
      let decodeToken = jwt_decode(this.tokens.access_token);

      //at the end, route the user to their corresponding home page (based on role?)
    })
  }

  onSubmit(){
    console.log(this.loginForm);
    this.login();
  }
}
