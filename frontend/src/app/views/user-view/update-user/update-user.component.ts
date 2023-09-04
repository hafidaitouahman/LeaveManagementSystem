import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Site } from 'src/app/shared/models/site.module';
import { Team } from 'src/app/shared/models/team.module';
import { Departement } from 'src/app/shared/models/departement.module';
import { UserService } from '../user.service';
import { TeamService } from '../../team-view/team.service';
import { DepartementService } from '../../departement-view/departement.service';
import { SiteService } from '../../site-view/site.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent {
  userForm: FormGroup;
  teams!: Team[]; // Liste des équipes depuis le backend
  sites!: Site[]; // Liste des sites depuis le backend
  departments!: Departement[]; // Liste des départements depuis le backend
  userId!: number; // Declare userId at the component level

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private siteService:SiteService,
    private departementService:DepartementService,
    private teamService:TeamService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.userForm = this.formBuilder.group({
      username: [''],
      email: [''],
      // Autres champs de l'utilisateur
      teamId: [''], // Sélection d'équipe
      siteId: [''], // Sélection de site
      departmentId: [''] // Sélection de département
    });
  }

  ngOnInit(): void {
    const userId = this.route.snapshot.params['id'];
    console.log('User ID:', userId); // Vérifiez la valeur de l'identifiant ici
  
    // Récupérer les données de l'utilisateur depuis le backend
    this.userService.getUser(userId).subscribe(user => {
      this.userForm.patchValue(user); // Remplir le formulaire avec les données de l'utilisateur
    });
    // Récupérer les données de l'utilisateur depuis le backend
    this.userService.getUser(userId).subscribe(user => {
      this.userForm.patchValue(user); // Remplir le formulaire avec les données de l'utilisateur
    });

    // Récupérer les données pour les listes déroulantes depuis le backend
    this.teamService.getTeamsList().subscribe(teams => {
      this.teams = teams;
    });

    this.siteService.getSitesList().subscribe(sites => {
      this.sites = sites;
    });

    this.departementService.getDepartementsList().subscribe(departments => {
      this.departments = departments;
    });
  }

  onSubmit() {
    const userId = this.route.snapshot.params['id'];
    // Envoyer les données mises à jour au backend
    this.userService.updateUser(userId, this.userForm.value).subscribe(() => {
      // Rediriger après la mise à jour (par exemple, vers la page de détails)
      const userId = this.route.snapshot.params['id'];
      this.router.navigate(['rh/users/user', userId]);
    });
    
}
}
