import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from '../sevices/storage.service';
@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard {
  currentUser = this.storageService.getUser();

  constructor(private storageService : StorageService, private router : Router) {
  }
  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.currentUser.roles=='ROLE_RH'){
      return true;
    }else {
      this.router.navigateByUrl("/404")
      return false;
    }

  }

}