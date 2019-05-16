package co.com.ceiba.ceibaestacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.CostoEstadiaRepositorio;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.CostoEstadiaEntity;

@Component
public class CostoEstadiaRepositorioMySQL implements CostoEstadiaRepositorio
{
	@Autowired
	private CostoEstadiaRepositorioData costoEstadiaRepositorioData;
	
	@Override
	public Optional<CostoEstadiaEntity> findBy(String tipoMovil, String tipoPago, String tiempoEstadia) 
	{
		return costoEstadiaRepositorioData.findBy(tipoMovil, tipoPago, tiempoEstadia);
	}

}
