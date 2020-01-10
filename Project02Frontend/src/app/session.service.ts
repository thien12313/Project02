import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '../../node_modules/@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private httpClient: HttpClient) { }

  login(username, password) {
    const headers = {
      headers: new HttpHeaders({
        'Content-Type':  'application/x-www-form-urlencoded',
      })
    };
    const body = `username=${username}&password=${password}`;
    console.log(body);
    this.httpClient.post('http://localhost:8080/login', body, headers)
    .subscribe( (data: any) => {
      if (data) {
        console.log(data);
      } else {
        alert('Invalid Credentials');
      }
    });
  }
}
