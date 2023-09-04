import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from 'src/app/shared/models/user.module';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users!: User[]; // Define the User array
  selectedUser!: User; // To store the selected user for details view

  constructor(private userService: UserService,private router: Router, private route: ActivatedRoute) { } // Inject your user service

  ngOnInit(): void {
    this.loadUsers(); // Load users when the component initializes
  }

  loadUsers() {
    // Call a service method to get the list of users from your API
    this.userService.getUsersList().subscribe(users => {
      this.users = users;
    });
  }

  viewUserDetails(userId: number) {
    // Naviguer vers la page de détails de l'utilisateur en utilisant une route relative
    this.router.navigate(['user', userId], { relativeTo: this.route });
  }



  updateUser(userId: number) {
    // Navigate to the user update page with the user's ID as a route parameter
    this.router.navigate(['user', userId, 'update']);
  }
}
