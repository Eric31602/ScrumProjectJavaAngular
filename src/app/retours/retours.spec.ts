import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Retours } from './retours';

describe('Retours', () => {
  let component: Retours;
  let fixture: ComponentFixture<Retours>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Retours],
    }).compileComponents();

    fixture = TestBed.createComponent(Retours);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
