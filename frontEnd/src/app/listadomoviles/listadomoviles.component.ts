import { Component, OnInit, ViewChild, EventEmitter, Output} from '@angular/core';
import { Router } from '@angular/router';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { EstacionamientoService } from '../estacionamiento.service';
import { Factura } from '../factura';
import { ConfirmareliminacionComponent } from '../confirmareliminacion/confirmareliminacion.component';
import {MatDialog, MatDialogRef} from '@angular/material';


@Component({
  selector: 'app-listadomoviles',
  templateUrl: './listadomoviles.component.html',
  styleUrls: ['./listadomoviles.component.css']
})
export class ListadomovilesComponent implements OnInit
{
  public hayMoviles: boolean = false;
  facturas: Factura[] = [];

  listaColumnas = ['placa', 'tipoMovil', 'fechaIngreso', 'operaciones'];

  @Output() public eliminar = new EventEmitter<Factura>();

  constructor(private router: Router, private dialog: MatDialog, private estacionamientoService: EstacionamientoService) {}

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
        return this.estacionamientoService.getListadoMovilesEstacionamiento();
      }),
      map(data => {
        return data;
      }),
      catchError(() => {
        return observableOf([]);
      })
      ).subscribe(data =>
        {
          this.facturas = data as Factura[];
          if (this.facturas.length > 0) { this.hayMoviles = true; }
          else { this.hayMoviles = false; }
        }
      );
  }

  ngOnInit() { }

  eliminarMovil(factura)
  {
    let dialogref = this.dialog.open(ConfirmareliminacionComponent,
    {
      data:
      {
        titulo: 'Confirmar Eliminación Móvil',
        texto: factura
      }
    });

    dialogref.afterClosed().subscribe(result =>
    {
      if (result != null){
        this.eliminar.emit(result);
      }
    });


  }

}
