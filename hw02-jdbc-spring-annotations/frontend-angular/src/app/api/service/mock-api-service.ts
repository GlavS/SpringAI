import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { delay, map } from 'rxjs/operators';
import { WorkDto } from '../data/work-dto'; // поправь путь
import { RecordingDto } from '../data/work-dto';
import {mockWorks} from '../data/works-api-mocks'; // поправь путь

@Injectable({ providedIn: 'root' })
export class MockApiService {
  private readonly LATENCY_MS = 150;

  // если хочешь persistence:
  private readonly STORAGE_KEY = 'mock_works_v1';
  private readonly persist = true;

  private store$ = new BehaviorSubject<WorkDto[]>(this.loadInitial());

  // счётчики id (имитируем автоинкремент на бэке)
  private nextWorkId = this.calcNextWorkId(this.store$.value);
  private nextRecordingId = this.calcNextRecordingId(this.store$.value);

  list(): Observable<WorkDto[]> {
    return this.store$.pipe(
      map(list => structuredClone(list)),
      delay(this.LATENCY_MS),
    );
  }

  getById(id: number): Observable<WorkDto> {
    return this.store$.pipe(
      map(list => {
        const found = list.find(w => w.id === id);
        if (!found) throw new Error(`Work ${id} not found`);
        return structuredClone(found);
      }),
      delay(this.LATENCY_MS),
    );
  }

  create(work: WorkDto): Observable<WorkDto> {
    if (work.id !== 0) {
      return throwError(() => new Error('create(): work.id must be 0'));
    }

    const created = this.normalizeForCreateOrUpdate(work, /*isCreate*/ true);

    const list = this.store$.value;
    const next = [...list, created];

    this.commit(next);
    return of(structuredClone(created)).pipe(delay(this.LATENCY_MS));
  }

  update(id: number, work: WorkDto): Observable<WorkDto> {
    if (id <= 0) {
      return throwError(() => new Error('update(): id must be > 0'));
    }
    if (work.id !== id) {
      return throwError(() => new Error('update(): route id must match body id'));
    }

    const list = this.store$.value;
    const idx = list.findIndex(w => w.id === id);
    if (idx < 0) {
      return throwError(() => new Error(`Work ${id} not found`));
    }

    const updated = this.normalizeForCreateOrUpdate(work, /*isCreate*/ false);

    const next = list.slice();
    next[idx] = updated;

    this.commit(next);
    return of(structuredClone(updated)).pipe(delay(this.LATENCY_MS));
  }

  // ---------- helpers ----------

  private normalizeForCreateOrUpdate(input: WorkDto, isCreate: boolean): WorkDto {
    const w = structuredClone(input);

    // Work.id
    if (isCreate) {
      w.id = this.nextWorkId++;
    } else if (w.id <= 0) {
      throw new Error('update(): work.id must be > 0');
    }

    // Recordings.id: всё что 0 -> выдаём новый id
    // (важно: это имитирует бэк, который раздаёт id вложенным сущностям)
    w.recordings = (w.recordings ?? []).map(r => {
      const rr = structuredClone(r) as any as RecordingDto;
      if (rr.id === 0) rr.id = this.nextRecordingId++;
      if (rr.id < 0) rr.id = this.nextRecordingId++; // на всякий случай
      return rr;
    });

    // Можно (по желанию) отсортировать записи стабильно, чтобы UI не “прыгал”
    // w.recordings.sort((a,b) => a.id - b.id);

    return w;
  }

  private commit(next: WorkDto[]) {
    this.store$.next(next);
    if (this.persist) {
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(next));
    }
  }

  private loadInitial(): WorkDto[] {
    if (!this.persist) return this.initialMocks();

    const raw = localStorage.getItem(this.STORAGE_KEY);
    if (!raw) return this.initialMocks();

    try {
      return JSON.parse(raw) as WorkDto[];
    } catch {
      return this.initialMocks();
    }
  }

  private initialMocks(): WorkDto[] {
    // возьми твои mockWorks, или что у тебя там сейчас
    // return mockWorks;
    return mockWorks;
  }

  private calcNextWorkId(list: WorkDto[]): number {
    const maxId = Math.max(0, ...list.map(w => w.id ?? 0));
    return maxId + 1;
  }

  private calcNextRecordingId(list: WorkDto[]): number {
    const all = list.flatMap(w => w.recordings ?? []);
    const maxId = Math.max(0, ...all.map(r => r.id ?? 0));
    return maxId + 1;
  }

  // опционально: сброс моков
  resetMocks() {
    const init = this.initialMocks();
    this.store$.next(init);
    this.nextWorkId = this.calcNextWorkId(init);
    this.nextRecordingId = this.calcNextRecordingId(init);
    if (this.persist) localStorage.removeItem(this.STORAGE_KEY);
  }
}
