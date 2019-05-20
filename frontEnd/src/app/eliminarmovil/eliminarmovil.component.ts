import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material';
import { EstacionamientoService } from '../estacionamiento.service';
import { Factura } from '../factura';
import { MensajeComponent } from '../mensaje/mensaje.component';

@Component({
  selector: 'app-eliminarmovil',
  templateUrl: './eliminarmovil.component.html',
  styleUrls: ['./eliminarmovil.component.css']
})
export class EliminarmovilComponent implements OnInit
{
  public placa: string;
  public cilindraje: number;
  public fechaIngreso: string;

  @Output() public recargar = new EventEmitter()

  constructor(private dialog: MatDialog, private estacionamientoService: EstacionamientoService) { }

  ngOnInit() {
  }

  public cargarDatos(factura)
  {
    if (factura)
    {
      this.placa = factura.movPlaca;
      this.cilindraje = factura.cilindraje;
      this.fechaIngreso = factura.facFechaIngreso;
    }
  }

  public eliminarMovil()
  {
    if (this.placa !== '')
    {
      console.log('placa = ' + this.placa);
      this.estacionamientoService.eliminarMovil(this.placa).subscribe((response) =>
      {
        if(response.facValor)
        {
          this.dialog.open(MensajeComponent,
          {
            data: {
              titulo: 'Eliminación Exitosa',
              texto: 'El vehículo de placa ' + this.placa + ' fue eliminado exitosamente. Valor total: $' + response.facValor + ' pesos.'
            }
          });
          console.log(response);
        }
        this.recargar.emit();
        this.placa = '';
        this.cilindraje = 1;
        this.fechaIngreso = '';
      },
      (error) =>
      {
        this.dialog.open(MensajeComponent,
          {
          data: {
            titulo: 'Error',
            texto: 'Debe seleccionar un móvil a eliminar.'
          }
        });
      });
    }
  }
}
