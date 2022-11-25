import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Department } from '../entities/department';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {


  baseUrl = "http://localhost:8081/collegemanagement/department";
  constructor(private httpClient: HttpClient) { }

  // registerUser(department: Department): Observable<object> {
  //   console.log(department);
  //   return this.httpClient.post(`${this.baseUrl}`, department);
  // }

  
  retrieveAllDepartments(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}`);
  }
  createDepartMent(department: Department): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}`, department);
  }

  editDepartment(department: Department): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}`+"/"+department.departmentId, department);
  }

  deleteDepartment(id: String): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}`+"/"+id,  {responseType: 'text'});
  }
  
}


