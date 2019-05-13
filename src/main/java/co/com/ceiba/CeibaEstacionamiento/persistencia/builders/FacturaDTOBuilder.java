package co.com.ceiba.CeibaEstacionamiento.persistencia.builders;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import co.com.ceiba.CeibaEstacionamiento.dominio.Factura;
import co.com.ceiba.CeibaEstacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.FacturaEntity;

public final class FacturaDTOBuilder 
{
	private ModelMapper modelMapper = new ModelMapper();
	
	public FacturaDTOBuilder() 
	{
		System.out.println("--ENTRE AL CONSTRUCTOR");
		PropertyMap<FacturaEntity, FacturaDTO> mapFacturas = new PropertyMap<FacturaEntity, FacturaDTO>() 
		{
			protected void configure() 
			{
				System.out.println("--ENTRE AL CONFIGURE");
				map().setFac_fechaIngreso(source.getFacFechaIngreso());
				map().setFac_fechaSalida(source.getFacFechaSalida());
				map().setMov_placa(source.getMovil().getMovPlaca());
				map().setTipoMovil(source.getMovil().getMovTipoMovil());
				map().setCilindraje(source.getMovil().getMovCilindraje());
				System.out.println("---PASE EL CONFIGURE");
			}
		};
		System.out.println("--ANTES DEL ADDMAPPING");
		modelMapper.addMappings(mapFacturas);
	}
	
	public FacturaDTO convertToDTO(FacturaEntity facturaEntity) 
	{
		return modelMapper.map(facturaEntity, FacturaDTO.class);
	}
	
	public FacturaDTO convertToDTO(Factura factura) 
	{
		return modelMapper.map(factura, FacturaDTO.class);
	}

}
