import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quota } from 'src/app/shared/models/quota.module';

@Injectable({
  providedIn: 'root'
})
export class QuotaService {

  private baseUrl ='http://localhost:8080/api/leavequotas'

  // quota: Quota = new Quota();

  constructor(private http: HttpClient) {  }


  getLeaveQuotaById(quotaId: number): Observable<Quota> {
    return this.http.get<Quota>(`${this.baseUrl}/${quotaId}`);
  }
  createLeaveQuota(quota: Quota): Observable<Quota> {
    return this.http.post<Quota>(`${this.baseUrl}/create`, quota);
  }

  updateLeaveQuota(quotaId: number, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${quotaId}`, data);
  }

  getAllLeaveQuotas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/all`);
  }

  deleteLeaveQuota(quotaId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${quotaId}`, { responseType: 'text' });
  }
  // getQuota(id: number): Observable<any> {
  //   return this.http.get(`${this.baseUrl}/${id}/users`);
  // }

  // getQuotasList(): Observable<any> {
  //   return this.http.get(`${this.baseUrl}/all`);
  // }

  // deleteQuota(id: number): Observable<any> {
  //   return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  // }

  // // createQuota(quota: Object): Observable<Object> {
  // //   return this.http.post(`${this.baseUrl}/add`, quota);
  // // }
  // updateQuota(id: number, value: any): Observable<Object> {
  //   return this.http.put(`${this.baseUrl}/${id}`, value);
  // }



  // createQuota(quota: Object): Observable<Object> {
  //   return this.http.post(`${this.baseUrl}/create`, quota);
  // }


}
