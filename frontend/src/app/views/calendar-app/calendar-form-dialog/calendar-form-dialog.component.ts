
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
import { MatDialogRef } from '@angular/material/dialog';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarEvent } from 'angular-calendar';
import { EventColor } from 'calendar-utils';

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
  constructor(public modal: NgbActiveModal) {}

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
