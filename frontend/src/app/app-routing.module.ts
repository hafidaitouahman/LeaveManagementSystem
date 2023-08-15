import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LeaveTypeListComponent } from './views/leaveType-view/leave-type-list/leave-type-list.component';
import { DepartementListComponent } from './views/departement-view/departement-list/departement-list.component';
import { TeamListComponent } from './views/team-view/team-list/team-list.component';
import { AppComponent } from './app.component';
import { SiteListComponent } from './views/site-view/site-list/site-list.component';
const routes: Routes = [
  { path: '', component: LeaveTypeListComponent },
  { path: 'leavetypes', component: LeaveTypeListComponent },
  { path: 'departement', component: DepartementListComponent },
  {path:'team', component:TeamListComponent},
  {path:'site', component:SiteListComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
