export class UpdateUserForm {
    username!: string;
    email!: string;
    pays!: string;
    departementId!: number; // Le nom du département de l'utilisateur
    teamId!: number;       // Le nom de l'équipe de l'utilisateur
    siteId!: number;       // Le nom du site de l'utilisateur
    hirDate!: Date;
    role!:string[];
    quota!:number;
    residuel!:number;
    currentDepartementName!: string; // Ajoutez ces propriétés
    currentTeamName!: string;
    currentSiteName!: string;
  }
  