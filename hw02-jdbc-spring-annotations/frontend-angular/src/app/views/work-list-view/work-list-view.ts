import { Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable, of } from 'rxjs';
import { catchError, map, startWith } from 'rxjs/operators';

import { WorksApiService } from '../../api/works-api';
import { LoadState, WorkDto } from '../../api/work-dto';
import {Router} from '@angular/router';
import {WorkDetailsPanelComponent} from '../../components/work-details-panel/work-details-panel';
import {ErrorMessageService} from '../../services/error-message';

@Component({
  selector: 'app-work-list-view',
  standalone: true,
  imports: [CommonModule, WorkDetailsPanelComponent],
  templateUrl: './work-list-view.html',
})
export class WorkListViewComponent {

  private worksApi: WorksApiService = inject(WorksApiService);
  private errorService: ErrorMessageService = inject(ErrorMessageService);
  private router = inject(Router)
  expandedId: number | null = null;

  // поток состояний (аналог проекта Vue: loading/error/success)
  readonly state$: Observable<LoadState<WorkDto[]>> = this.worksApi.list().pipe(
    map((data) => ({ status: 'success', data } as const)),
    catchError((e: unknown) =>
      of({ status: 'error', message: this.errorService.getErrorMessage(e) } as const)
    ),
    startWith({ status: 'loading' } as const)
  );

  toggle(id: number) {
    this.expandedId = this.expandedId === id ? null : id;
  }

  openDetails(id: number) {
    this.router.navigate(['/works', id]);
  }
}
