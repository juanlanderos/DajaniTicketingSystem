import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommentService } from '../comment.service';
import { Comment } from '../comment';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  comments: Comment[] = [];

  constructor(private commentService: CommentService, 
    private router: Router) { }

  ngOnInit(): void {
    this.getComments();
  }

  private getComments(){
    this.commentService.getCommentsList().subscribe(data => {
      this.comments = data;
    })
  }

  

}
