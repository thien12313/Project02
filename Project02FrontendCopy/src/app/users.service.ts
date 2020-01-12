import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '../../node_modules/@angular/router';
import { Observable} from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  allusers(): Observable<User[]> {
    return this.httpClient.get('http://localhost:8088/allusers') as Observable<User[]>;
  }

}
