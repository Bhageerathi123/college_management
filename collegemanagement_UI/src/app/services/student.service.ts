import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../entities/student';


@Injectable({
  providedIn: 'root'
})
export class StudentService {

  baseUrl = "http://localhost:8081/collegemanagement/student";
  constructor(private httpClient: HttpClient) { }

  retrieveAllDepartments(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}`);
  }
  createDepartMent(faculty: Student): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}`, faculty);
  }

  editDepartment(faculty: Student): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}` + "/" + faculty.studentId, faculty);
  }

  deleteDepartment(id: String): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}` + "/" + id, { responseType: 'text' });
  }

}



