package co.com.ceiba.CeibaEstacionamiento.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;
import co.com.ceiba.CeibaEstacionamiento.persistencia.builders.MovilBuilder;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.MovilEntity;
import co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio.MovilRepositorio;

@Service
public class MovilServicio 
{
	@Autowired
	private MovilRepositorio movilRepositorio;
	
	private MovilBuilder movilBuilder = new MovilBuilder();

	public boolean registrarMovil(Movil movil) 
	{
		MovilEntity movilEntity = movilBuilder.convertirAEntity(movil);
		MovilEntity mov = movilRepositorio.save(movilEntity);
		if(mov != null) return true;
		else return false;
	}
	
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
