import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmareliminacionComponent } from './confirmareliminacion.component';

describe('ConfirmareliminacionComponent', () => {
  let component: ConfirmareliminacionComponent;
  let fixture: ComponentFixture<ConfirmareliminacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmareliminacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmareliminacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
