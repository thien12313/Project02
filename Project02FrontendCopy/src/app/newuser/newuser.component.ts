import { Component, OnInit, Input } from '@angular/core';
import { SessionService } from '../session.service';

@Component({
  selector: 'app-newuser',
  templateUrl: './newuser.component.html',
  styleUrls: ['./newuser.component.css']
})
export class NewuserComponent implements OnInit {

  constructor(private sessionService: SessionService) { }

  @Input() username: string;
  @Input() password: string;
  @Input() passwordagain: string;
  @Input() fullname: string;
  @Input() aboutme: string;
  passwordcheck: boolean = false;
  ngOnInit() {
  }

  submit() {
    if (this.password == this.passwordagain) {
      this.sessionService.create(this.username, this.password, this.fullname, this.aboutme);
    } else {
      this.passwordcheck = true;
    }
  }
}
