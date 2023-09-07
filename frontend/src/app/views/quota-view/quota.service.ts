import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quota } from 'src/app/shared/models/quota.module';

@Injectable({
  providedIn: 'root'
})
export class QuotaService {

  private baseUrl ='http://localhost:8080/api/leavequotas'

  quota: Quota = new Quota();

  constructor(private http: HttpClient) {  }

  getQuota(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/users`);
  }

  getQuotasList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  deleteQuota(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  // createQuota(quota: Object): Observable<Object> {
  //   return this.http.post(`${this.baseUrl}/add`, quota);
  // }
  updateQuota(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }




  createQuota(quotaData: any, queryParams: string): Observable<any> {
    // Faites une requête HTTP POST pour créer une quota
    return this.http.post<any>(`${this.baseUrl}${queryParams}`, quotaData);
  }

}
