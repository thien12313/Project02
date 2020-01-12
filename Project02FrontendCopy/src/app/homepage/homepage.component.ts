import { Component, OnInit } from '@angular/core';
import { WatchList } from '../watchlist';
import { WatchlistService } from '../watchlist.service';
import { User } from '../user';
import { SessionService } from '../session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  watchlists: WatchList[];
  watchlist: WatchList;
  user: User;
  isloggedin: boolean = false;
  constructor(private watchlistService: WatchlistService, private sessionService: SessionService, private router: Router) { }

  ngOnInit() {
    this.getarandommovie();
  }

  getarandommovie() {
    this.watchlistService.randommovie().subscribe(watchlist => this.watchlist = watchlist);
  }
}
