import {Component, inject} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-work-form-page',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './work-form-page.html',
  styleUrl: './work-form-page.css',
})

export class WorkFormPage {
  private route = inject(ActivatedRoute);
  protected mode:string = this.route.snapshot.paramMap.has("id")? 'EDIT' : 'CREATE';
  private fb: FormBuilder = inject(FormBuilder);

  workForm = this.fb.nonNullable.group({
    title: '',
    instrument: '',
    difficulty: ''
  });

  submitValue(){
   console.log(this.mode);
   console.log(this.workForm.value.title)
   console.log(this.workForm.value.instrument)
   console.log(this.workForm.value.difficulty)
  }
}
