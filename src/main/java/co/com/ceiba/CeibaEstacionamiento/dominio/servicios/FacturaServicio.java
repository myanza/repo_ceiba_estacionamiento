package co.com.ceiba.ceibaestacionamiento.dominio.servicios;

import java.util.List;

import co.com.ceiba.ceibaestacionamiento.dominio.Factura;
import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.MovilDTO;

public interface FacturaServicio 
{
	public List<FacturaDTO> getListadoMovilesEstacionamiento();
	
	public Integer getCantidadMovilesByTipo(String tipoVehiculo);

	public Integer getMovilEstacionamientoByPlaca(String placa);

	public boolean crearFactura(Movil movil);
	
	public boolean registrarMovil(MovilDTO movilDTO, MovilServicio movilServicio, CostoEstadiaServicio costoEstadiaServicio);

	public Factura getFacturaByPlaca(String placa);
	
	public Factura actualizarFactura(Factura factura);

	public FacturaDTO eliminarMovil(String placa, MovilServicio movilServicio, CostoEstadiaServicio costoEstadiaServicio);
}
