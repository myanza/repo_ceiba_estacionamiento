package co.com.ceiba.ceibaestacionamiento.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.dominio.Estacionamiento;
import co.com.ceiba.ceibaestacionamiento.dominio.Factura;
import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.MovilDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.FacturaRepositorio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.CostoEstadiaServicio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.FacturaServicio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.MovilServicio;
import co.com.ceiba.ceibaestacionamiento.persistencia.builders.FacturaBuilder;
import co.com.ceiba.ceibaestacionamiento.persistencia.builders.FacturaDTOBuilder;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.FacturaEntity;

@Service
public class FacturaServicioImpl implements FacturaServicio
{
	@Autowired
	public FacturaRepositorio facturaRepositorio;
	
	@Autowired
	public FacturaServicioImpl(FacturaRepositorio facturaRepositorio) 
	{
		this.facturaRepositorio = facturaRepositorio;
	}
	
	@Override
	public List<FacturaDTO> getListadoMovilesEstacionamiento()
	{
		FacturaDTOBuilder facturaDTOBuilder = new FacturaDTOBuilder();
		List<FacturaEntity> facturasEntity = facturaRepositorio.getListadoMovilesEstacionamiento();
		return facturasEntity.stream().map(facturaDTOBuilder::convertToDTO).collect(Collectors.toList());
	}
	
	public Movil crearMovilDominio(MovilDTO movilDTO)
	{
		String placa = movilDTO.getPlaca();
		double cilindraje = movilDTO.getCilindraje();
		String tipoMovil = movilDTO.getTipoMovil();
		
		return new Movil(placa, cilindraje, tipoMovil);
	}
	
	@Override
	public Integer getCantidadMovilesByTipo(String tipoMovil) 
	{
		return facturaRepositorio.getCantidadMovilesByTipo(tipoMovil);
	}
	
	@Override
	public Integer getMovilEstacionamientoByPlaca(String placa) 
	{
		return facturaRepositorio.getMovilEstacionamientoByPlaca(placa);
	}
	
	@Override
	public boolean crearFactura(Movil movil)
	{
		FacturaBuilder facturaBuilder = new FacturaBuilder();
		
		Date actual = new Date();
		Factura factura = new Factura(actual, movil);
		
		FacturaEntity facturaEntity = facturaBuilder.convertirAEntity(factura);
		FacturaEntity fac = facturaRepositorio.save(facturaEntity);
		return fac != null;
	}
	
	@Override
	public boolean registrarMovil(MovilDTO movilDTO, MovilServicio movilServicio, CostoEstadiaServicio costoEstadiaServicio) 
	{
		Movil movil = crearMovilDominio(movilDTO);
		
		Estacionamiento estacionamiento = new Estacionamiento(this, movilServicio, costoEstadiaServicio);
		return estacionamiento.registrarMovil(movil);
	}
	
	@Override
	public Factura getFacturaByPlaca(String placa) 
	{
		Factura factura = null;
		FacturaBuilder facturaBuilder = new FacturaBuilder();
		
		Optional<FacturaEntity> facturasEntity = facturaRepositorio.getFacturaByPlaca(placa);
		if (facturasEntity.isPresent()) 
		{
			FacturaEntity facturaEntity = facturasEntity.get();
			factura = facturaBuilder.convertirADominio(facturaEntity);
		}
		return factura;
	}

	@Override
	public Factura actualizarFactura(Factura factura) 
	{
		Factura facturaNueva = null;
		FacturaBuilder facturaBuilder = new FacturaBuilder();
		
		String placa = factura.getMovil().getPlaca();
		Optional<FacturaEntity> facturasEntity = facturaRepositorio.getFacturaByPlaca(placa);
		
		if (facturasEntity.isPresent()) 
		{
			FacturaEntity facturaEntity = facturasEntity.get();
			facturaEntity.setFacFechaSalida(factura.getFechaSalida());
			facturaEntity.setFacValor(factura.getValor());
			facturaEntity = facturaRepositorio.save(facturaEntity);
			
			facturaNueva = facturaBuilder.convertirADominio(facturaEntity);
		}
		return facturaNueva;
	}

	@Override
	public FacturaDTO eliminarMovil(String placa, MovilServicio movilServicio, CostoEstadiaServicio costoEstadiaServicio) 
	{
		FacturaDTOBuilder facturaDTOBuilder = new FacturaDTOBuilder();
		
		Estacionamiento estacionamiento = new Estacionamiento(this, movilServicio, costoEstadiaServicio);
		Factura factura = estacionamiento.eliminarMovil(placa);
		
		FacturaDTO facturaDTO = facturaDTOBuilder.convertToDTO(factura);
		return facturaDTO;
	}
	
}
