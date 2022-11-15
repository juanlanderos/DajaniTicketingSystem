import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-change-role',
  templateUrl: './change-role.component.html',
  styleUrls: ['./change-role.component.css']
})
export class ChangeRoleComponent implements OnInit {

  username!:string;
  newRole!:string;

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  changeRole(){
    this.userService.changeUserRole(this.username, this.newRole).subscribe(data => {
      console.log(data);
      this.router.navigate(['/dashboard']);
    })
  }

  onSubmit(){
    this.changeRole();
  }

}
