import { Component, OnInit, Input } from '@angular/core';

import { SessionService } from '../session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() username: string;
  @Input() password: string;
  invalidLogin: boolean = false;

  constructor(private sessionService: SessionService) { }

  submit() {
    this.sessionService.login(this.username, this.password);
  }

  ngOnInit() {
  }

}
