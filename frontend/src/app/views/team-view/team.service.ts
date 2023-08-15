import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Team } from 'src/app/shared/models/team.module';
@Injectable({
  providedIn: 'root'
})
export class TeamService {

  private baseUrl ='http://localhost:8080/team'

  team: Team = new Team();

  constructor(private http: HttpClient) {  }

  getTeam(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getTeamsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  deleteTeam(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  createTeam(team: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, team);
  }
  updateTeam(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }}
