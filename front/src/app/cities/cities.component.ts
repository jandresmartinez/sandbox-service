import { Component, OnInit,ViewChild } from '@angular/core';
import {CitiesService} from '../cities.service';
import { MatPaginator } from '@angular/material/paginator';

import { CitiesDataSource } from '../cities.datasource';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {
  displayedColumns = ['id', 'name'];
  citiesDatasource: CitiesDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private citiesService: CitiesService) { }

  ngOnInit() {
    this.citiesDatasource = new CitiesDataSource(this.citiesService);
    this.citiesDatasource.loadCities();
  }

  ngAfterViewInit() {
    this.citiesDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();

    this.paginator.page
      .pipe(
        tap(() => this.loadCities())
      )
      .subscribe();
      
  } 

  

  loadCities() {
    this.citiesDatasource.loadCities(this.paginator.pageIndex, this.paginator.pageSize);
  }

}
