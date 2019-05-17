package co.com.ceiba.ceibaestacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.MovilDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.CostoEstadiaServicio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.FacturaServicio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.MovilServicio;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/estacionamiento")
@CrossOrigin(origins = "http://localhost:4200")

public class EstacionamientoController 
{
	@Autowired
	private FacturaServicio facturaServicio;
	
	@Autowired
	private CostoEstadiaServicio costoEstadiaServicio;
	
	@Autowired
	private MovilServicio movilServicio;
	
	@GetMapping(value = "/listadomoviles")
	@ResponseBody
	public List<FacturaDTO> getListadoMovilesEstacionamiento() 
	{
		return facturaServicio.getListadoMovilesEstacionamiento();
	}
	
	@PostMapping(value = "/registrarmovil")
	@ResponseStatus(value = HttpStatus.OK)
	public void registrarMovil(@RequestBody MovilDTO movilDTO) 
	{
		facturaServicio.registrarMovil(movilDTO, movilServicio, costoEstadiaServicio);
	}
	
	@PostMapping(value = "/eliminarmovil")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody FacturaDTO eliminarMovil(@RequestBody String placa) 
	{
		return facturaServicio.eliminarMovil(placa, movilServicio, costoEstadiaServicio);
	}
}
