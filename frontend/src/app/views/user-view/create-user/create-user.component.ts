import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { Site } from 'src/app/shared/models/site.module';
import { Team } from 'src/app/shared/models/team.module';
import { Departement } from 'src/app/shared/models/departement.module';
import { SiteService } from '../../site-view/site.service';
import { TeamService } from '../../team-view/team.service';
import { DepartementService } from '../../departement-view/departement.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  addUserForm!: FormGroup; // Déclarez le formulaire FormGroup
  // Déclarez ici les listes nécessaires pour les options de sélection (départements, équipes, sites, etc.)
  sites: Site[] = [];
  teams: Team[] = [];
  departements: Departement[] = [];
  
  constructor(private formBuilder: FormBuilder,public modal: NgbActiveModal, private userService: UserService,private siteService:SiteService,private teamService:TeamService
    ,private departementService: DepartementService) { this.addUserForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      pays: ['', Validators.required],
      departementId: ['', Validators.required],
      teamId: ['', Validators.required],
      siteId: ['', Validators.required],
      hirDate: ['', Validators.required],
      role: [[]],
      quota: ['', Validators.required],
      residuel: ['', Validators.required],

       // Utilisation de [[]] pour permettre la sélection multiple des rôles
      // Ajoutez ici les champs pour quota et résiduel, le cas échéant
    });}

  ngOnInit(): void {
    this.siteService.getSitesList().subscribe((data) => {
      this.sites = data;
    });
  
    this.teamService.getTeamsList().subscribe((data) => {
      this.teams = data;
    });
  
    this.departementService.getDepartementsList().subscribe((data) => {
      this.departements = data;
    });
  }

  onSubmit(): void {
    if (this.addUserForm.valid) {
      // Récupérez les valeurs du formulaire
      const formData = this.addUserForm.value;

      // Appelez le service pour ajouter l'utilisateur
      this.userService.createUser(formData).subscribe(
        (response) => {
          // Gérez la réponse (par exemple, affichez un message de succès)
        },
        (error) => {
          // Gérez les erreurs (par exemple, affichez un message d'erreur)
        }
      );
    }
  }

}
