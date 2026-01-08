import { Component, signal } from '@angular/core';
import {WorkListViewComponent} from './views/work-list-view/work-list-view';

@Component({
  selector: 'app-root',
  imports: [WorkListViewComponent],
  templateUrl: './app-work.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend-angular');
}
