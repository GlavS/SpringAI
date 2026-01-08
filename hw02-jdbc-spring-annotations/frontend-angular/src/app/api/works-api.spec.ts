import { TestBed } from '@angular/core/testing';

import { WorksApiService } from './works-api';

describe('WorksApi', () => {
  let service: WorksApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorksApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
