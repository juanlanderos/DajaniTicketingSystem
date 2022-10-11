import { Comment } from "./comment";
import { User } from "./user";

export class Ticket {
    ticketId!:number;
    createdAt!: String;
    updatedAt!: String;
    completedAt!: String;
    statusId!: String;
    commentList!: Comment[];
    userList!: User[];
    
}
