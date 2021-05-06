import { Component, OnInit } from '@angular/core';
import {CitiesService} from '../cities.service';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {
  cities: Array<any>;
  constructor(private citiesService: CitiesService) { }

  ngOnInit() {
    this.citiesService.findAll({page:0,size:30}).subscribe(data => {
      this.cities = data.content;
    });
  }

}
