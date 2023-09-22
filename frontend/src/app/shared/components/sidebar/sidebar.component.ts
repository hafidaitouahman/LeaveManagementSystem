import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SidebarService } from '../../sevices/sidebar.service';
import { StorageService } from '../../sevices/storage.service';
import { pendingLeaveRequests } from '../../models/pendingLeaveRequests.module';
import { Observable } from 'rxjs';
import { LeaveRequestService } from '../../sevices/leaverequest.service';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  sidebarState!: string;
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  username?: string;
  currentUser: any;
  userId!: number; // L'ID de l'utilisateur authentifié
  leaveRequests!: pendingLeaveRequests[];

  constructor(
    private sidebarService: SidebarService, private router: Router,private storageService: StorageService,private leaveRequestService: LeaveRequestService
  ) { 
    

  }

  ngOnInit() {
    this.sidebarService.sidebarStateObservable$.
      subscribe((newState: string) => {
        this.sidebarState = newState;
      });

      this.currentUser = this.storageService.getUser();

      this.isLoggedIn = this.storageService.isLoggedIn();
  
      if (this.isLoggedIn) {
        const user = this.storageService.getUser();
        this.roles = user.roles;
  
        this.showAdminBoard = this.roles.includes('ROLE_RH');
        this.showUserBoard = this.roles.includes('ROLE_USER');
  
        this.username = user.username;
      }

      this.userId = this.currentUser.id;
      this.loadPendingLeaveRequests();


  }
  loadPendingLeaveRequests(): void {
    this.leaveRequestService.getPendingLeaveRequestsByUserId(this.userId)
      .subscribe(data => {
        this.leaveRequests = data;
      });
  }
  gotoDepartement(){
    this.router.navigate(['/rh/departement']);
  }
  gotoTeam(){
    this.router.navigate(['/rh/team']);
  }
  gotoSite(){
    this.router.navigate(['/rh/site']);
  }
  gotoLeaveType(){
    this.router.navigate(['/rh/leavetypes']);
  }
}
