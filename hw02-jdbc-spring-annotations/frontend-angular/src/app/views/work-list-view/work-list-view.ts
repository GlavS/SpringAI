import { Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable, of } from 'rxjs';
import { catchError, map, startWith } from 'rxjs/operators';

import { WorksApiService } from '../../api/works-api';
import { LoadState, WorkDto } from '../../api/work-dto';

@Component({
  selector: 'app-work-list-view',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './work-list-view.html',
})
export class WorkListViewComponent {

  private worksApi: WorksApiService = inject(WorksApiService);
  expandedId: number | null = null;

  // поток состояний (как твой state: loading/error/success)
  readonly state$: Observable<LoadState<WorkDto[]>> = this.worksApi.list().pipe(
    map((data) => ({ status: 'success', data } as const)),
    catchError((e: unknown) =>
      of({ status: 'error', message: this.getErrorMessage(e) } as const)
    ),
    startWith({ status: 'loading' } as const)
  );

  fmtDuration(sec?: number | null) {
    if (sec == null) return ''
    const m = Math.floor(sec / 60)
    const s = sec % 60
    return `${m}:${String(s).padStart(2, '0')}`
  }

  toggle(id: number) {
    this.expandedId = this.expandedId === id ? null : id;
  }

  private getErrorMessage(e: unknown): string {
    return e instanceof Error ? e.message : String(e);
  }
}
