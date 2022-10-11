import { Comment } from "./comment";
import { Ticket } from "./ticket";

export class User {
    userId!: number;
    email!: String;
    commentList!: Comment[];
    ticketList!: Ticket[];
}
