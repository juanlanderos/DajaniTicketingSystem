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
import { SignUpComponent } from './Components/sign-up/sign-up.component';
import { ForgotPasswordComponent } from './Components/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './Components/reset-password/reset-password.component';

const routes: Routes = [
  {path: "users", component: UserListComponent},
  {path: "create-user", component: CreateUserComponent},
  {path: '', redirectTo: "login", pathMatch: "full"},
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
  {path: "login", component: LoginComponent},
  {path: "signUp", component: SignUpComponent},
  {path: "forgotPassword", component: ForgotPasswordComponent},
  {path: "resetPassword", component: ResetPasswordComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
