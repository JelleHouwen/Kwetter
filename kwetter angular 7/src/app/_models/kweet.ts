import { User } from "./user";
import { Topic } from "./topic";
export class Kweet {
    ID: number;
    text: string;
    placer: User;
    date: Date;
    mentions:User[];
    topics:Topic[];
}