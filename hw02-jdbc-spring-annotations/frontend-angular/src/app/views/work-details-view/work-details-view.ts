import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, distinctUntilChanged, map, startWith, switchMap } from 'rxjs/operators';

import {WorksApi } from '../../api/works-api';
import { LoadState, WorkDto } from '../../api/work-dto';
import { WorkDetailsPanelComponent } from '../../components/work-details-panel/work-details-panel';
import {ErrorMessageService} from '../../services/error-message';
import {WORKS_API} from '../../api/works-api-token';

@Component({
  selector: 'app-work-details-view',
  standalone: true,
  imports: [CommonModule, WorkDetailsPanelComponent],
  templateUrl: './work-details-view.html',
  styleUrl: './work-details-view.css',
})
export class WorkDetailsViewComponent {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private worksApi:WorksApi = inject(WORKS_API);
  private errorService: ErrorMessageService = inject(ErrorMessageService);

  readonly id$: Observable<number | null> = this.route.paramMap.pipe(
    map((pm) => {
      const raw = pm.get('id');
      if (raw == null) return null;
      const n = Number(raw);
      return Number.isFinite(n) ? n : null;
    }),
    distinctUntilChanged()
  );

  readonly state$: Observable<LoadState<WorkDto>> = this.id$.pipe(
    switchMap((id) => {
      if (id == null) {
        return of({ status: 'error', message: 'Invalid id' } as const);
      }
      return this.worksApi.getById(id).pipe(
        map((data) => ({ status: 'success', data } as const)),
        catchError((e: unknown) =>
          of({ status: 'error', message: this.errorService.getErrorMessage(e) } as const)
        ),
        startWith({ status: 'loading' } as const)
      );
    })
  );

  goBackToList() {
    this.router.navigate(['/works']);
  }
}
