import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from '../sevices/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard {

  constructor(private storageService : StorageService, private router : Router) {
  }
  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.storageService.isLoggedIn()) {
      return true
    } else {
      this.router.navigateByUrl("/login")
      return false;
    }
  }

}