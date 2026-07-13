import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Hoofdmenu } from './hoofdmenu';

describe('Hoofdmenu', () => {
  let component: Hoofdmenu;
  let fixture: ComponentFixture<Hoofdmenu>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Hoofdmenu],
    }).compileComponents();

    fixture = TestBed.createComponent(Hoofdmenu);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
