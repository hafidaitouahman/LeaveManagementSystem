import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LeaveRequestDTOResponse } from 'src/app/shared/models/LeaveRequestDTOResponse';
import { leaveRequestDetails } from 'src/app/shared/models/leaveRequestDetails';
import { LeaveRequest } from 'src/app/shared/models/leaverequest.module';
import { pendingLeaveRequests } from 'src/app/shared/models/pendingLeaveRequests.module';

@Component({
  selector: 'app-leave-request-details',
  templateUrl: './leave-request-details.component.html',
  styleUrls: ['./leave-request-details.component.css']
})
export class LeaveRequestDetailsComponent {
  leaveRequest!: leaveRequestDetails; // Déclaration de la propriété leaveRequest

  constructor(
    public dialogRef: MatDialogRef<LeaveRequestDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: leaveRequestDetails
  ) {}

  closeDialog(): void {
    this.dialogRef.close();
  }
}
