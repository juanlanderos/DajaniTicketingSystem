import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user';
import { AuthService } from 'src/app/Services/auth.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

 user: User = new User();
 userForm: FormGroup;	

  constructor(private router: Router,
              private authService: AuthService) { 
          this.userForm = new FormGroup({
            userName: new FormControl('', Validators.required),
            email: new FormControl('', [Validators.required, Validators.email]),
            firstName: new FormControl('', Validators.required),
            lastName: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required),
          });
  }

  ngOnInit(): void {
  }

  signUp(){
    //call the user service and call the endpoint for signup
    console.log(this.userForm.value);
    this.user.username = this.userForm.value.userName;
    this.user.email = this.userForm.value.email;
    this.user.firstName = this.userForm.value.firstName;
    this.user.lastName = this.userForm.value.lastName;
    this.user.password = this.userForm.value.password;
    console.log(this.user);
    this.authService.signUp(this.user).subscribe(data => {
      //if signUp is successful, route them to the login page
      console.log("Sign-Up Successfu!");
      this.router.navigate(['/login']);
    })

  }

  onSubmit(){
    console.log(this.userForm.value);
    this.signUp();
  }

}
