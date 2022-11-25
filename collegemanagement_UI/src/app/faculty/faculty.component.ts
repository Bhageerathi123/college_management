import { AfterViewInit,Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';
import { Faculty } from '../entities/faculty';
import { AuthenticationService } from '../services/authentication.service';
import { FacultyService } from '../services/faculty.service';

 
@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.css']
})
export class FacultyComponent implements OnInit, AfterViewInit{
  
  departments: Faculty[] = [];
  faculty: Faculty = new Faculty();
  constructor(private departmentService: FacultyService,private authService: AuthenticationService, private router: Router) {
  }
  ngOnInit(): void {
    this.retrieveAllDepartments();
    if(!this.authService.isUserLoggedIn()){
      this.router.navigate(['login']);
    }

  }

  dataSource!: MatTableDataSource<Faculty>;

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  ngAfterViewInit() {

  }

  craeteDepartment() {
    if (this.faculty != undefined  && this.faculty.facultyId == undefined) {
      this.departmentService.createDepartMent(this.faculty).subscribe(data => {
        if (data != undefined) {
          this.closePopup();
          alert("Faculty created successsfully");
          this.faculty = new Faculty();
          this.retrieveAllDepartments();
        } else {
          alert("Failed to craete faculty " + this.faculty.facultyName);
        }
      }, error => alert("Server error : Failed to craete faculty"));
    }else {
      this.updateDepartment();
    }
  }

  displayedColumns: string[] = ['facultyId', 'facultyName',  'contactEmail', 'contactPhone', 'creationDateTime', 'updatedDateTime', 'actions'];

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

  editDepartmentModal(faculty: Faculty) {
    console.log("edit ", faculty)
    this.openPopup()
    this.faculty = faculty;
  }

  updateDepartment() {
    if (this.faculty != undefined && this.faculty.facultyId != undefined) {
      this.departmentService.editDepartment(this.faculty).subscribe(data => {
        console.log(data);
        if (data != undefined) {
          this.faculty = new Faculty();
          this.closePopup();
          alert("Faculty updated successsfully");
          this.retrieveAllDepartments();
        } else {
          alert("Failed to craete faculty " + this.faculty.facultyName);
        }
      }, error => alert("Server error : Failed to update faculty"));
    }
  }

  deleteDepartment(faculty: Faculty) {
    this.departmentService.deleteDepartment(faculty.facultyId).subscribe(data => {
        alert("Faculty deleted successfully");
        this.retrieveAllDepartments();
    }, error => alert("Server error : Failed to delete faculty"));
  }

}

