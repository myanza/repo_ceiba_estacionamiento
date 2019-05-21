package co.com.ceiba.ceibaestacionamiento.dominio.servicios;

import co.com.ceiba.ceibaestacionamiento.dominio.Movil;

public interface MovilServicio 
{
	public boolean registrarMovil(Movil movil);
	
	public Movil getMovilByPlaca(String placa);
}
