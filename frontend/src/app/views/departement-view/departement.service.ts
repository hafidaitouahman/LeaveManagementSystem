import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Departement } from 'src/app/shared/models/departement.module';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

  private baseUrl ='http://localhost:8080/departement'

  departement: Departement = new Departement();

  constructor(private http: HttpClient) {  }

  getDepartement(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getDepartementsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  deleteDepartement(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  createDepartement(departement: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, departement);
  }
  updateDepartement(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
}
