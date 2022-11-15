import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  password!: string;
  username!: string;

  constructor(private userService: UserService,
              private router: Router,
              private localStorage: LocalStorageService) { }

  ngOnInit(): void {
    this.username = this.localStorage.get("current_user") as string;
  }

  saveNewPassword(){
    this.userService.changePassword(this.username,this.password).subscribe(data => {
      console.log(data);
      this.router.navigate(['/login']);
    })
  }

  onSubmit(){
    this.saveNewPassword();
  }

}
