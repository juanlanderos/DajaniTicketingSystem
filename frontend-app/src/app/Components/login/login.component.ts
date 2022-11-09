import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtToken } from 'src/app/Models/jwt-token';
import { LoginForm } from 'src/app/Models/login-form';
import { AuthService } from 'src/app/Services/auth.service';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import jwt_decode from "jwt-decode";
import { UserService } from 'src/app/Services/user.service';
import { User } from 'src/app/Models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: LoginForm = new LoginForm();
  currentUser!: User;
  tokens!: JwtToken;

  constructor(private authService: AuthService,
    private localStorage: LocalStorageService,
    private userService: UserService,
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
      //let decodeToken = jwt_decode(this.tokens.access_token);

      //using the username, get that user and store info into localstorage
      this.userService.getUserByUsername(this.loginForm.username).subscribe(data => {
        this.currentUser = data;
        
        this.localStorage.set("firstName", this.currentUser.firstName);
        this.localStorage.set("lastName", this.currentUser.lastName);
        this.localStorage.set("role", this.currentUser.roles[0].roleName);
      });

      //at the end, route the user to their corresponding home page (based on role?)
    })
  }

  onSubmit(){
    console.log(this.loginForm);
    this.login();
  }
}
