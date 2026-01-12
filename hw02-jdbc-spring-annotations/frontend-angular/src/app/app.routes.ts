import {Routes} from '@angular/router';
import {WorkListViewComponent} from './views/work-list-view/work-list-view';
import {WorkDetailsViewComponent} from './views/work-details-view/work-details-view';
import {WorkFormPage} from './components/work-form-page/work-form-page';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'works' },
  { path: 'works', component: WorkListViewComponent },
  { path: 'works/new', component: WorkFormPage },
  { path: 'works/:id', component: WorkDetailsViewComponent },
  { path: 'works/:id/edit', component: WorkFormPage },
  { path: '**', redirectTo: 'works' },
];
