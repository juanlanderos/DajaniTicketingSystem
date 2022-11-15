import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user';
import { AuthService } from 'src/app/Services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

 //user: User = new User();
 userForm: FormGroup;	

  constructor(private router: Router,
              private authService: AuthService,
        private fb: FormBuilder) { 
          this.userForm = fb.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            userName: ['', Validators.required],
            password: ['', Validators.required],
          });
          }

  ngOnInit(): void {
  }

  signUp(){
    //call the user service and call the endpoint for signup
    this.authService.signUp(this.userForm.value).subscribe(data => {
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
