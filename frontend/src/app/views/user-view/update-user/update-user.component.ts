import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from 'src/app/shared/models/userDetails.module';
import { UserService } from '../user.service';
import { UpdateUserForm } from 'src/app/shared/models/UpdateUserForm.module';
import { Site } from 'src/app/shared/models/site.module';
import { Team } from 'src/app/shared/models/team.module';
import { Departement } from 'src/app/shared/models/departement.module';
import { SiteService } from '../../site-view/site.service';
import { DepartementService } from '../../departement-view/departement.service';
import { TeamService } from '../../team-view/team.service';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css'],
})
export class UpdateUserComponent implements OnInit {
  updateUserForm: FormGroup;
  userId!: number;
  currentUser!: UpdateUserForm; // Ajoutez cette propriété pour stocker les données de l'utilisateur actuel
  sites: Site[] = [];
  teams: Team[] = [];
  departements: Departement[] = [];
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private updateUserService: UserService,
    private siteService:SiteService,
    private teamService:TeamService
    ,private departementService: DepartementService
  ) {
    this.updateUserForm = this.formBuilder.group({
      username: '',
      email: '',
      hirDate: '', // Ajoutez les autres champs du formulaire
      departementId: '',
      teamId: '',
      siteId: '',
      quota: '',
      residuel: '',
      departementName:'',
      teamName:'',
      siteName:''
      // Ajoutez d'autres champs du formulaire
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.userId = +params['id'];

      // Chargez les données de l'utilisateur actuel à partir du service ou de l'API
      this.loadCurrentUser();
    });
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

  loadCurrentUser() {
    // Utilisez le service ou l'API pour récupérer les données de l'utilisateur actuel
    // Mettez à jour la propriété currentUser avec ces données
    this.updateUserService.getUserById(this.userId).subscribe(
      (user) => {
        this.currentUser = user;
  
        // Préremplissez les champs du formulaire avec les données de l'utilisateur actuel
        this.updateUserForm.patchValue({
          username: this.currentUser.username,
          email: this.currentUser.email,
          hirDate: this.currentUser.hirDate,
          departementId: this.currentUser.departementId,
          teamId: this.currentUser.teamId,
          siteId: this.currentUser.siteId,
          quota: this.currentUser.quota,
          residuel: this.currentUser.residuel,
            departementName: this.currentUser.currentDepartementName, // Ajoutez ces lignes
  teamName: this.currentUser.currentTeamName,
  siteName: this.currentUser.currentSiteName,
        });
      },
      (error) => {
        console.error('Erreur lors du chargement des données de l\'utilisateur :', error);
        // Gérez l'erreur
      }
    );
  }
  
  // loadCurrentUser() {
  //   // Utilisez le service ou l'API pour récupérer les données de l'utilisateur actuel
  //   // Mettez à jour la propriété currentUser avec ces données
  //   this.updateUserService.getUserById(this.userId).subscribe(
  //     (user) => {
  //       this.currentUser = user;

  //       // Préremplissez les champs du formulaire avec les données de l'utilisateur actuel
  //       this.updateUserForm.patchValue({
  //         username: this.currentUser.username,
  //         email: this.currentUser.email,
  //         // Préremplissez d'autres champs du formulaire
  //       });
  //     },
  //     (error) => {
  //       console.error('Erreur lors du chargement des données de l\'utilisateur :', error);
  //       // Gérez l'erreur
  //     }
  //   );
  // }

  onSubmit() {
    if (this.updateUserForm.valid) {
      const updateUserFormData: UpdateUserForm = this.updateUserForm.value;

      this.updateUserService.updateUser(this.userId, updateUserFormData).subscribe(
        () => {
          // Mise à jour réussie, redirigez l'utilisateur vers la page de détails de l'utilisateur
          this.router.navigate(['user', this.userId]);
        },
        (error) => {
          console.error('Erreur lors de la mise à jour de l\'utilisateur :', error);
          // Gérez l'erreur
        }
      );
    }
  }
}

  // updateUserForm!: FormGroup;
  // userId!: number;
  // user: any; // Vous devez définir un modèle utilisateur approprié

  // // Injectez les dépendances nécessaires (FormBuilder, ActivatedRoute, Router, UserService) dans le constructeur
  // constructor(
  //   private formBuilder: FormBuilder,
  //   private route: ActivatedRoute,
  //   private router: Router,
  //   private userService: UserService,
  //   public modal: NgbActiveModal
  // ) { }

  // ngOnInit() {
  //   if (this.userId) {
  //     this.userService.getUserById(this.userId)
  //       .subscribe(data => {
  //         this.user = data;
  //       });
  //   }
  //       // Initialisez le formulaire réactif avec les champs et les validateurs appropriés
  //   this.updateUserForm = this.formBuilder.group({
  //     username: ['', Validators.required],
  //     email: ['', [Validators.required, Validators.email]],
  //     // Ajoutez d'autres champs et validateurs au besoin
  //   });

  //   // Récupérez les données de l'utilisateur à mettre à jour en utilisant son ID
  //   this.userService.getUserById(this.userId).subscribe(data => {
  //     this.user = data;
  //     // Remplissez le formulaire avec les données de l'utilisateur à mettre à jour
  //     this.updateUserForm.patchValue({
  //       username: this.user.username,
  //       email: this.user.email,
  //       // Associez les autres champs aux propriétés du modèle utilisateur
  //     });
  //   });
  // }

  // onSubmit() {
  //   if (this.updateUserForm.invalid) {
  //     return;
  //   }

  //   // Créez un objet utilisateur à partir des données du formulaire
  //   const updatedUser = {
  //     id: this.userId,
  //     username: this.updateUserForm.value.username,
  //     email: this.updateUserForm.value.email,
  //     // Associez les autres champs aux propriétés du modèle utilisateur
  //   };

  //   // Appelez le service pour mettre à jour l'utilisateur
  //   this.userService.updateUser(this.userId,updatedUser).subscribe(
  //     response => {
  //       console.log('Utilisateur mis à jour avec succès !', response);
  //       this.router.navigate(['/users']); // Redirigez vers la liste des utilisateurs après la mise à jour
  //     },
  //     error => {
  //       console.error('Erreur lors de la mise à jour de l\'utilisateur :', error);
  //       // Gérez l'erreur comme vous le souhaitez
  //     }
  //   );
  // }

