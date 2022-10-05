import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/Models/profile';
import { Router } from '@angular/router';
import { ProfileService } from 'src/app/Services/profile.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: Profile[] =  [];
  constructor(private ProfileService: ProfileService, 
    private router: Router) { }

  ngOnInit(): void {
    this.getProfile();
  }

  private getProfile(){
    this.ProfileService.getProfile().subscribe(data => {this.profile = data})
  }
}
