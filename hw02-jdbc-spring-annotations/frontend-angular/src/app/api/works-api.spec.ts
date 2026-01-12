import { TestBed } from '@angular/core/testing';

import {inject} from '@angular/core';
import {WORKS_API} from './works-api-token';

describe('WorksApi', () => {
  let service = inject(WORKS_API);

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WORKS_API);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
