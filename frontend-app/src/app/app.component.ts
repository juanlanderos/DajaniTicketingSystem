import { Component } from '@angular/core';
import { LocalStorageService } from './Services/local-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Dajani Ticketing System';
  isLoggedIn! : boolean;
  currentUser!: string | null;
  isAdmin!: boolean;
  isAgent!: boolean;
  isUser!: boolean; 
  

  constructor(private myLocalStorage: LocalStorageService) {  
    this.currentUser = myLocalStorage.get('current_user');
    if(this.currentUser != null){
      //user is logged in
      this.isLoggedIn = true;
    }else{
      //user is not logged in
      this.isLoggedIn = false;
    }
    window.onbeforeunload = function() {
      //localStorage.clear();
      return '';
    };
  }

  signUserIn(){
    this.isLoggedIn = true;
  }
  signUserOut(){
    this.isLoggedIn = false;
  }

  checkCurrentRole(role: string){
    if(role == "ADMIN"){
      this.isAdmin = true;
      this.isAgent = false;
      this.isUser = false;
    } else if (role == "AGENT"){
      this.isAdmin = false;
      this.isAgent = true;
      this.isUser = false;
    } else if ( role == "USER"){
      this.isAdmin = false;
      this.isAgent = false;
      this.isUser = true;
    }
  }
}
