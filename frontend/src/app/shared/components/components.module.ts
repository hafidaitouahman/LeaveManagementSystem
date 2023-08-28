import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderTopComponent } from './header-top/header-top.component';
import { SharedMaterialModule } from '../shared-material.module';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NotAuthComponent } from './not-auth/not-auth.component';


@NgModule({
  declarations: [
    HeaderTopComponent,
    SidebarComponent,
    NotAuthComponent
  ],
  imports: [
    CommonModule,
    SharedMaterialModule

  ],

  exports:[
    HeaderTopComponent,
    SidebarComponent
  ]
})
export class ComponentsModule { }
