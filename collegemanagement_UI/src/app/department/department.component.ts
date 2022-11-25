import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Department } from '../entities/department';
import { AuthenticationService } from '../services/authentication.service';
import { DepartmentService } from '../services/department.service';


@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit, AfterViewInit {

  departments: Department[] = [];
  department: Department = new Department();
  constructor(private departmentService: DepartmentService,private authService: AuthenticationService, private router: Router) {
  }
  ngOnInit(): void {
    if(!this.authService.isUserLoggedIn()){
      this.router.navigate(['login']);
    }
    this.retrieveAllDepartments();

  }

  // displayedColumns: string[] = ['departmentId', 'departmentName', 'hod','contactEmail','contactPhone','updatedDateTime', 'creationDateTime'];
  dataSource!: MatTableDataSource<Department>;

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  ngAfterViewInit() {

  }

  craeteDepartment() {
    if (this.department != undefined  && this.department.departmentId == undefined) {
      this.departmentService.createDepartMent(this.department).subscribe(data => {
        if (data != undefined) {
          this.closePopup();
          alert("Department created successsfully");
          this.department = new Department();
          this.retrieveAllDepartments();
        } else {
          alert("Failed to craete department " + this.department.departmentName);
        }
      }, error => alert("Server error : Failed to craete department"));
    }else {
      this.updateDepartment();
    }
  }

  displayedColumns: string[] = ['departmentId', 'departmentName', 'hod', 'contactEmail', 'contactPhone', 'creationDateTime', 'updatedDateTime', 'actions'];

  retrieveAllDepartments() {
    this.departmentService.retrieveAllDepartments().subscribe(data => {
      if (data != undefined) {
        this.departments = data;
        this.dataSource = new MatTableDataSource(this.departments);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  displayStyle = "none";

  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  editDepartmentModal(department: Department) {
    console.log("edit ", department)
    this.openPopup()
    this.department = department;
  }

  updateDepartment() {
    if (this.department != undefined && this.department.departmentId != undefined) {
      this.departmentService.editDepartment(this.department).subscribe(data => {
        console.log(data);
        if (data != undefined) {
          this.department = new Department();
          this.closePopup();
          alert("Department updated successsfully");
          this.retrieveAllDepartments();
        } else {
          alert("Failed to craete department " + this.department.departmentName);
        }
      }, error => alert("Server error : Failed to update department"));
    }
  }

  deleteDepartment(department: Department) {
    console.log("delete ", department)
    this.departmentService.deleteDepartment(department.departmentId).subscribe(data => {
        alert("Department deleted successfully");
        console.log(data);
        this.retrieveAllDepartments();
    }, error => alert("Server error : Failed to delete department"));
  }

}
