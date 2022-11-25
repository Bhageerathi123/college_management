
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Student } from '../entities/student';
import { AuthenticationService } from '../services/authentication.service';
import { StudentService } from '../services/student.service';


@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit, AfterViewInit {

  departments: Student[] = [];
  student: Student = new Student();

  ngOnInit(): void {
    if (!this.authService.isUserLoggedIn()) {
      this.router.navigate(['login']);
    }
    this.retrieveAllDepartments();

  }
  constructor(private departmentService: StudentService, private authService: AuthenticationService, private router: Router) {
  }
  dataSource!: MatTableDataSource<Student>;

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  ngAfterViewInit() {

  }

  craeteDepartment() {
    if (this.student != undefined && this.student.studentId == undefined) {
      this.departmentService.createDepartMent(this.student).subscribe(data => {
        console.log(data);
        if (data != undefined) {
          this.closePopup();
          alert("Student created successsfully");
          this.student = new Student();
          this.retrieveAllDepartments();
        } else {
          alert("Failed to craete student " + this.student.studentfName);
        }
      }, error => alert("Server error : Failed to craete student"));
    } else {
      this.updateDepartment();
    }
  }

  displayedColumns: string[] = ['studentId', 'studentfName', 'studentlName', 'contactEmail', 'contactPhone', 'creationDateTime', 'updatedDateTime', 'actions'];

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

  editDepartmentModal(student: Student) {
    console.log("edit ", student)
    this.openPopup()
    this.student = student;
  }

  updateDepartment() {
    if (this.student != undefined && this.student.studentId != undefined) {
      this.departmentService.editDepartment(this.student).subscribe(data => {
        console.log(data);
        if (data != undefined) {
          this.student = new Student();
          this.closePopup();
          alert("Student updated successsfully");
          this.retrieveAllDepartments();
        } else {
          alert("Failed to craete student " + this.student.studentfName);
        }
      }, error => alert("Server error : Failed to update student"));
    }
  }

  deleteDepartment(student: Student) {
    console.log("delete ", student)
    this.departmentService.deleteDepartment(student.studentId).subscribe(data => {
      alert("Student deleted successfully");
      this.retrieveAllDepartments();
    }, error => alert("Server error : Failed to delete student"));
  }

}



