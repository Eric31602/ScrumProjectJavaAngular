import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Leveranciers } from './leveranciers';

describe('Leveranciers', () => {
  let component: Leveranciers;
  let fixture: ComponentFixture<Leveranciers>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Leveranciers],
    }).compileComponents();

    fixture = TestBed.createComponent(Leveranciers);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
