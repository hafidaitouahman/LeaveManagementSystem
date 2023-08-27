import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from 'src/app/shared/models/login.module';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = 'http://localhost:8080'; 
  constructor(private http: HttpClient) { }

  loginUser(user: Login): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, user);
  }
}
