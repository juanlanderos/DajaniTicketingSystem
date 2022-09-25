import { Ticket } from "./ticket";
import { User } from "src/app/Models/user";

export class Comment{
    commentId!: number;
    ticketEntity!: Ticket;
    content!: String;
    userEntity!: User;
    createdAt!: String;
    updatedAt!: String;
}
