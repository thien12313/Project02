import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from '../session.service';
import { User } from '../user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router, private sessionService: SessionService) { }

  user: User;
  isloggedin: boolean = false;

  ngOnInit() {
    this.checkifloggedin();
  }

  checkifloggedin() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    console.log(this.user);
    if (this.user == null) {
      this.isloggedin = true;
    } else {
      this.isloggedin = false;
    }
  }

  logout() {
    sessionStorage.clear();
    this.sessionService.logout();
  }

  login() {
    this.router.navigateByUrl('login');
  }
}
