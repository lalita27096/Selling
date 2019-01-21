import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { SellingService } from './selling.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Selling System';
  staffs: Array<any>;

  constructor(private sellingService: SellingService ) { }

  ngOnInit() {
    this.sellingService.getStaff().subscribe(data => {
      this.staffs = data;
      console.log(data);
    });
  }
}


