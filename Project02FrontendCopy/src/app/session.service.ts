import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '../../node_modules/@angular/router';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  user: User;

  login(username, password) {
    const headers = {
      headers: new HttpHeaders({
        'Content-Type':  'application/x-www-form-urlencoded',
      })
    };
    const body = `username=${username}&password=${password}`;
    console.log(body);
    this.httpClient.post('http://localhost:8080/login', body, headers)
    .subscribe( (user) => {
      if (user != null) {
        console.log(user);
        sessionStorage.setItem('user', JSON.stringify(user));
        this.router.navigateByUrl('homepage');
      } else {
        alert('Invalid Credentials');
      }
    });
  }

  logout() {
    this.httpClient.get('http://localhost:8080/logout');
    this.router.navigateByUrl('login');
  }
}
