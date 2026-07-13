import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Leveringen } from './leveringen';

describe('Leveringen', () => {
  let component: Leveringen;
  let fixture: ComponentFixture<Leveringen>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Leveringen],
    }).compileComponents();

    fixture = TestBed.createComponent(Leveringen);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
