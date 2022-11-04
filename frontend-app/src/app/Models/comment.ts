import { Ticket } from "./ticket";
import { User } from "src/app/Models/user";

export class Comment {
    commentId!: number;
    ticketEntity!: Ticket;
    userEntity!: User;
    content!: string;
    createdAt!: string; //changed to lowercase "s" to handle dates better in Angular
    updatedAt!: string;
}
