import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tvscherm } from './tvscherm';

describe('Tvscherm', () => {
  let component: Tvscherm;
  let fixture: ComponentFixture<Tvscherm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Tvscherm],
    }).compileComponents();

    fixture = TestBed.createComponent(Tvscherm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
