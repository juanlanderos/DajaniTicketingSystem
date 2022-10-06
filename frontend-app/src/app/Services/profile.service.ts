import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from 'src/app/Models/profile';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseUrl = "http://localhost:9000/api/profile"


  constructor(private httpClient: HttpClient) { }

  //functions go here

  getProfile(): Observable<Profile[]>{
    return this.httpClient.get<Profile[]>(this.baseUrl);
  }
}
