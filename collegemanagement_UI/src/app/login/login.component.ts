import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../entities/user';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  invalidLogin = false
  constructor(private router: Router,
    private loginservice: AuthenticationService) { }

  ngOnInit(): void {
  }

  user: User = new User();
  login() {
    this.loginservice.authenticate(this.user).subscribe(data => {
      if (data != undefined && data == true) {
        sessionStorage.setItem('username', this.user.email)
        this.router.navigate(['/home'])
        this.invalidLogin = false
      } else
        this.invalidLogin = true
    });
  }

}
