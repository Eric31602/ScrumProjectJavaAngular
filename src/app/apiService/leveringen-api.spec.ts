import { TestBed } from '@angular/core/testing';

import { LeveringenApi } from './leveringen-api';

describe('LeveringenApi', () => {
  let service: LeveringenApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeveringenApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
