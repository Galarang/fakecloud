import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Helper } from 'src/models/helper';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  getDuration(helper: Helper) {
    return this.http.post('http://localhost:8080/connection', helper);
  }

}
