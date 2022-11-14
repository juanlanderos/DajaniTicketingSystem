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
  

  constructor(private myLocalStorage: LocalStorageService) {  
    this.currentUser = myLocalStorage.get('current_user');
    console.log(this.currentUser);
    if(this.currentUser != null){
      //user isnt logged in, hide the nav bar
      this.isLoggedIn = true;
    }else{
      //user is logged in
      this.isLoggedIn = false;
    }
    window.onbeforeunload = function() {
      localStorage.clear();
      return '';
    };
  }

  toggleNavBar(){
    this.isLoggedIn = !this.isLoggedIn;
  }
}
