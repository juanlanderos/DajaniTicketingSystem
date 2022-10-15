import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Comment } from '../../Models/comment';
import { CommentService } from '../../Services/comment.service';

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css']
})
export class CreateCommentComponent implements OnInit {

  comment: Comment = new Comment();
  ticketId!: number;
  userId!: number;

  constructor(private commentService: CommentService,
    private router: Router) { }

  ngOnInit(): void {
  }

 saveComment(){
     this.commentService.createComment(this.comment, this.ticketId, this.userId).subscribe( data => {
     this.goToCommentList();
  }, error => console.log(error));
 }

  goToCommentList(){
    this.router.navigate(['/comments']);
  }

  onSubmit(){
    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
    this.comment.createdAt = date + ' :: ' + time;
    this.comment.updatedAt = date + ' :: ' + time;
    console.log(this.comment);
    this.saveComment();
  }

}
