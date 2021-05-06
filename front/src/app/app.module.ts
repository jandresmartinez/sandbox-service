import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CitiesComponent } from './cities/cities.component';
import {  MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule  } from '@angular/material/table';
import { MatPaginatorModule  } from '@angular/material/paginator';
import { MatSortModule  } from '@angular/material/sort';
import { TableComponent } from './table/table.component';

@NgModule({
  declarations: [
    AppComponent,
    CitiesComponent,
    TableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule ,
    MatSortModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
