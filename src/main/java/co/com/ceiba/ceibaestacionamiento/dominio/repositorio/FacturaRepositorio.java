package co.com.ceiba.ceibaestacionamiento.dominio.repositorio;

import java.util.List;
import java.util.Optional;

import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.FacturaEntity;

public interface FacturaRepositorio 
{
	FacturaEntity save(FacturaEntity facturaEntity);
	
	List<FacturaEntity> getListadoMovilesEstacionamiento();
	
	Integer getCantidadMovilesByTipo(String tipoMovil);
	
	Integer getMovilEstacionamientoByPlaca(String placa);
	
	Optional<FacturaEntity> getFacturaByPlaca(String placa); 
	
	int getCantMovilesEstacionamiento();
}	
