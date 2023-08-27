import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SidebarService } from '../../sevices/sidebar.service';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  sidebarState!: string;


  constructor(
    private sidebarService: SidebarService, private router: Router
  ) { }

  ngOnInit() {
    this.sidebarService.sidebarStateObservable$.
      subscribe((newState: string) => {
        this.sidebarState = newState;
      });
  }

  gotoDepartement(){
    this.router.navigate(['departement']);
  }
  gotoTeam(){
    this.router.navigate(['team']);
  }
  gotoSite(){
    this.router.navigate(['site']);
  }
  gotoLeaveType(){
    this.router.navigate(['leavetypes']);
  }
}
