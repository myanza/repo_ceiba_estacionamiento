import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarmovilComponent } from './registrarmovil.component';

describe('RegistrarmovilComponent', () => {
  let component: RegistrarmovilComponent;
  let fixture: ComponentFixture<RegistrarmovilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrarmovilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarmovilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
