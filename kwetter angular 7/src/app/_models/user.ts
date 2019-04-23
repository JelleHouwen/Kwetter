import { Kweet } from "./kweet";
import { Role } from "./role";
export class User {
    ID: number;
    userName: string;
    password: string;
    website: string;
    bio: string;
    location:string;
    profilePicture:string;
    following:User;
    followers:User;
    kweets:Kweet;
    roles: Role[];
}