package co.com.ceiba.ceibaestacionamiento.dominio.servicios;

@FunctionalInterface
public interface CostoEstadiaServicio 
{
	public double getCostoEstadiaBy(String tipoMovil, String tipoPago, String tiempoEstadia);
}
