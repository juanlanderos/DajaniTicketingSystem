import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtToken } from 'src/app/Models/jwt-token';
import { LoginForm } from 'src/app/Models/login-form';
import { AuthService } from 'src/app/Services/auth.service';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import jwt_decode from "jwt-decode";
import { UserService } from 'src/app/Services/user.service';
import { User } from 'src/app/Models/user';
import { AppComponent } from 'src/app/app.component';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  currentUser!: User;
  tokens!: JwtToken;

  constructor(private authService: AuthService,
    private localStorage: LocalStorageService,
    private userService: UserService,
    private router: Router,
    private appComponent: AppComponent) { 
      this.loginForm = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required)
      });
    }

  ngOnInit(): void {
    if(this.appComponent.isLoggedIn == true){
      localStorage.clear();
      this.appComponent.toggleNavBar();
    }
  }

  login(){
    this.authService.login(this.loginForm.value).subscribe(data =>{
      //this runs if the login is valid
      this.tokens = data;
      console.log(this.tokens);
      const { username } = this.loginForm.value;
      this.localStorage.set("access_token", this.tokens.access_token);
      this.localStorage.set("refresh_token", this.tokens.refresh_token);
      this.localStorage.set("current_user", username);
      //let decodeToken = jwt_decode(this.tokens.access_token);

      //using the username, get that user and store info into localstorage
      this.userService.getUserByUsername(username).subscribe(data => {
        this.currentUser = data;
        
        this.localStorage.set("firstName", this.currentUser.firstName);
        this.localStorage.set("lastName", this.currentUser.lastName);
        this.localStorage.set("role", this.currentUser.roles[0].roleName);
        this.appComponent.checkCurrentRole(this.localStorage.get("role") as string);
      });
      //before going to the dashboard, toggle on the nav bars
      this.appComponent.toggleNavBar();

      //at the end, route the user to their corresponding home page 
      this.goToDashboard();
    })
  }

  goToDashboard(){
    this.router.navigate(['/dashboard']);
  }

  onSubmit(){
    console.log(this.loginForm);
    this.login();
  }
}
