import { Routes } from '@angular/router';
import {WorkListViewComponent} from './views/work-list-view/work-list-view';
import {WorkDetailsViewComponent} from './views/work-details-view/work-details-view';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'works' },
  { path: 'works', component: WorkListViewComponent },
  { path: 'works/:id', component: WorkDetailsViewComponent },
  { path: '**', redirectTo: 'works' },
];
