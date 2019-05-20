import { Component, OnInit, Inject, ViewChild, EventEmitter, Output } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { EliminarmovilComponent } from '../eliminarmovil/eliminarmovil.component';
import { Factura } from '../factura';

@Component({
  selector: 'app-confirmareliminacion',
  templateUrl: './confirmareliminacion.component.html',
  styleUrls: ['./confirmareliminacion.component.css']
})
export class ConfirmareliminacionComponent implements OnInit
{
  @ViewChild(EliminarmovilComponent) private eliminarMovil: EliminarmovilComponent;

  @Output() public eliminar = new EventEmitter<Factura>();

  constructor(private confimacionRef: MatDialogRef<ConfirmareliminacionComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit()
  {
  }

  public cancelar()
  {
    this.confimacionRef.close(null);
  }

  public eliminarMovilEstacionamiento(factura)
  {
    console.log('placa = ' + factura.movPlaca);
    this.confimacionRef.close(factura);
  }

}
