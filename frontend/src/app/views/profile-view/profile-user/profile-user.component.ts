import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LeaveRequestDTOResponse } from 'src/app/shared/models/LeaveRequestDTOResponse';
import { UserQuota } from 'src/app/shared/models/UserQuota.module';
import { UserDetails } from 'src/app/shared/models/userDetails.module';
import { UserService } from '../../user-view/user.service';
import { LeaveRequestService } from '../../calendar-app/leave-request.service';
import { leaveRequestDetails } from 'src/app/shared/models/leaveRequestDetails';

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit {
  userId: number;
  userDetails!: UserDetails;
  userQuota!: UserQuota[];
  leaveRequests!: leaveRequestDetails[];

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private leaveRequestService: LeaveRequestService
  ) {
    this.userId = this.route.snapshot.params['userId'];
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.userId = +params['id'];

      // Chargez les données de l'utilisateur actuel à partir du service ou de l'API
    });
    this.userService.getUserById(this.userId).subscribe(data => {
      this.userDetails = data;
    });

    this.userService.getUserQuota(this.userId).subscribe(data => {
      if (Array.isArray(data)) {
        this.userQuota= data; // Si c'est déjà un tableau, pas besoin de modification
      } else {
        this.userQuota= [data]; // Si ce n'est pas un tableau, créez un tableau avec un seul élément
      }
    });

    this.leaveRequestService.getLeaveRequestsByUserId(this.userId).subscribe(data => {
      if (Array.isArray(data)) {
        this.leaveRequests = data; // Si c'est déjà un tableau, pas besoin de modification
      } else {
        this.leaveRequests = [data]; // Si ce n'est pas un tableau, créez un tableau avec un seul élément
      }
    });
    
}}