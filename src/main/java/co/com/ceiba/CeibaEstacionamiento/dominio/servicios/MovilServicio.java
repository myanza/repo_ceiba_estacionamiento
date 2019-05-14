package co.com.ceiba.CeibaEstacionamiento.dominio.servicios;

import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;

public interface MovilServicio 
{
	public boolean registrarMovil(Movil movil);
	
	public Movil getMovilByPlaca(String placa);
}
