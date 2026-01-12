import {Component, inject} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-work-form-page',
  imports: [],
  templateUrl: './work-form-page.html',
  styleUrl: './work-form-page.css',
})
export class WorkFormPage {
  private route = inject(ActivatedRoute);
  protected mode:string = this.route.snapshot.paramMap.has("id")? 'EDIT' : 'CREATE';
}
