import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  token!: string;
  password!: string;
  constructor(private appComponent: AppComponent,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
    //when you go to this page, make sure to disable the navbars if they are up
    if(this.appComponent.isLoggedIn == true) {
      this.appComponent.toggleNavBar();
    }
  }

  resetPassword(){
    //call the user service and call endpoint for resetting a password with that token
    this.authService.processResetPassword(this.token,this.password).subscribe(data =>{
      //reset password has been processed and accepted, so route them back to login page
        this.goToLogin();
    })
  }

  goToLogin(){
    this.router.navigate(['/login']);
  }

  onSubmit(){
    console.log(this.token);
    console.log(this.password);
    this.resetPassword();
  }

}
