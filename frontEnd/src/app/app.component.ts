import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ListadomovilesComponent } from './listadomoviles/listadomoviles.component';
import { Factura } from './factura';
import { EliminarmovilComponent } from './eliminarmovil/eliminarmovil.component';

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

  @ViewChild(EliminarmovilComponent) private eliminarMovil:
  EliminarmovilComponent;

  public recargarListadoMoviles()
  {
    this.listadoMoviles.getListadoMoviles();
  }

  public eliminarMovilEstacionamiento(factura: Factura)
  {
    this.eliminarMovil.cargarDatos(factura);
  }
}
