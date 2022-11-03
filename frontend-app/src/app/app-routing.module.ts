import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommentListComponent } from './Components/comment-list/comment-list.component';
import { CreateCommentComponent } from './Components/create-comment/create-comment.component';
import { CreateTicketComponent } from './Components/create-ticket/create-ticket.component';
import { CreateUserComponent } from './Components/create-user/create-user.component';
import { TicketListComponent } from './Components/ticket-list/ticket-list.component';
import { UpdateUserComponent } from './Components/update-user/update-user.component';
import { UserListComponent } from './Components/user-list/user-list.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { TicketViewComponent } from './Components/ticket-view/ticket-view.component';
import { UpdateTicketsComponent } from './Components/update-tickets/update-tickets.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { LoginComponent } from './Components/login/login.component';

const routes: Routes = [
  {path: "users", component: UserListComponent},
  {path: "create-user", component: CreateUserComponent},
  {path: '', redirectTo: "users", pathMatch: "full"},
  {path: "update-user/:id", component: UpdateUserComponent},
  {path: "tickets", component: TicketListComponent},
  {path: "create-ticket", component: CreateTicketComponent},
  {path: "comments", component: CommentListComponent},
  {path: "create-comment", component: CreateCommentComponent},
  {path: "profile", component: ProfileComponent},
  {path: "ticket-view", component: TicketViewComponent},
  {path: "ticket-view/:id", component: TicketViewComponent},
  {path: "dashboard", component: DashboardComponent},
  {path: "update-tickets/:statusId", component: UpdateTicketsComponent},
  {path: "login", component: LoginComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
