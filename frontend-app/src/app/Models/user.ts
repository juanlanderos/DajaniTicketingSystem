import { Comment } from "./comment";
import { Ticket } from "./ticket";

export class User {
    userId!: number;
    email!: String;
    firstName!: String;
    lastName!: String;
    commentList!: Comment[];
    ticketList!: Ticket[];
}
