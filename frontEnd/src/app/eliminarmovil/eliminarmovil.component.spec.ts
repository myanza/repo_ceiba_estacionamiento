import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarmovilComponent } from './eliminarmovil.component';

describe('EliminarmovilComponent', () => {
  let component: EliminarmovilComponent;
  let fixture: ComponentFixture<EliminarmovilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EliminarmovilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EliminarmovilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
