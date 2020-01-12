import { Component, OnInit } from '@angular/core';
import { WatchList } from '../watchlist';
import { WatchlistService} from '../watchlist.service';

@Component({
  selector: 'app-allreviews',
  templateUrl: './allreviews.component.html',
  styleUrls: ['./allreviews.component.css']
})
export class AllreviewsComponent implements OnInit {

  constructor(private watchlistservice: WatchlistService) { }

  watchlists: WatchList[];
  ngOnInit() {
    this.getallreviews();
  }

  getallreviews(): void {
    this.watchlistservice.allmovies().subscribe(watchlists => this.watchlists = watchlists);
  }
}
