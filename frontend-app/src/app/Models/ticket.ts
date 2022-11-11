import { User } from "./user";

export class Ticket {
    ticketId!:number;
    ticketDept!: string;
    createdAt!: string;
    updatedAt!: string;
    completedAt!: string;
    statusId!: string;
    assigneeId!: number;
    commentList!: Comment[];
    userList!: User[];
    requesterId!: number;
    title!: string;

}
