import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/shared/models/user.module'; // Import your User model
import { UserDetails } from 'src/app/shared/models/userDetails.module';
import { UpdateUserForm } from 'src/app/shared/models/UpdateUserForm.module';
import { UserQuota } from 'src/app/shared/models/UserQuota.module';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users';
  private quotaUrl = 'http://localhost:8080/api/userquota';
  constructor(private http: HttpClient) { }

  getQuotaAndResidualForCurrentUser(): Observable<UserQuota[]> {
    const url = `${this.quotaUrl}/quota`; // Remplacez '/user/${userId}/quota' par l'URL appropriée de votre API
    return this.http.get<UserQuota[]>(url);
  }
  getUserQuota(userId: number): Observable<UserQuota> {
    const url = `${this.quotaUrl}/${userId}`; // Remplacez '/user/${userId}/quota' par l'URL appropriée de votre API
    return this.http.get<UserQuota>(url);
  }
  getUserById(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${userId}`);
  }

  getUsersList(): Observable<UserDetails[]> {
    return this.http.get<UserDetails[]>(`${this.baseUrl}/all`);
  }
  getApprovers(): Observable<any> {
    return this.http.get(`${this.baseUrl}/rh`);
  }
  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, user);
  }

  // updateUser(userId: number, updateUserRequest: any): Observable<any> {
  //   const url = `${this.baseUrl}/${userId}`;
  //   return this.http.put(url, updateUserRequest);
  // }
  updateUser(userId: number, updateUserForm: UpdateUserForm): Observable<any> {
    // Effectuez une requête HTTP PUT pour mettre à jour l'utilisateur
    // Utilisez l'URL appropriée pour mettre à jour un utilisateur spécifique
    return this.http.put<any>(`${this.baseUrl}/${userId}`, updateUserForm);
  }
}
