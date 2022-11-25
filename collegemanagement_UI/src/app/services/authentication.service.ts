import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../entities/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  baseUrl = "http://localhost:8081/collegemanagement/";
  constructor(private httpClient: HttpClient) { }

  authenticate(user: User): Observable<any> {
    console.log(user);
      return this.httpClient.post(`${this.baseUrl}` + "login", user);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}