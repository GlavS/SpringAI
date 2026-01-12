// works-api.ts
import { Observable } from 'rxjs';
import { WorkDto } from './work-dto';

export interface WorksApi {
  list(): Observable<WorkDto[]>;
  getById(id: number): Observable<WorkDto>;
  create(work: WorkDto): Observable<WorkDto>;
  update(id: number, work: WorkDto): Observable<WorkDto>;
}
