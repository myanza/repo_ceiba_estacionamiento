package co.com.ceiba.CeibaEstacionamiento.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaEstacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.CeibaEstacionamiento.persistencia.builders.FacturaDTOBuilder;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.FacturaEntity;
import co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio.FacturaRepositorio;

@Service
public class FacturaServicio 
{
	@Autowired
	public FacturaRepositorio facturaRepositorio;
	
	public List<FacturaDTO> getListadoMovilesEstacionamiento()
	{
		FacturaDTOBuilder facturaDTOBuilder = new FacturaDTOBuilder();
		List<FacturaEntity> facturasEntity = facturaRepositorio.getListadoMovilesEstacionamiento();
		List<FacturaDTO> listadoFacturaDTO = facturasEntity.stream().map(FacturaDTOBuilder::convertToDTO).collect(Collectors.toList());
		return listadoFacturaDTO;
	}
	
	public Integer getCantidadMovilesByTipo(String tipoMovil) 
	{
		return facturaRepositorio.getCantidadMovilesByTipo(tipoMovil);
	}
	
	public Integer getMovilEstacionamientoByPlaca(String placa) 
	{
		return facturaRepositorio.getMovilEstacionamientoByPlaca(placa);
	}
}
