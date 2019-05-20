import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material';
import { EstacionamientoService } from '../estacionamiento.service';

@Component({
  selector: 'app-registrarmovil',
  templateUrl: './registrarmovil.component.html',
  styleUrls: ['./registrarmovil.component.css']
})
export class RegistrarmovilComponent implements OnInit
{
  public placa: string = '';
  public tipoMovil: string = '';
  public cilindraje: number = 1;

  @ViewChild('registrarmovilform') registrarmovilform;

  @Output() public recargar = new EventEmitter();

  constructor(private dialog: MatDialog, private estacionamientoServicio: EstacionamientoService) { }

  ngOnInit() {
  }

  registrarMovil()
  {
    if (!this.placa || this.placa === '')
    {
      /*this.dialog.open(DialogoComponent,
      {
        data:
        {
          titulo: "Error al validar datos",
          mensaje: "La placa del vehiculo es obligatoria."
        }
      });*/
    }
    else
    {
      var movil =
      {
        placa: this.placa,
        tipoMovil: this.tipoMovil,
        cilindraje: this.cilindraje >= 1 && this.tipoMovil === 'MOTO' ? this.cilindraje : 0
      };

      this.estacionamientoServicio.registrarMovil(movil).subscribe((response) =>
      {
        /*this.dialog.open(DialogoComponent,
        {
          data: {
            titulo: "Informacion",
            mensaje: "Entrada registrada, para vehiculo con placa " + this.placa + "."
          }
        });*/
       this.recargar.emit();
        this.placa = '';
        this.cilindraje = 1;
        this.tipoMovil = '';
        this.registrarmovilform.reset();
      },
      (error) =>
      {
        /*this.dialog.open(DialogoComponent,
        {
          data:
          {
            titulo: "Error",
            mensaje: error.error.message
          }
        });*/
      });
    }
  }

}
