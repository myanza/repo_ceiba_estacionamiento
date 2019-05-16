package co.com.ceiba.ceibaestacionamiento.persistencia.repositorio;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.FacturaRepositorio;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.FacturaEntity;

@Component("facturaRepositorioMySQL")
@Transactional
public class FacturaRepositorioMySQL implements FacturaRepositorio
{
	@Autowired
	@Qualifier(value="facturaRepositorioData")
	FacturaRepositorioData facturaRepositorioData;
	
	@Override
	public FacturaEntity save(FacturaEntity facturaEntity)
	{
		return facturaRepositorioData.save(facturaEntity);
	}
	
	@Override
	public List<FacturaEntity> getListadoMovilesEstacionamiento()
	{
		return facturaRepositorioData.getListadoMovilesEstacionamiento();
	}
	
	@Override
	public Integer getCantidadMovilesByTipo(String tipoMovil)
	{
		return facturaRepositorioData.getCantidadMovilesByTipo(tipoMovil);
	}
	
	@Override
	public Integer getMovilEstacionamientoByPlaca(String placa)
	{
		return facturaRepositorioData.getMovilEstacionamientoByPlaca(placa);
	}
	
	@Override
	public Optional<FacturaEntity> getFacturaByPlaca(String placa)
	{
		return facturaRepositorioData.getFacturaByPlaca(placa);
	}
}
