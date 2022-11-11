import { User } from "./user";

export class Ticket {
    ticketId!:number;
    title!: string;
    description!: string;
    createdAt!: string;
    updatedAt!: string;
    completedAt!: string;
    status!: string;
    commentList!: Comment[];
    userList!: User[];
    requesterId!: number;
    agentId!: number;

}
