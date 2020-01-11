import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '../../node_modules/@angular/router';
import { Observable} from 'rxjs';
import { WatchList } from './watchlist';

@Injectable({
  providedIn: 'root'
})
export class WatchlistService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  allmovies(): Observable<WatchList[]> {
    return this.httpClient.get('http://localhost:8080/watchlist/all') as Observable<WatchList[]>;
  }

  randommovie(): Observable<WatchList> {
    return this.httpClient.get('http://localhost:8080/watchlist/random/') as Observable<WatchList>;
  }

  allmoviesfromoneuser(userid: any): Observable<WatchList[]> {
    return this.httpClient.get('http://localhost:8080/watchlist/userall/' + userid) as Observable<WatchList[]>;
  }
}
