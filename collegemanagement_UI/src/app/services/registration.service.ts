import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../entities/user';


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {


  baseUrl="http://localhost:8081/collegemanagement/user";
  constructor(private httpClient:HttpClient) { }

registerUser(user:User):Observable<any>{
  console.log(user);
  return this.httpClient.post(`${this.baseUrl}`,user);
}
}





