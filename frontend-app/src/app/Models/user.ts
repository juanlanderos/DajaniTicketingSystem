import { Comment } from "./comment";
import { Role } from "./role";
import { Ticket } from "./ticket";

export class User {
    userId!: number;
    username!: string;
    email!: string;
    firstName!: string;
    lastName!: string;
    password!: string;
    roles!: Role[];
    commentEntityList!: Comment[];
    ticketEntities!: Ticket[];
}
