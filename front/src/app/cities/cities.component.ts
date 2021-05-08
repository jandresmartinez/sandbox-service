import { Component, OnInit, ViewChild } from "@angular/core";
import { CitiesService } from '../service/cities.service';
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { CitiesDataSource } from "../service/cities.datasource";
import { tap,debounceTime,distinctUntilChanged } from "rxjs/operators";
import { merge,fromEvent } from "rxjs";
import { ElementRef } from "@angular/core";
import {MatDialog} from '@angular/material/dialog';
import { DialogComponent } from "../dialog/dialog.component";

@Component({
  selector: "app-cities",
  templateUrl: "./cities.component.html",
  styleUrls: ["./cities.component.css"],
})
export class CitiesComponent implements OnInit {
  displayedColumns = ["id", "name"];
  citiesDatasource: CitiesDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild("input") input: ElementRef;
  
  constructor(private citiesService: CitiesService,public dialog: MatDialog) {}

  ngOnInit() {
    this.citiesDatasource = new CitiesDataSource(this.citiesService);
    this.citiesDatasource.loadCities();
  }

  ngAfterViewInit() {
    if (this.paginator != undefined) {
      this.setPaginator();
      this.setSort();
      this.setFilterByName();
    }
  }

  onRowClicked(row) {
    
     this.dialog.open(DialogComponent,{
      data: { name: row.name },
    });
  }

  loadCities() {
    this.citiesDatasource.loadCities(
      this.paginator.pageIndex,
      this.paginator.pageSize,
      this.sort.direction,
      this.input.nativeElement.value
    );
  }



  setPaginator() {
    /***Paginator ***/
    this.citiesDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();

    this.paginator.page.pipe(tap(() => this.loadCities())).subscribe();
  }

  setSort() {
    // reset the paginator after sorting
    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(tap(() => this.loadCities()))
      .subscribe();
  }

  setFilterByName(){
      // server-side search
      fromEvent(this.input.nativeElement,'keyup')
      .pipe(
          debounceTime(150),
          distinctUntilChanged(),
          tap(() => {
              this.paginator.pageIndex = 0;
              this.loadCities();
          })
      )
      .subscribe();
  }
}
