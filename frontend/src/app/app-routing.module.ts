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
import { AuthenticationGuard } from './shared/guards/authentication.guard';
import { AuthorizationGuard } from './shared/guards/authorization.guard';
import { RhDashboardComponent } from './views/rh-dashboard/rh-dashboard.component';
import { NotAuthComponent } from './shared/components/not-auth/not-auth.component';
import { HolidaysComponent } from './views/holiday-view/holidays/holidays.component';
import { UserListComponent } from './views/user-view/user-list/user-list.component';
import { UserDetailsComponent } from './views/user-view/user-details/user-details.component';
import { UpdateUserComponent } from './views/user-view/update-user/update-user.component';
import { QuotaListComponent } from './views/quota-view/quota-list/quota-list.component';
import { UserDashboardComponent } from './views/user-dashboard/user-dashboard.component';
import { LeaverequestListComponent } from './views/leaveRequest-view/leaverequest-list/leaverequest-list.component';
import { ProfileUserComponent } from './views/profile-view/profile-user/profile-user.component';
const routes: Routes = [
  //{ path: '', redirectTo: 'login', pathMatch: 'full' },
  
  
  {path:'calendar', component:CalendarAppComponent},
  {path:'user',component:UserDashboardComponent , canActivate : [AuthenticationGuard], data : {role : "ROLE_USER"}  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'profile', component: ProfileComponent },
  { path :"404", component : NotAuthComponent},
  { path: 'login', component: LoginComponent },
  { path: 'holiday', component: HolidaysComponent , canActivate : [AuthenticationGuard]},
  { path: 'register', component: RegistrationComponent },
  {path:"rh",component:RhDashboardComponent , canActivate : [AuthenticationGuard],
  children : [
    { path: 'leaverequest', component: LeaverequestListComponent, canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"} },

  { path: 'departement', component: DepartementListComponent, canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"} },
  {path:'team', component:TeamListComponent ,canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"}},
  {path:'site', component:SiteListComponent,canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"}},
  { path: 'leavetypes', component: LeaveTypeListComponent ,canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"}},
  { path: 'users', component: UserListComponent ,canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"}},
  { path: 'leavequota', component: QuotaListComponent ,canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"}},



  ]},
  { path: 'rh/users/user/:id', component: UserDetailsComponent ,canActivate : [AuthorizationGuard], data : {role : "ROLE_RH"} },
  {
    path: 'user/:id/update',
    component: UpdateUserComponent
  },

  {
    path: 'profile/:id', // Utilisez un paramètre dynamique ':id' pour l'ID de l'utilisateur
    component: ProfileUserComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
