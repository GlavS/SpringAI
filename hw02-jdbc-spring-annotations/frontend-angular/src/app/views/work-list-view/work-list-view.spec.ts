import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WorkListViewComponent} from './work-list-view';

describe('WorkListView', () => {
  let component: WorkListViewComponent;
  let fixture: ComponentFixture<WorkListViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkListViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkListViewComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
