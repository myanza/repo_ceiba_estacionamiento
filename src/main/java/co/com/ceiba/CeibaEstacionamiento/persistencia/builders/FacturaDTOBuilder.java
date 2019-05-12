package co.com.ceiba.CeibaEstacionamiento.persistencia.builders;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import co.com.ceiba.CeibaEstacionamiento.dominio.Factura;
import co.com.ceiba.CeibaEstacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.FacturaEntity;

public final class FacturaDTOBuilder 
{
	private static ModelMapper modelMapper = new ModelMapper();
	
	public FacturaDTOBuilder() 
	{
		PropertyMap<FacturaEntity, FacturaDTO> mapFacturas = new PropertyMap<FacturaEntity, FacturaDTO>() 
		{
			protected void configure() 
			{
				map().setFac_fechaIngreso(source.getFacFechaIngreso());
				map().setFac_fechaSalida(source.getFacFechaSalida());
				map().setMov_placa(source.getMovil().getMovPlaca());
				map().setTipoMovil(source.getMovil().getMovTipoMovil());
				map().setCilindraje(source.getMovil().getMovCilindraje());
			}
		};
		modelMapper.addMappings(mapFacturas);
	}
	
	public static FacturaDTO convertToDTO(FacturaEntity facturaEntity) 
	{
		return modelMapper.map(facturaEntity, FacturaDTO.class);
	}
	
	public static FacturaDTO convertToDTO(Factura factura) 
	{
		return modelMapper.map(factura, FacturaDTO.class);
	}

}
