import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class DataAccessService {

  private baseUrl = 'http://localhost:8081/api/v1';

  constructor(private http: HttpClient) { }

  getDuration(requestObject: Object) {
    return this.http.post(`${this.baseUrl}/connections`, requestObject);
  }

}
