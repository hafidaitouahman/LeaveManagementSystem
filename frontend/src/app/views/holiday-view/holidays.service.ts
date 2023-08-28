import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HolidaysService {
  private apiURL = 'http://localhost:8080/api/holidays';

  constructor(private http: HttpClient) {}

  getHolidays(): Observable<any[]> {
    return this.http.get<any[]>(this.apiURL);
  }
}
