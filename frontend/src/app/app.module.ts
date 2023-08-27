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
import { CreateOrganisationComponent } from './views/organisation-view/create-organisation/create-organisation.component';
import { OrganisationDetailsComponent } from './views/organisation-view/organisation-details/organisation-details.component';
import { OrganisationListComponent } from './views/organisation-view/organisation-list/organisation-list.component';
import { CreateLeaveTypeComponent } from './views/leaveType-view/create-leave-type/create-leave-type.component';
import { LeaveTypeListComponent } from './views/leaveType-view/leave-type-list/leave-type-list.component';
import {HttpClientModule} from '@angular/common/http';
import { UpdateLeaveTypeComponent } from './views/leaveType-view/update-leave-type/update-leave-type.component'
import { SidebarComponent } from './shared/components/sidebar/sidebar.component';
import { SidebarService } from './shared/sevices/sidebar.service';
import { CreateDepartementComponent } from './views/departement-view/create-departement/create-departement.component';
import { UpdateDepartementComponent } from './views/departement-view/update-departement/update-departement.component';
import { DepartementListComponent } from './views/departement-view/departement-list/departement-list.component';
import { CreateTeamComponent } from './views/team-view/create-team/create-team.component';
import { UpdateTeamComponent } from './views/team-view/update-team/update-team.component';
import { TeamListComponent } from './views/team-view/team-list/team-list.component';
import { CreateSiteComponent } from './views/site-view/create-site/create-site.component';
import { UpdateSiteComponent } from './views/site-view/update-site/update-site.component';
import { SiteListComponent } from './views/site-view/site-list/site-list.component';
import { LoginComponent } from './views/login-view/login/login.component';
import { RegistrationComponent } from './views/registration-view/registration/registration.component';
import { httpInterceptorProviders } from './shared/helpers/http.interceptor';
import { ProfileComponent } from './views/profile-view/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    CalendarAppComponent,
    SidenavBarComponent,
    CalendarFormDialogComponent,
    CreateOrganisationComponent,
    OrganisationDetailsComponent,
    OrganisationListComponent,
    CreateLeaveTypeComponent,
    LeaveTypeListComponent,
    UpdateLeaveTypeComponent,
    CreateDepartementComponent,
    UpdateDepartementComponent,
    DepartementListComponent,
    CreateTeamComponent,
    UpdateTeamComponent,
    TeamListComponent,
    CreateSiteComponent,
    UpdateSiteComponent,
    SiteListComponent,
    LoginComponent,
    RegistrationComponent,
    ProfileComponent

  ],
  imports: [
    HttpClientModule,
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
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
