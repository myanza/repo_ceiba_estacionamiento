import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EstacionamientoService } from '../estacionamiento.service';


@Component({
  selector: 'app-listadomoviles',
  templateUrl: './listadomoviles.component.html',
  styleUrls: ['./listadomoviles.component.css']
})
export class ListadomovilesComponent implements OnInit
{
  moviles: [];
  //selectedMovil: Bike;

  constructor(private router: Router, private estacionamientoService: EstacionamientoService) {

  }

  ngOnInit()
  {
    this.estacionamientoService.getListadoMovilesEstacionamiento.then(moviles => this.moviles = moviles);
  }

}
