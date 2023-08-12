import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LeaveTypeService } from '../leave-type.service';
import {LeaveType} from '../../../shared/models/leavetype.module'
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-create-leave-type',
  templateUrl: './create-leave-type.component.html',
  styleUrls: ['./create-leave-type.component.css']
})
export class CreateLeaveTypeComponent implements OnInit {
  leaveType: LeaveType = new LeaveType();
  submitted = false;

  constructor(private leaveTypeService: LeaveTypeService,
    private router: Router,public modal: NgbActiveModal) { }

  ngOnInit() {
  }

  newLeaveType(): void {
    this.submitted = false;
    this.leaveType = new LeaveType();
  }

  save() {
    this.leaveTypeService
    .createLeaveType(this.leaveType).subscribe(data => {
      console.log(data)
      this.leaveType = new LeaveType();
      this.modal.close(this.leaveType); // Ferme la boîte de dialogue
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
   

  }
 
  gotoList() {
    this.router.navigate(['/leavetypes']);
  }
}
