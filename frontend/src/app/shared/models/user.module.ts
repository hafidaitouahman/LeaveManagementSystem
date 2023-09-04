import { Departement } from "./departement.module";
import { Site } from "./site.module";
import { Team } from "./team.module";

export class User {
    id!: number;
    username!: string;
    email!: string;
    password!: string;
    hirDate!: Date;
    team!: Team; // Replace with the appropriate type for Team
    department!: Departement; 
    site!: Site;// Replace with the appropriate type for Department
    // Add other properties as needed
  }