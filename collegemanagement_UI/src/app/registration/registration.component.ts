import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../entities/user';
import { RegistrationService } from '../services/registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private registrationService: RegistrationService,private router : Router) { }
  cpassword:any

  ngOnInit(): void {
  }

  user: User = new User();
  registerUser() {
    if (this.user != undefined) {
      this.registrationService.registerUser(this.user).subscribe(data => {
        if (data != undefined) {
          alert("User created successsfully");
          this.user = new User();
          this.router.navigate(['login']);
        } else {
          alert("Failed to craete registration " + this.user.fname);
        }
      }, error => alert("Server error : Failed to craete user"));
    }
  }


}
