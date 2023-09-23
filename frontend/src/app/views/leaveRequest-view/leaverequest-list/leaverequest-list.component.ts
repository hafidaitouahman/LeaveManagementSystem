import { Component, OnInit } from '@angular/core';
import { pendingLeaveRequests } from 'src/app/shared/models/pendingLeaveRequests.module';
import { LeaveRequestService } from '../../calendar-app/leave-request.service';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { LeaveRequest } from 'src/app/shared/models/leaverequest.module';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-leaverequest-list',
  templateUrl: './leaverequest-list.component.html',
  styleUrls: ['./leaverequest-list.component.scss']
})
export class LeaverequestListComponent implements OnInit {
  // leaveRequest!: Observable<pendingLeaveRequests[]>;

  pendingLeaveRequests: pendingLeaveRequests[] = [];
  pagedPendingLeaveRequests: pendingLeaveRequests[] = [];
  itemsPerPage = 2; // Définissez la valeur par défaut à 2
  currentPage = 0;
  totalItems = 0; // Ajoutez cette variable pour stocker le nombre total d'éléments

  constructor(private leaveRequestService: LeaveRequestService,private router: Router) {}

  ngOnInit(): void {
    // this.reloadData();
    // Appelez la fonction getPendingLeaveRequests lorsque le composant est initialisé
    this.leaveRequestService.getPendingLeaveRequests().subscribe((data) => {
      this.pendingLeaveRequests = data;
      this.totalItems = this.pendingLeaveRequests.length; // Mettez à jour le nombre total d'éléments
      this.onPageChange({ pageIndex: this.currentPage, pageSize: this.itemsPerPage, length: this.totalItems });
    });
    // this.reloadData();
  

  }
  // reloadData() {
  //   this.pendingLeaveRequests = this.leaveRequestService.getPendingLeaveRequests();
  // }
  onPageChange(event: PageEvent) {
    const startIndex = event.pageIndex * event.pageSize;
    const endIndex = startIndex + event.pageSize;
    this.pagedPendingLeaveRequests = this.pendingLeaveRequests.slice(startIndex, endIndex);
  }

  validateLeaveRequest(requestId: number): void {
    // Appeler le service pour valider la demande de congé avec l'ID donné
    this.leaveRequestService.validateLeaveRequest(requestId).subscribe(() => {
      // Mettre à jour la liste des demandes de congé après validation
      window.location.reload();
    });
  }

  rejectLeaveRequest(requestId: number): void {
    // Appeler le service pour refuser la demande de congé avec l'ID donné
    this.leaveRequestService.rejectLeaveRequest(requestId).subscribe(() => {
      // Mettre à jour la liste des demandes de congé après refus
      window.location.reload();
        });
  }

  cancelLeaveRequest(requestId: number): void {
    // Appeler le service pour annuler la demande de congé avec l'ID donné
    this.leaveRequestService.cancelLeaveRequest(requestId).subscribe(() => {
      // Mettre à jour la liste des demandes de congé après annulation
      window.location.reload();
        });
  }
}
