
// import { Component, Inject, OnInit } from '@angular/core';
// import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
// import { CalendarEvent } from 'angular-calendar';
// import { UntypedFormBuilder, UntypedFormControl, UntypedFormGroup } from '@angular/forms';
// import { EgretCalendarEvent } from '../../../shared/models/events/events.module';
// import { FormsModule } from '@angular/forms';

// interface DialogData {
//   event?: CalendarEvent,
//   action?: string,
//   date?: Date
// }

// @Component({
//   selector: 'app-calendar-form-dialog',
//   templateUrl: './calendar-form-dialog.component.html',
//   styleUrls: ['./calendar-form-dialog.component.css']
// })
// export class CalendarFormDialogComponent implements OnInit {
//   event!: CalendarEvent;
//   dialogTitle: string;
//   action!: string;
//   constructor(
//     public dialogRef: MatDialogRef<CalendarFormDialogComponent>,
//     @Inject(MAT_DIALOG_DATA) private data: DialogData,
//     private formBuilder: UntypedFormBuilder
//   ) {
//     this.event == data.event;
//     this.action == data.action;
    
//     if (this.action === 'edit') {
//       this.dialogTitle = this.event.title;
//     } else {
//       this.dialogTitle = 'Add Event';
//       this.event = new EgretCalendarEvent({
//         start: data.date,
//         end: data.date
//       });
//     }
//     // console.log(data);
//   }

//   ngOnInit() {
//   }

//   buildEventForm(event: EgretCalendarEvent) {
//     return new UntypedFormGroup({
//       _id: new UntypedFormControl(event._id),
//       title: new UntypedFormControl(event.title),
//       start: new UntypedFormControl(event.start),
//       end: new UntypedFormControl(event.end),
//       allDay: new UntypedFormControl(event.allDay),
    
//     });
//   }

// }
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarEvent } from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { LeaveRequest } from 'src/app/shared/models/leaverequest.module';
import { LeaveRequestService } from '../leave-request.service';
import { LeaveType } from 'src/app/shared/models/leavetype.module';
import { LeaveTypeService } from '../../leaveType-view/leave-type.service';
import { UserService } from '../../user-view/user.service';
import { Router } from '@angular/router';

const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};
@Component({
  selector: 'app-calendar-form-dialog',
  templateUrl: './calendar-form-dialog.component.html',
  styleUrls: ['./calendar-form-dialog.component.css']
})
export class CalendarFormDialogComponent {
  // conge: any = {};
  // calculatedDays: number = 0;

  // constructor(public dialogRef: MatDialogRef<CalendarFormDialogComponent>) { }

  // submitForm() {
  //   // Implémentez ici la logique pour soumettre le formulaire au serveur, si nécessaire.
  //   this.dialogRef.close();
  // }

  // closeDialog() {
  //   this.dialogRef.close();
  // }

  // updateCalculatedDays() {
  //   // Implémentez ici la logique pour calculer le nombre de jours entre les dates de début et de fin.
  //   // Par exemple :
  //   if (this.conge.startDate && this.conge.endDate) {
  //     const startDate = new Date(this.conge.startDate);
  //     const endDate = new Date(this.conge.endDate);
  //     const timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
  //     this.calculatedDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
  //   } else {
  //     this.calculatedDays = 0;
  //   }
  // }

  eventType: string = '';
  eventStart: string = '';
  eventEnd: string = '';
  leaveRequestForm: FormGroup;
  leaveTypes: any[] = [];
  approvers: any[] = [];
  replacements: any[] = [];
  constructor(private fb: FormBuilder,public modal: NgbActiveModal, private leaveRequestService: LeaveRequestService ,
    private leaveTypeService:LeaveTypeService, private userService:UserService,private router: Router,) {
    this.leaveRequestForm = this.fb.group({
      leaveTypeId: [null, Validators.required],
      approverId: [null, Validators.required],
      replacementIds: [[]], // Utilisez un tableau vide pour commencer
      startDate: [null, Validators.required],
      endDate: [null, Validators.required],
      comment: ['']
    });
  }

  ngOnInit(): void {
    this.leaveTypeService.getLeaveTypesList().subscribe((data) => {
      this.leaveTypes = data;
    });

    this.userService.getApprovers().subscribe((data) => {
      this.approvers = data;
    });

    this.userService.getUsersList().subscribe((data) => {
      this.replacements = data;
    });
  }  

  onSubmit() {
    if (this.leaveRequestForm.valid) {
      const leaveRequestData = this.leaveRequestForm.value as LeaveRequest;
      this.leaveRequestService.createLeaveRequest(leaveRequestData).subscribe(
        (response) => {
            this.gotoList();
        },
        (error) => {
          // Gérez les erreurs ici, par exemple, affichez un message d'erreur.
        }
      );
    }
  }
  gotoList() {
    this.router.navigate(['/user']);
  }
  createEvent() {
    // Validate the input and create a new event
    const newEvent: CalendarEvent = {
      title: this.eventType,
      start: new Date(this.eventStart),
      end: new Date(this.eventEnd),

      color: colors['blue'], // You can use any color you want here
      draggable: true,
      resizable: {
        beforeStart: true,
        afterEnd: true,
      },
    };

    // Close the modal and pass the newly created event back to the parent component
    this.modal.close(newEvent);
  }
}
