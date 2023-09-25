import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LeaveRequestDTOResponse } from 'src/app/shared/models/LeaveRequestDTOResponse';
import { leaveRequestDetails } from 'src/app/shared/models/leaveRequestDetails';
import { LeaveRequest } from 'src/app/shared/models/leaverequest.module';
import { pendingLeaveRequests } from 'src/app/shared/models/pendingLeaveRequests.module';
import { LeaveRequestService } from '../leave-request.service';
import { ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-leave-request-details',
  templateUrl: './leave-request-details.component.html',
  styleUrls: ['./leave-request-details.component.css']
})
export class LeaveRequestDetailsComponent{
  leaveRequest!: leaveRequestDetails; // Déclaration de la propriété leaveRequest
  userId!: number;
  // startDate!: Date;
  // endDate!: Date;
  constructor(
    public dialogRef: MatDialogRef<LeaveRequestDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: leaveRequestDetails,
    private datePipe: DatePipe // Injectez DatePipe ici

  ) {
    this.leaveRequest = data; // Initialisez leaveRequest avec les données transmises
    // this.startDate = new Date(this.leaveRequest.startDate);
    // this.endDate = new Date(this.leaveRequest.endDate);
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
