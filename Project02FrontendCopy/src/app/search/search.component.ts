import { Component, OnInit, Input } from '@angular/core';
import { WatchList } from '../watchlist';
import { Console } from '@angular/core/src/console';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from '../movie';
import { WatchlistService } from '../watchlist.service';
import { Title } from '@angular/platform-browser';
import { User } from '../user';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  postResult: Object;

  constructor(private httpClient: HttpClient, private watchListService: WatchlistService) { }

  watchlist: WatchList;
  movies: Movie;
  movie: Movie;
  user: User;
  searched: boolean = false;
  @Input() moviename: string;
  @Input() rating: number;
  @Input() review: string;
  
  ngOnInit( ) {
  }

  find() {
    this.searched = true;
    this.httpClient.get('http://localhost:8080/watchlist/movies/' + this.moviename).subscribe( (data: any) => {
        if (data) {
          this.movies = data;
          sessionStorage.setItem('movie', JSON.stringify(this.movies));
        }
    });
  }

  submit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.movie = JSON.parse(sessionStorage.getItem('movie'));
    const headers = {
      headers: new HttpHeaders({
        'Content-Type':  'application/x-www-form-urlencoded',
      })
    };
    const body = `moviename=${this.movie.Title}&movieyear=${this.movie.Year}&rating=${this.rating}
    &review=${this.review}&imageurl=${this.movie.Poster}&userid=${this.user.userid}
    &username=${this.user.username}&password=${this.user.password}&aboutme=${this.user.aboutme}
    &fullname=${this.user.fullname}`;
    console.log(body);
    this.httpClient.post('http://localhost:8080/watchlist/new', body, headers)
    .subscribe(msg => {this.postResult = msg; console.log(msg); }, err => { console.log(err); throw ''; });
    sessionStorage.removeItem('movie');
  }
}
