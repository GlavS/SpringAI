import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WorkDto } from './work-dto';

@Injectable({ providedIn: 'root' })
export class WorksApiService {
  constructor(private http: HttpClient) {}

  list(): Observable<WorkDto[]> {
    return this.http.get<WorkDto[]>('/api/works');
  }
}
