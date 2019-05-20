import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material';
import { EstacionamientoService } from '../estacionamiento.service';
import { MensajeComponent } from '../mensaje/mensaje.component';


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

  constructor(private mensaje: MatDialog, private estacionamientoServicio: EstacionamientoService) { }

  ngOnInit() {
  }

  abrirMensaje(stitulo: string, stexto: string)
  {
    this.mensaje.open(MensajeComponent,
    {
      data:
      {
        titulo: stitulo,
        texto: stexto
      }
    });
  }

  validarDatos()
  {
    if(!this.placa || this.placa === '')
    {
      const titulo = 'Datos inválidos.';
      const texto =  'La placa del móvil es obligatoria.';
      this.abrirMensaje(titulo, texto);
    }
    else if(!this.tipoMovil || this.tipoMovil === '')
    {
      const titulo = 'Datos inválidos.';
      const texto =  'El tipo del móvil es obligatorio.';
      this.abrirMensaje(titulo, texto);
    }
  }

  registrarMovil()
  {
    this.validarDatos();

    const movil =
    {
      placa: this.placa,
      tipoMovil: this.tipoMovil,
      cilindraje: this.cilindraje >= 1 && this.tipoMovil === 'MOTO' ? this.cilindraje : 0
    };

    this.estacionamientoServicio.registrarMovil(movil).subscribe((response) =>
    {
      this.mensaje.open(MensajeComponent, {
        data: {
          titulo: 'Registro Exitoso',
          texto: 'El móvil de placa ' + this.placa + ' fue registrado existosamente.'
        }
      });

      this.recargar.emit();
      this.placa = '';
      this.tipoMovil = '';
      this.cilindraje = 1;
      this.registrarmovilform.reset();
    },
    (error) =>
    {
      this.mensaje.open(MensajeComponent,
      {
        data: {
          titulo: 'Error',
          texto: error.error.message
        }
      });
    });
  }
}
