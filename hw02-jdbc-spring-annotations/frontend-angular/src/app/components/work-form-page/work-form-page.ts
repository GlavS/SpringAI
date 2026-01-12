import {Component, inject} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

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

  workForm = new FormGroup(
    {
      title: new FormControl('')
    }
  );

  submitValue(){
    if(this.mode === 'CREATE'){
      console.log('Creating title: ', this.workForm.value.title);
    } else {
      console.log('Editing title: ', this.workForm.value.title);
    }
  }
}
