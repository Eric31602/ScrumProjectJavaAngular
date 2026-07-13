import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Orderpicking } from './orderpicking';

describe('Orderpicking', () => {
  let component: Orderpicking;
  let fixture: ComponentFixture<Orderpicking>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Orderpicking],
    }).compileComponents();

    fixture = TestBed.createComponent(Orderpicking);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
