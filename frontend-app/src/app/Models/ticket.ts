import { Comment } from "./comment";
import { User } from "./user";

export class Ticket {
    ticketId!:number;
    ticketDept!: string;
    createdAt!: String;
    updatedAt!: String;
    completedAt!: String;
    statusId!: String;
    assigneeId!: number;
    commentList!: Comment[];
    userList!: User[];
    requesterId!: number;
}
