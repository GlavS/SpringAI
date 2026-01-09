import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of} from 'rxjs';
import { WorkDto } from './work-dto';
import {mockWorks} from './works-api-mocks';

@Injectable({ providedIn: 'root' })
export class WorksApiService {
  constructor(private http: HttpClient) {}

  // list(): Observable<WorkDto[]> {
  //   return this.http.get<WorkDto[]>('/api/works');
  // }
  // getById(id: number): Observable<WorkDto> {
  //   return this.http.get<WorkDto>(`/api/works/${id}`);
  // }

  //Подключить mock-api  //////////////////////////////////////////////////

  list(): Observable<WorkDto[]> {
    return of(mockWorks)
  }
  getById(id: number): Observable<WorkDto> {
    return of(mockWorks[id - 1]);
  }
}
