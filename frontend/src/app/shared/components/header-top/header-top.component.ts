import { Component, OnInit, Input, OnDestroy, Renderer2 } from '@angular/core';
import { Subscription } from 'rxjs';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faBell } from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'app-header-top',
  templateUrl: './header-top.component.html',
  styleUrls: ['./header-top.component.css']

})
export class HeaderTopComponent implements OnInit, OnDestroy {
  faBell = faBell;
  layoutConf: any;
  menuItems:any;
  egretThemes: any[] = [];
  currentLang = 'en';
  availableLangs = [{
    name: 'English',
    code: 'en',
  }, {
    name: 'Spanish',
    code: 'es',
  }]


  ngOnInit() {
  
  }
  ngOnDestroy() {
  }
  setLang() {
  }

}
