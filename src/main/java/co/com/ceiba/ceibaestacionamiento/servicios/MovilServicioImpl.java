package co.com.ceiba.ceibaestacionamiento.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.MovilRepositorio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.MovilServicio;
import co.com.ceiba.ceibaestacionamiento.persistencia.builders.MovilBuilder;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.MovilEntity;
import co.com.ceiba.ceibaestacionamiento.persistencia.repositorio.MovilRepositorioData;

@Service
public class MovilServicioImpl implements MovilServicio
{
	@Autowired
	private MovilRepositorio movilRepositorio;
	
	private MovilBuilder movilBuilder = new MovilBuilder();
	
	@Autowired
	public MovilServicioImpl(MovilRepositorio movilRepositorio) {
		
	}

	@Override
	public boolean registrarMovil(Movil movil) 
	{
		MovilEntity movilEntity = movilBuilder.convertirAEntity(movil);

		MovilEntity mov = movilRepositorio.save(movilEntity);
		return mov != null;
	}
	
	@Override
	public Movil getMovilByPlaca(String placa) 
	{
		Movil movil = null;
		Optional<MovilEntity> movilEntity = movilRepositorio.findById(placa);
		
		if(movilEntity.isPresent())
		{
			movil = movilBuilder.convertirADominio(movilEntity.get());
		}
		return movil;
	}
}
