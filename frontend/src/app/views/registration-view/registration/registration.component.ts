// import { Component } from '@angular/core';
// import { Router } from '@angular/router';
// import { RegistrationService } from '../registration.service';
// import { Registration } from 'src/app/shared/models/registration.module';
// @Component({
//   selector: 'app-registration',
//   templateUrl: './registration.component.html',
//   styleUrls: ['./registration.component.css']
// })
// export class RegistrationComponent {
//   user: Registration = new Registration(); // Assurez-vous que le nom et le type correspondent

//   constructor(private registrationService: RegistrationService, private router: Router) { }

//   register() {
//     this.registrationService.registerUser(this.user).subscribe(
//       response => {
//         // Opération réussie, redirigez l'utilisateur vers la page de login
//         this.router.navigate(['/login']); // Remplacez '/login' par le chemin de la page de login
//       },
//       error => {
//         // Gérez les erreurs ici
//       }
//     );
//   }
// }
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/shared/sevices/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  form: any = {
    username: null,
    email: null,
    password: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { username, email, password } = this.form;

    this.authService.register(username, email, password).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }
}