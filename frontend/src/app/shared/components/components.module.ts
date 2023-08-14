import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderTopComponent } from './header-top/header-top.component';
import { SharedMaterialModule } from '../shared-material.module';
import { SidebarComponent } from './sidebar/sidebar.component';


@NgModule({
  declarations: [
    HeaderTopComponent,
    SidebarComponent
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
