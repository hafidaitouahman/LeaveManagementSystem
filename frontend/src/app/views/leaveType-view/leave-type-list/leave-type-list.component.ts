import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { LeaveTypeService } from '../leave-type.service';
import {LeaveType} from '../../../shared/models/leavetype.module'
import { Router } from '@angular/router';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
import { CreateLeaveTypeComponent } from '../create-leave-type/create-leave-type.component';
import { UpdateLeaveTypeComponent } from '../update-leave-type/update-leave-type.component';
@Component({
  selector: 'app-leave-type-list',
  templateUrl: './leave-type-list.component.html',
  styleUrls: ['./leave-type-list.component.css']
})
export class LeaveTypeListComponent implements OnInit {
  leavetypes!: Observable<LeaveType[]>;
  constructor(private leaveTypeService: LeaveTypeService,
    private router: Router,private modal: NgbModal) {}

  ngOnInit() {
    this.reloadData();
  }
  

  reloadData() {
    this.leavetypes = this.leaveTypeService.getLeaveTypesList();
  }

  deleteLeaveType(id: number) {
    this.leaveTypeService.deleteLeaveType(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  openCreateEventModal(): void {
    const modalRef = this.modal.open(CreateLeaveTypeComponent);
    modalRef.closed.subscribe(result => {
      this.reloadData(); // Recharge les données après la fermeture du popup
    });

  }
  openUpdateEventModal(id: number): void {
    
    const modalRef = this.modal.open(UpdateLeaveTypeComponent);
    modalRef.componentInstance.id = id; // Pass the id to the modal component
  
    modalRef.closed.subscribe(result => {
      this.reloadData();
    });


  }
  
}
