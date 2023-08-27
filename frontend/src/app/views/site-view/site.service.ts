import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Site } from 'src/app/shared/models/site.module';

@Injectable({
  providedIn: 'root'
})
export class SiteService {

  private baseUrl ='http://localhost:8080/api/site'

  site: Site = new Site();

  constructor(private http: HttpClient) {  }

  getSite(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getSitesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  deleteSite(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  createSite(site: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, site);
  }
  updateSite(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
}
