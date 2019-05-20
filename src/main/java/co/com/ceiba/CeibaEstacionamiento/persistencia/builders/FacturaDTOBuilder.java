package co.com.ceiba.ceibaestacionamiento.persistencia.builders;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import co.com.ceiba.ceibaestacionamiento.dominio.Factura;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.FacturaEntity;

public final class FacturaDTOBuilder 
{
	private ModelMapper modelMapper = new ModelMapper();
	
	public FacturaDTOBuilder() 
	{
		PropertyMap<FacturaEntity, FacturaDTO> mapFacturas = new PropertyMap<FacturaEntity, FacturaDTO>() 
		{
			protected void configure() 
			{
				map().setFacFechaIngreso(source.getFacFechaIngreso());
				map().setFacFechaSalida(source.getFacFechaSalida());
				map().setFacValor(source.getFacValor());
				map().setMovPlaca(source.getMovil().getMovPlaca());
				map().setTipoMovil(source.getMovil().getMovTipoMovil());
				map().setCilindraje(source.getMovil().getMovCilindraje());
			}
		};
		modelMapper.addMappings(mapFacturas);
	}
	
	public FacturaDTO convertToDTO(FacturaEntity facturaEntity) 
	{
		return modelMapper.map(facturaEntity, FacturaDTO.class);
	}
	
	public FacturaDTO convertToDTO(Factura factura) 
	{
		PropertyMap<Factura, FacturaDTO> mapFacturas = new PropertyMap<Factura, FacturaDTO>() 
		{
			protected void configure() 
			{
				map().setFacFechaIngreso(source.getFechaIngreso());
				map().setFacFechaSalida(source.getFechaSalida());
				map().setFacValor(source.getValor());
				map().setMovPlaca(source.getMovil().getPlaca());
				map().setTipoMovil(source.getMovil().getTipoMovil());
				map().setCilindraje(source.getMovil().getCilindraje());
			}
		};
		TypeMap<Factura, FacturaDTO> fact = modelMapper.addMappings(mapFacturas);
		return fact.map(factura);
	}

}
