export class TicketView {
    ticketId!:number;
    createdAt!: String;
    updatedAt!: String;
    completedAt!: String;
    statusId!: String;
    assigneeId!: number;
    requesterId!: number; //name of person that requested ticket
    firstName!: string;
    lastName!: string;
    title!: string; //title of ticket
    description!: string; //ticket issue description
    ticketState!: string; //open, closed, resolved...
    admin!: string;
}
