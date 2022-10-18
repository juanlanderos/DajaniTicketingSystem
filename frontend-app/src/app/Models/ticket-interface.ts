//Ticket Model
export interface ITicket{
    ticketId: number;
    ticketDept: string; //Ticket category/department. Ex. NCS, NCT, ECS..
	title: string;
    description: string;
    createdAt: string;
    updatedAt: string;
    completedAt: string;
    statusId: number;
    assigneeId: number;
    requesterId: number;
	user: IUser; 
    admin: string
    agent: string
    requester: string;
}

export interface IUser {
	userId: string;
	firstName: string;
	lastName: string;
	email: string;
    phone: number;
}