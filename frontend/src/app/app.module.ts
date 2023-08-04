import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { SharedModule } from './shared/shared.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CalendarAppComponent } from './views/calendar-app/calendar-app.component';
import { SidenavBarComponent } from './views/sidenav-bar/sidenav-bar.component'
import { FormsModule } from '@angular/forms';
import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarFormDialogComponent } from './views/calendar-app/calendar-form-dialog/calendar-form-dialog.component';
import { SharedMaterialModule } from './shared/shared-material.module';
@NgModule({
  declarations: [
    AppComponent,
    CalendarAppComponent,
    SidenavBarComponent,
    CalendarFormDialogComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    SharedModule,
    FontAwesomeModule,
    SharedMaterialModule,
    FormsModule,
    NgbModalModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
