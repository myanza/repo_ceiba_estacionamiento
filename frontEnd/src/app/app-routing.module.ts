import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListadomovilesComponent } from './listadomoviles/listadomoviles.component';

const routes: Routes = [
	{ path: 'listadomoviles', component: ListadomovilesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
