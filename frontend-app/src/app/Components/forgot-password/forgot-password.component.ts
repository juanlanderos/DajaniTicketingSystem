import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  email!: string;
  constructor(private appComponent: AppComponent,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
    //when you go to this page, make sure to disable the navbars if they are up
    if(this.appComponent.isLoggedIn == true) {
      this.appComponent.toggleNavBar();
    }
  }

  forgotPassword(){
    //call the user service and call the endpoint for forgetting password
    this.authService.processForgotPassword(this.email).subscribe(data =>{
      //reset password has been processed and accepted, so route them back to login page
        this.goToLogin();
    })
  }

  goToLogin(){
    this.router.navigate(['/login']);
  }

  onSubmit(){
    this.forgotPassword();
  }
}
