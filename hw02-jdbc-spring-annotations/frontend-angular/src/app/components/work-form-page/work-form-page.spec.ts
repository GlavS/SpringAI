import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkFormPage } from './work-form-page';

describe('WorkFormPage', () => {
  let component: WorkFormPage;
  let fixture: ComponentFixture<WorkFormPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkFormPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkFormPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
