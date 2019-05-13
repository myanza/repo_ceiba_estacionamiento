package co.com.ceiba.CeibaEstacionamiento.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaEstacionamiento.dominio.Factura;
import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;
import co.com.ceiba.CeibaEstacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.CeibaEstacionamiento.dominio.dto.MovilDTO;
import co.com.ceiba.CeibaEstacionamiento.persistencia.builders.FacturaBuilder;
import co.com.ceiba.CeibaEstacionamiento.persistencia.builders.FacturaDTOBuilder;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.FacturaEntity;
import co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio.FacturaRepositorio;
import co.com.ceiba.CeibaEstacionamiento.servicios.excepciones.*;

@Service
public class FacturaServicio 
{
	@Autowired
	public FacturaRepositorio facturaRepositorio;
	
	@Autowired
	private MovilServicio movilServicio;

	@Autowired
	private CostoEstadiaServicio costoEstadiaServicio;
	
	public static final int CANTIDAD_MAXIMA_CARROS = 20;
	public static final int CANTIDAD_MAXIMA_MOTOS = 10;
	public static final int MOVIL_NO_REGISTRADO = 0;
	public static final String SIN_ESPACIO_ESTACIONAMIENTO = "El estacionamiento no cuenta con espacio disponible.";
	public static final String SIN_AUTORIZACION_INGRESO = "No estpa autorizado a ingresar.";
	public static final String MOVIL_REGISTRADO = "Este movil ya se encuentra en el estacionamiento.";
	
	
	
	public List<FacturaDTO> getListadoMovilesEstacionamiento()
	{
		FacturaDTOBuilder facturaDTOBuilder = new FacturaDTOBuilder();
		List<FacturaEntity> facturasEntity = facturaRepositorio.getListadoMovilesEstacionamiento();
		List<FacturaDTO> listadoFacturaDTO = facturasEntity.stream().map(facturaDTOBuilder::convertToDTO).collect(Collectors.toList());
		return listadoFacturaDTO;
	}
	
	private Movil crearMovilDominio(MovilDTO movilDTO)
	{
		String placa = movilDTO.getPlaca();
		double cilindraje = movilDTO.getCilindraje();
		String tipoMovil = movilDTO.getTipoMovil();
		
		Movil movil = new Movil(placa, cilindraje, tipoMovil);
		return movil;
	}
	
	private Integer getCantidadMovilesByTipo(String tipoMovil) 
	{
		return facturaRepositorio.getCantidadMovilesByTipo(tipoMovil);
	}
	
	private void hayEspacioEstacionamiento(Movil movil)
	{
		if (movil.getTipoMovil() == "CARRO") 
		{
			Integer cantCarrosEstacionamiento = this.getCantidadMovilesByTipo("CARRO");
			if (cantCarrosEstacionamiento >= CANTIDAD_MAXIMA_CARROS) 
			{
				throw new SinEspacioException(SIN_ESPACIO_ESTACIONAMIENTO);
			}
		} 
		else 
		{
			Integer cantMotosEstacionamiento = this.getCantidadMovilesByTipo("MOTO");
			if (cantMotosEstacionamiento >= CANTIDAD_MAXIMA_MOTOS) 
			{
				throw new SinEspacioException(SIN_ESPACIO_ESTACIONAMIENTO);
			}

		}
	}
	
	private boolean esDiaPermitido() 
	{
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String day = simpleDateformat.format(now);
        if(day == "Sunday" || day == "Monday")
        	return true;
        else 
        	return false;
	}
	
	private void ingresaEnDiaPermitido(Movil movil) 
	{
		String placa = movil.getPlaca().toUpperCase();
		
		if(placa.startsWith("A") && !esDiaPermitido())
		{
			throw new SinAutorizacionException(SIN_AUTORIZACION_INGRESO);
		}
	}
	
	private Integer getMovilEstacionamientoByPlaca(String placa) 
	{
		return facturaRepositorio.getMovilEstacionamientoByPlaca(placa);
	}
	
	private void estaEnEstacionamiento(Movil movil)
	{
		String placa = movil.getPlaca();
		
		if(this.getMovilEstacionamientoByPlaca(placa) != MOVIL_NO_REGISTRADO)
		{
			throw new MovilRegistradoException(MOVIL_REGISTRADO);
		}
	}
	
	private void crearMovilBase(Movil movil)
	{
		String placa = movil.getPlaca();
		
		if (movilServicio.getMovilByPlaca(placa) == null)
				movilServicio.registrarMovil(movil);
	}
	
	public boolean crearFactura(Movil movil)
	{
		FacturaBuilder facturaBuilder = new FacturaBuilder();
		Date actual = new Date();
		Factura factura = new Factura(actual, movil);
		
		FacturaEntity facturaEntity = facturaBuilder.convertirAEntity(factura);
		FacturaEntity fac = facturaRepositorio.save(facturaEntity);
		if(fac != null) return true;
		else return false;
	}
	
	
	public boolean registrarMovil(MovilDTO movilDTO) 
	{
		Movil movil = crearMovilDominio(movilDTO);
		hayEspacioEstacionamiento(movil);
		ingresaEnDiaPermitido(movil);
		estaEnEstacionamiento(movil);
		crearMovilBase(movil);
		return crearFactura(movil);
	}
	
}
