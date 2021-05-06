import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CitiesComponent } from './cities/cities.component';
import { TableComponent } from './table/table.component';

const routes: Routes = [
  {path : 'cities', component: CitiesComponent},
  {path : 'table', component: TableComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
