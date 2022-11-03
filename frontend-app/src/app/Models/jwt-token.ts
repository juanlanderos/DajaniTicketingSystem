/*
    This class is for storing the tokens recieved from the backend
    when a user logs in.
*/
export class JwtToken {
    access_token!: string;
    refresh_token!: string;
}
