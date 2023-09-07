import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuotaService } from '../quota.service';
import { UserService } from '../../user-view/user.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-quota',
  templateUrl: './create-quota.component.html',
  styleUrls: ['./create-quota.component.css']
})
export class CreateQuotaComponent implements OnInit {

  // quotaForm!: FormGroup;
  // usersList: any[] = []; // Initialize usersList with your user data
  // selectedUserIds: number[] = [];

  // constructor(private fb: FormBuilder, private quotaService: QuotaService,private userService: UserService) {}

  
// ngOnInit(): void {
//   this.quotaForm = this.fb.group({
//     year: [null, Validators.required],
//     quota: [null, Validators.required],
//     selectedUsers: [[]], // Initialisez comme un tableau vide
//   });

//   // Fetch user data from your backend API or database
//   this.userService.getUsersList().subscribe(
//     (users) => {
//       // Assuming your API response is an array of user objects
//       this.usersList = users;
//     },
//     (error) => {
//       console.error('Error fetching user data:', error);
//     }
//   );
// }

// onUserSelect(userId: number): void {
//   // Check if the userId is already in the array
//   const index = this.selectedUserIds.indexOf(userId);
//   if (index === -1) {
//     // If not, add it to the array
//     this.selectedUserIds.push(userId);
//   } else {
//     // If it's already in the array, remove it
//     this.selectedUserIds.splice(index, 1);
//   }
// }

// // Function to create a quota
// createQuota(): void {
//   // Convert the selectedUserIds array to a comma-separated string
//   const queryParams = `?userIds=${this.selectedUserIds.join(',')}`;
  
//   // Get year and quota from the form
//   const yearControl = this.quotaForm.get('year');
//   const quotaControl = this.quotaForm.get('quota');
//   if (yearControl && quotaControl){
//     const year = yearControl.value;
//       const quota = quotaControl.value;
//   // Make the HTTP POST request with queryParams
//   this.quotaService.createQuota({ year, quota }, queryParams).subscribe(
//     (response) => {
//       // Handle the response as needed
//     },
//     (error) => {
//       // Handle errors
//       console.error('Error creating quota:', error);} );
//     }  else {
//       // Handle the case where year, quota, or selectedUsers is null or not defined
//     }
//   } 
  
 
  // createQuota() {
  //   const yearControl = this.quotaForm.get('year');
  //   const quotaControl = this.quotaForm.get('quota');
  //   const selectedUsersControl = this.quotaForm.get('selectedUsers');

  //   if (yearControl && quotaControl && selectedUsersControl) {
  //     const year = yearControl.value;
  //     const quota = quotaControl.value;
  //     const selectedUserIds = selectedUsersControl.value;

  //     // Use selectedUserIds to get the selected user IDs
  //     const queryParams = `?userIds=${selectedUserIds.join(',')}`;

  //     // Make the HTTP POST request with queryParams
  //     this.quotaService.createQuota({ year, quota }, queryParams).subscribe(
  //       (response) => {
  //         // Handle the response as needed
  //       },
  //       (error) => {
  //         console.error('Error creating quota:', error);
  //       }
  //     );
  //   } else {
  //     // Handle the case where year, quota, or selectedUsers is null or not defined
  //   }
  // }
  


  
  quotaForm!: FormGroup;
  usersList: any[] = [];
  selectedUserIds: number[] = []; // Array to store selected user IDs

  constructor(private fb: FormBuilder, private quotaService: QuotaService, private userService: UserService,public modal: NgbActiveModal,private router: Router) {}

  ngOnInit(): void {
    this.quotaForm = this.fb.group({
      year: ['', [Validators.required]],
      quota: ['', [Validators.required]],
      users: [[], [Validators.required]],
    });

    // Fetch the list of users when the component initializes
    this.userService.getUsersList().subscribe((users) => {
      this.usersList = users;
    });
  }

  // Function to handle user selection
  // onUserSelect(userId: number): void {
  //   if (this.selectedUserIds.includes(userId)) {
  //     // If user is already selected, remove them from the list
  //     this.selectedUserIds = this.selectedUserIds.filter((id) => id !== userId);
  //   } else {
  //     // If user is not selected, add them to the list
  //     this.selectedUserIds.push(userId);
  //   }

   
  // }

  onUserSelect(userId: number): void {
    // Check if the userId is already in the array
    const index = this.selectedUserIds.indexOf(userId);
    if (index === -1) {
      // If not, add it to the array
      this.selectedUserIds.push(userId);
    } else {
      // If it's already in the array, remove it
      this.selectedUserIds.splice(index, 1);
    }
  }

  // Function to create a quota
  createQuota(): void {
        // Vérification de nullité pour year et quota
        const yearControl = this.quotaForm.get('year');
        const quotaControl = this.quotaForm.get('quota');

        if (yearControl && quotaControl) {
          // Obtenir la valeur de l'année (year) à partir du contrôle (yearControl)
          const year = yearControl.value;
          // Obtenir la valeur du quota à partir du contrôle (quotaControl)
          const quota = quotaControl.value;

          // Create queryParams to include selectedUserIds in the request URL
          const queryParams = `?userIds=${this.selectedUserIds.join(',')}`;

          // Make the HTTP POST request with queryParams
          this.quotaService.createQuota({ year, quota }, queryParams).subscribe((response) => {
            // Handle the response as needed
            // This is where you can process the response from the server, e.g., display a success message or handle errors.
          });
          this.modal.close(this.quotaForm); // Ferme la boîte de dialogue
          this.gotoList();
        } else {
          // Traitez le cas où year ou quota sont nuls ou non définis
          console.error('Year or Quota control is null or undefined.');
          // Ou vous pouvez effectuer d'autres actions en cas de valeurs manquantes.
        }
   
      
}

gotoList() {
  this.router.navigate(['/leavequota']);
}
}
