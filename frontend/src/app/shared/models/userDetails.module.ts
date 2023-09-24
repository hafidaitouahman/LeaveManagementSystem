export class UserDetails{
    id!: number;
    username!: string;
    email!: string;
    pays!: string;
    departementName!: string; // Le nom du département de l'utilisateur
    teamName!: string;       // Le nom de l'équipe de l'utilisateur
    siteName!: string;       // Le nom du site de l'utilisateur
    active!: string;
    hirDate!: Date;
}