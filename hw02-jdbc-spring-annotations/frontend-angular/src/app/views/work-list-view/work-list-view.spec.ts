import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkListView } from './work-list-view';

describe('WorkListView', () => {
  let component: WorkListView;
  let fixture: ComponentFixture<WorkListView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkListView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkListView);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
