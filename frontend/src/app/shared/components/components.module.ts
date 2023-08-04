import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderTopComponent } from './header-top/header-top.component';
import { SharedMaterialModule } from '../shared-material.module';


@NgModule({
  declarations: [
    HeaderTopComponent
  ],
  imports: [
    CommonModule,
    SharedMaterialModule

  ],

  exports:[
    HeaderTopComponent
  ]
})
export class ComponentsModule { }
