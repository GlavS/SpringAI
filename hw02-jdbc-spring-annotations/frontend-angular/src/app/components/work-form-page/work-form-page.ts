import {Component, inject} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {DIFFICULTIES, DIFFICULTY_LABELS} from '../../api/data/model/difficulty-type';
import {oneOf} from './work-form-validator';

@Component({
  selector: 'app-work-form-page',
  standalone: true,
  imports: [
    ReactiveFormsModule,
  ],
  templateUrl: './work-form-page.html',
  styleUrl: './work-form-page.css',
})

export class WorkFormPage {
  difficulties = DIFFICULTIES;
  dLabels = DIFFICULTY_LABELS;
  private route = inject(ActivatedRoute);
  private fb: FormBuilder = inject(FormBuilder);
  workForm = this.fb.nonNullable.group({
    title: ['', [Validators.required, Validators.maxLength(200)]],
    instrument: [''],
    difficulty: ['', [Validators.required, oneOf(DIFFICULTIES)]]
  });

  get mode(): 'EDIT' | 'CREATE' {
    return this.route.snapshot.paramMap.has('id') ? 'EDIT' : 'CREATE';
  }

  submitValue(){
    const draft = this.workForm.getRawValue();
    console.log(this.mode, draft);
  }
}
