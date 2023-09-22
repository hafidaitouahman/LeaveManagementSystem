import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { pendingLeaveRequests } from '../models/pendingLeaveRequests.module';

@Injectable({
  providedIn: 'root',
})
export class LeaveRequestService {
  private baseUrl = 'http://localhost:8080/api/leaverequests';

  constructor(private http: HttpClient) {}

  getPendingLeaveRequestsByUserId(userId: number): Observable<pendingLeaveRequests[]> {
    return this.http.get<pendingLeaveRequests[]>(` ${this.baseUrl}/pending/${userId}`);
  }
}