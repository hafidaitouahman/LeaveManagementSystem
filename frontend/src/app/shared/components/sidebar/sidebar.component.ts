import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SidebarService } from '../../sevices/sidebar.service';
import { StorageService } from '../../sevices/storage.service';
import { pendingLeaveRequests } from '../../models/pendingLeaveRequests.module';
import { Observable } from 'rxjs';
import { LeaveRequestService } from 'src/app/views/calendar-app/leave-request.service';
import { MatDialog } from '@angular/material/dialog';
import { LeaveRequestDetailsComponent } from 'src/app/views/calendar-app/leave-request-details/leave-request-details.component';
import { LeaveRequestDTOResponse } from '../../models/LeaveRequestDTOResponse';
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
    private sidebarService: SidebarService, private router: Router,
    private storageService: StorageService,private leaveRequestService: LeaveRequestService,public dialog: MatDialog
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
  // openLeaveRequestDetails(leaveRequestId: number) {
  //   // Utilisez l'ID de la demande de congé pour afficher ses détails ou effectuer d'autres opérations
  //   // Vous pouvez appeler getLeaveRequestById ou une autre fonction pour obtenir les détails
  //   this.leaveRequestService.getLeaveRequestById(leaveRequestId).subscribe(
  //     (response) => {
  //       // Gérez la réponse ici, par exemple, affichez les détails dans une fenêtre modale
  //       const leaveRequestDetails = response;
  //       // Affichez les détails dans une fenêtre modale ou une autre interface utilisateur
  //       console.log('Détails de la demande de congé :', leaveRequestDetails);
  //     },
  //     (error) => {
  //       // Gérez les erreurs ici, par exemple, affichez un message d'erreur.
  //       console.error('Erreur lors de la récupération de la demande de congé :', error);
  //     }
  //   );
  // }
  openLeaveRequestDetails(leaveRequestId: number): void {
    
    // Utilisez la fonction getLeaveRequestById pour récupérer les détails de la demande de congé par son ID
    this.leaveRequestService.getLeaveRequestById(leaveRequestId).subscribe(
      (leaveRequest: LeaveRequestDTOResponse) => {
        // Ouvrez une fenêtre contextuelle et transmettez les détails de la demande de congé
        const dialogRef = this.dialog.open(LeaveRequestDetailsComponent, {
          width: '400px', // Ajustez la largeur selon vos besoins
          data: leaveRequest
        });

        dialogRef.afterClosed().subscribe(result => {
          // Vous pouvez gérer les actions après la fermeture de la fenêtre contextuelle ici
        });
      },
      (error) => {
        // Gérez les erreurs ici, par exemple, affichez un message d'erreur.
        console.error('Erreur lors de la récupération de la demande de congé :', error);
      }
    );
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
