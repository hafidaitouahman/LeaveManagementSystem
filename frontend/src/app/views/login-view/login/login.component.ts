// import { Component } from '@angular/core';
// import { Router } from '@angular/router';
// import { LoginService } from '../login.service';
// import { Login } from 'src/app/shared/models/login.module';
// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent {
//   user: Login = new Login(); // Assurez-vous que le nom et le type correspondent

//   constructor(private loginService: LoginService, private router: Router) { }

//   login() {
//     this.loginService.loginUser(this.user).subscribe(
//       response => {
//         // Opération réussie, redirigez l'utilisateur
//         this.router.navigate(['/calendar ']); // Remplacez '/home' par le chemin de la page d'accueil
//       },
//       error => {
//         // Gérez les erreurs ici
//       }
//     );
//   }
// }
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/shared/sevices/auth.service';
import { StorageService } from 'src/app/shared/sevices/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private storageService: StorageService) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe({
      next: data => {
        this.storageService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.storageService.getUser().roles;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }
}