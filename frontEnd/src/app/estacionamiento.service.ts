import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EstacionamientoService
{
  API_URL = 'http://localhost:9091/parqueadero';

  constructor(private httpClient: HttpClient) {}

  getListadoMovilesEstacionamiento(): Observable<Resultado>
  {
    return this.httpClient.get<Resultado>
      (this.API_URL + '/listadomoviles');
  }
}
