package co.com.ceiba.ceibaestacionamiento.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.CostoEstadiaRepositorio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.CostoEstadiaServicio;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.CostoEstadiaEntity;

@Service
public class CostoEstadiaServicioImpl implements CostoEstadiaServicio
{
	@Autowired
	private CostoEstadiaRepositorio costoEstadiaRepositorio;
	
	public double getCostoEstadiaBy(String tipoMovil, String tipoPago, String tiempoEstadia) 
	{
		Optional<CostoEstadiaEntity> costoEstadiaEntity = costoEstadiaRepositorio.findBy(tipoMovil, tipoPago, tiempoEstadia);
		
		if(costoEstadiaEntity.isPresent()) 
			return costoEstadiaEntity.get().getCosValor();
		else 
			return 0;
	}
}
