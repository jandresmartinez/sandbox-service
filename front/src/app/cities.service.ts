import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  private citiesUrl: string;

  constructor(private http: HttpClient) {
    this.citiesUrl = 'http://localhost:1111/api/cities/queryByPage';
  }

  public findAll(request): Observable<any> {
    const params = request;
    return this.http.get(this.citiesUrl,{params});
  }
}
