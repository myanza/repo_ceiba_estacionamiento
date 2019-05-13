package co.com.ceiba.CeibaEstacionamiento.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.CostoEstadiaEntity;
import co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio.CostoEstadiaRepositorio;

@Service
public class CostoEstadiaServicio 
{
	@Autowired
	private CostoEstadiaRepositorio costoEstadiaRepositorio;
	
	public double obtenerValorTarifa(String tipoMovil, String tipoPago, String tiempoEstadia) 
	{
		Optional<CostoEstadiaEntity> costoEstadiaEntity = costoEstadiaRepositorio.
				findByTipoMovilAndTiempoEstadiaAndTipoPago(tipoMovil, tipoPago, tiempoEstadia);
		
		if(costoEstadiaEntity.isPresent()) 
			return costoEstadiaEntity.get().getCos_valor();
		else 
			return 0;
	}
}
