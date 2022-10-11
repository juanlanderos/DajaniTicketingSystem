import { Ticket } from "./ticket";
import { User } from "src/app/Models/user";

export class Comment{
    commentId!: number;
    ticketEntity!: Ticket;
    userEntity!: User;
    content!: String;
    createdAt!: String;
    updatedAt!: String;
}
