import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  chosenRole: String = "";

  constructor(private userService: UserService, 
    private router: Router) { }

  ngOnInit(): void {
  }

  saveUser(){
    this.userService.createUser(this.user).subscribe( data => {
      console.log(data);
      this.goToUserList();
    }, error => console.log(error));
  }

  goToUserList(){
    this.router.navigate(['/users']);
  }

  changeRole(input: String){
    switch(input) {
      case "admin":
        this.user.admin = 1;
        this.user.agent = 0;
        this.user.requester = 0;
        break;
      case "agent":
        this.user.admin = 0;
        this.user.agent = 1;
        this.user.requester = 0;
        break;
      case "requester":
        this.user.admin = 0;
        this.user.agent = 0;
        this.user.requester = 1;
        break;
      default:
        this.user.admin = 0;
        this.user.agent = 0;
        this.user.requester = 0;
        break;
    } 
  }

  onSubmit(){
    console.log(this.user);
    console.log(this.chosenRole);
    this.changeRole(this.chosenRole);
    this.saveUser();
  }

}
