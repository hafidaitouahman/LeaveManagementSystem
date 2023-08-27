import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LeaveTypeListComponent } from './views/leaveType-view/leave-type-list/leave-type-list.component';
import { DepartementListComponent } from './views/departement-view/departement-list/departement-list.component';
import { TeamListComponent } from './views/team-view/team-list/team-list.component';
import { AppComponent } from './app.component';
import { SiteListComponent } from './views/site-view/site-list/site-list.component';
import { CalendarAppComponent } from './views/calendar-app/calendar-app.component';
import { LoginComponent } from './views/login-view/login/login.component';
import { RegistrationComponent } from './views/registration-view/registration/registration.component';
import { ProfileComponent } from './views/profile-view/profile/profile.component';
const routes: Routes = [
  //{ path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '', component: LeaveTypeListComponent },
  { path: 'departement', component: DepartementListComponent },
  {path:'team', component:TeamListComponent},
  {path:'site', component:SiteListComponent},
  {path:'calendar', component:CalendarAppComponent},
  { path: 'leavetypes', component: LeaveTypeListComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'profile', component: ProfileComponent },

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
