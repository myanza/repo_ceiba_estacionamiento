import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ListadomovilesComponent } from './listadomoviles/listadomoviles.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent
{
  title = 'Estacionamiento';

  @ViewChild(ListadomovilesComponent) private listadoMoviles:
  ListadomovilesComponent;

  public recargarListadoMoviles()
  {
    this.listadoMoviles.getListadoMoviles();
  }
}
