package co.com.ceiba.ceibaestacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.MovilRepositorio;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.MovilEntity;

@Component
public class MovilRepositorioMySQL implements MovilRepositorio
{
	@Autowired
	MovilRepositorioData movilRepositorioData;
	
	@Override
	public MovilEntity save(MovilEntity movilEntity)
	{
		return movilRepositorioData.save(movilEntity);
	}
	
	@Override
	public Optional<MovilEntity> findById(String placa)
	{
		return movilRepositorioData.findById(placa);
	}
}
