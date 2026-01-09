import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import {delay, Observable, of, throwError} from 'rxjs';
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
    const shouldFail = false; // поставь true чтобы проверить

    if (shouldFail) {
      return throwError(() =>
        new HttpErrorResponse({
          status: 500,
          statusText: 'Server Error',
          error: { message: 'Mock: list() failed' }
        })
      ).pipe(delay(300));
    }

    return of(mockWorks).pipe(delay(200));
  }
  getById(id: number): Observable<WorkDto> {
    return of(mockWorks[id - 1]).pipe(delay(200));
  }
}
