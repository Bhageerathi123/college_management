import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Faculty } from '../entities/faculty';


@Injectable({
  providedIn: 'root'
})
export class FacultyService {


  baseUrl = "http://localhost:8081/collegemanagement/faculty";
  constructor(private httpClient: HttpClient) { }

  retrieveAllDepartments(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}`);
  }
  createDepartMent(faculty: Faculty): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}`, faculty);
  }

  editDepartment(faculty: Faculty): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}` + "/" + faculty.facultyId, faculty);
  }

  deleteDepartment(id: String): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}` + "/" + id, { responseType: 'text' });
  }

}




