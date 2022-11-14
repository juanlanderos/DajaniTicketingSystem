import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  user: User = new User();

  constructor(private router: Router,
              private authService: AuthService) { }

  ngOnInit(): void {
  }

  signUp(){
    //call the user service and call the endpoint for signup
    this.authService.signUp(this.user).subscribe(data => {
      //if signUp is successful, route them to the login page
      console.log("Sign up successful");
      this.router.navigate(['/login']);
    })

  }

  onSubmit(){
    console.log(this.user);
    this.signUp();
  }

}
