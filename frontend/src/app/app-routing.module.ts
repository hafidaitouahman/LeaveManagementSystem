import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LeaveTypeListComponent } from './views/leaveType-view/leave-type-list/leave-type-list.component';
import { UpdateLeaveTypeComponent } from './views/leaveType-view/update-leave-type/update-leave-type.component';
const routes: Routes = [

  { path: 'leavetypes', component: LeaveTypeListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
