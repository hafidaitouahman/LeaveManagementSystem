import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LeaveType } from 'src/app/shared/models/leavetype.module';

@Injectable({
  providedIn: 'root'
})
export class LeaveTypeService {
  private baseUrl ='http://localhost:8080/leaveType'

  leaveType: LeaveType = new LeaveType();

  constructor(private http: HttpClient) {  }

  getLeaveType(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getLeaveTypesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  deleteLeaveType(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  createLeaveType(leaveType: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, leaveType);
  }
  updateLeaveType(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
}
