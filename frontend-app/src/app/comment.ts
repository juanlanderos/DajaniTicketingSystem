import { Ticket } from "./ticket";
import { User } from "./user";

export class Comment{
    commentId!: number;
    ticketEntity!: Ticket;
    content!: String;
    userEntity!: User;
    createdAt!: String;
    updatedAt!: String;
}
