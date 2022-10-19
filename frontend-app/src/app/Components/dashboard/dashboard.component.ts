import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardService } from '../../Services/dashboard.service';
import { TicketView } from '../../Models/ticket-view';
import { TicketViewComponent } from '../ticket-view/ticket-view.component';
import { TicketViewService } from '../../Services/ticket-view.service';
import { TicketListComponent } from 'src/app/Components/ticket-list/ticket-list.component';
import { TicketService } from '../../Services/ticket.service';
import { Dashboard } from 'src/app/Models/dashboard';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tickets: TicketView[] = [];
  constructor(private DashboardService: DashboardService,
    private router: Router) {}

  ngOnInit(): void {
    // this.getTickets();
  }

    // //REMOVE WHEN DB IS OFFICIALLY LINKED. TEMPORARY
    // private getTickets(){
    //   this.ticketViewService.getTicketsList().subscribe(data => {
    //     this.tickets = data.data
    //   });
    // }

}