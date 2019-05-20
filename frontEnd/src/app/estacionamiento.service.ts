import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Factura } from './factura';


@Injectable({
  providedIn: 'root'
})
export class EstacionamientoService
{
  API_URL = 'http://localhost:8181/estacionamiento';

  constructor(private httpClient: HttpClient) {}

  getListadoMovilesEstacionamiento(): Observable<Factura>
  {
    return this.httpClient.get<Factura>(this.API_URL + '/listadomoviles');
  }

  registrarMovil(movil)
  {
    return this.httpClient.post(this.API_URL + '/registrarmovil', movil);
  }
}
