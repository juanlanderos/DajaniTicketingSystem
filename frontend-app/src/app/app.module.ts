import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AngularMaterialModule } from './angular-material/angular-material.module';

//Components below
import { UserListComponent } from './Components/user-list/user-list.component';
import { CreateUserComponent } from './Components/create-user/create-user.component';
import { UpdateUserComponent } from './Components/update-user/update-user.component';
import { TicketListComponent } from './Components/ticket-list/ticket-list.component';
import { CreateTicketComponent } from './Components/create-ticket/create-ticket.component';
import { CommentListComponent } from './Components/comment-list/comment-list.component';
import { CreateCommentComponent } from './Components/create-comment/create-comment.component';
import { MatIconModule } from '@angular/material/icon';
import { ProfileComponent } from './Components/profile/profile.component';
import { TicketViewComponent } from './Components/ticket-view/ticket-view.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { HtmlEditorComponent } from './Components/html-editor/html-editor.component';
import { AngularEditorModule } from '@kolkov/angular-editor';

//Daniel Imports Below this

import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';

//Daniel Imports Above this

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    CreateUserComponent,
    UpdateUserComponent,
    TicketListComponent,
    CreateTicketComponent,
    CommentListComponent,
    CreateCommentComponent,
    ProfileComponent,
    TicketViewComponent,
    DashboardComponent,
    HtmlEditorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatIconModule,
    //Daniel Imports below here
    BrowserAnimationsModule,
    MatSidenavModule,
    ReactiveFormsModule,
    MatButtonModule,
    //Daniel Imports above here
    //Russell Import
    AngularMaterialModule,
    //Russell Import
    AngularEditorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
