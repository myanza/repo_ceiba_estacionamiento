import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListadomovilesComponent } from './listadomoviles/listadomoviles.component';

import { MatListModule, MatProgressSpinnerModule, MatCardModule,
  MatToolbarModule, MatFormFieldModule, MatInputModule, MatButtonModule,
  MatSidenavModule, MatIconModule, MatDialogModule, MatTableModule,
  MatPaginatorModule, MatSortModule } from '@angular/material';
import {MatRadioModule} from '@angular/material/radio';
import { RegistrarmovilComponent } from './registrarmovil/registrarmovil.component';
import { MensajeComponent } from './mensaje/mensaje.component';
import { EliminarmovilComponent } from './eliminarmovil/eliminarmovil.component';


@NgModule({
  declarations: [
    AppComponent,
    ListadomovilesComponent,
    RegistrarmovilComponent,
    MensajeComponent,
    EliminarmovilComponent
  ],
  entryComponents: [
    MensajeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatListModule,

    MatProgressSpinnerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    FormsModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    BrowserAnimationsModule,
    MatRadioModule,
    ReactiveFormsModule
  ],
  exports: [
    HttpClientModule,

    MatListModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    FormsModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    BrowserAnimationsModule,
    MatRadioModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
