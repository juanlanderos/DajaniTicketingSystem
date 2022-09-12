import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommentListComponent } from './comment-list/comment-list.component';
import { CreateCommentComponent } from './create-comment/create-comment.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserListComponent } from './user-list/user-list.component';

const routes: Routes = [
  {path: "users", component: UserListComponent},
  {path: "create-user", component: CreateUserComponent},
  {path: '', redirectTo: "users", pathMatch: "full"},
  {path: "update-user/:id", component: UpdateUserComponent},
  {path: "tickets", component: TicketListComponent},
  {path: "create-ticket", component: CreateTicketComponent},
  {path: "comments", component: CommentListComponent},
  {path: "create-comment", component: CreateCommentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
