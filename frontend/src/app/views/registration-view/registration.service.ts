import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Registration } from 'src/app/shared/models/registration.module';
@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private baseUrl = 'http://localhost:8080'; // Remplacez l'URL par l'URL de votre backend

  constructor(private http: HttpClient) { }

  registerUser(user: Registration): Observable<any> {
    return this.http.post(`${this.baseUrl}/registration`, user);
  }
}