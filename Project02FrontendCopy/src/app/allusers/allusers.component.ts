import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users.service';
import { User } from '../user';
import { WatchList } from '../watchlist';
import { WatchlistService } from '../watchlist.service';

@Component({
  selector: 'app-allusers',
  templateUrl: './allusers.component.html',
  styleUrls: ['./allusers.component.css']
})
export class AllusersComponent implements OnInit {

  constructor(private usersService: UsersService, private watchlistService: WatchlistService) { }

  users: User[];
  watchlists: WatchList[];
  show: boolean = true;
  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers(): void {
    this.usersService.allusers().subscribe(users => this.users = users);
  }

  allreviews(userid: any): void {
    this.watchlistService.allmoviesfromoneuser(userid).subscribe(watchlists => this.watchlists = watchlists);
    this.show = false;
  }

  back() {
    this.show = true;
  }
}
