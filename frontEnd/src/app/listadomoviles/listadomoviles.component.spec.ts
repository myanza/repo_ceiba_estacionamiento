import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadomovilesComponent } from './listadomoviles.component';

describe('ListadomovilesComponent', () => {
  let component: ListadomovilesComponent;
  let fixture: ComponentFixture<ListadomovilesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListadomovilesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadomovilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
