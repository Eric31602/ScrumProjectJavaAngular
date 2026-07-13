import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeveringenFrontend } from './leveringen-frontend';

describe('LeveringenFrontend', () => {
  let component: LeveringenFrontend;
  let fixture: ComponentFixture<LeveringenFrontend>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeveringenFrontend],
    }).compileComponents();

    fixture = TestBed.createComponent(LeveringenFrontend);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
