import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {WorkDto} from './work-dto';

@Injectable({ providedIn: 'root' })
export class HttpApiService {
  constructor(private http: HttpClient) {}

  list(): Observable<WorkDto[]> {
    return this.http.get<WorkDto[]>('/api/works');
  }
  getById(id: number): Observable<WorkDto> {
    return this.http.get<WorkDto>(`/api/works/${id}`);
  }
}
