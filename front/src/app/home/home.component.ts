import { Component, OnInit } from '@angular/core';
import {GlobalConstants} from '../global-constants';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = GlobalConstants.siteTitle;
  constructor() { }

  ngOnInit(): void {
    
  }

}
