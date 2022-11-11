import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Dajani Ticketing System';

  constructor() {   
    window.onbeforeunload = function() {
      localStorage.clear();
      return '';
    };
  }
}
