import { TestBed } from '@angular/core/testing';

import { WorksApi } from './works-api';

describe('WorksApi', () => {
  let service: WorksApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorksApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
