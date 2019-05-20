import { Component, OnInit, ViewChild, EventEmitter, Output} from '@angular/core';
import { Router } from '@angular/router';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { EstacionamientoService } from '../estacionamiento.service';
import { Factura } from '../factura';


@Component({
  selector: 'app-listadomoviles',
  templateUrl: './listadomoviles.component.html',
  styleUrls: ['./listadomoviles.component.css']
})
export class ListadomovilesComponent implements OnInit
{
  cargando = true;
  facturas: Factura[] = [];

  listaColumnas = ['placa', 'tipoMovil', 'fechaIngreso', 'operaciones'];

  @Output() public eliminar = new EventEmitter<Factura>();

  constructor(private router: Router, private estacionamientoService: EstacionamientoService) {}

  ngAfterViewInit()
  {
    this.getListadoMoviles();
  }

  public getListadoMoviles()
  {
    merge()
      .pipe(
      startWith({}),
      switchMap(() => {
        this.cargando = true;
        return this.estacionamientoService.getListadoMovilesEstacionamiento();
      }),
      map(data => {
        this.cargando = false;
        return data;
      }),
      catchError(() => {
        this.cargando = false;
        return observableOf([]);
      })
      ).subscribe(data => this.facturas = data as Factura[]);
  }

  ngOnInit() { }

  eliminarMovil(factura)
  {
    this.eliminar.emit(factura);
  }

}
