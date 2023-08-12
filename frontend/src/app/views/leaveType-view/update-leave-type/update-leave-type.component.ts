import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LeaveTypeService } from '../leave-type.service';
import { LeaveType } from 'src/app/shared/models/leavetype.module';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'

@Component({
  selector: 'app-update-leave-type',
  templateUrl: './update-leave-type.component.html',
  styleUrls: ['./update-leave-type.component.css']
})
export class UpdateLeaveTypeComponent implements OnInit {

  id!: number;
  leaveType!: LeaveType;

  constructor(private route: ActivatedRoute,private router: Router,
    private leaveTypeService: LeaveTypeService,public modal: NgbActiveModal) { }

  ngOnInit() {
    this.leaveType = new LeaveType();

    this.id = this.route.snapshot.params['id'];
    
    this.leaveTypeService.getLeaveType(this.id)
      .subscribe(data => {
        console.log(data)
        this.leaveType = data;
      }, error => console.log(error));
  }

  updateLeaveType() {
    this.leaveTypeService.updateLeaveType(this.id, this.leaveType)
      .subscribe(data => {
        console.log(data);
        this.leaveType = new LeaveType();
        this.modal.close(this.leaveType); // Ferme la boîte de dialogue
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateLeaveType();    
  }

  gotoList() {
    this.router.navigate(['/leavetypes']);
  }
}