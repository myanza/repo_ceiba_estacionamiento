package co.com.ceiba.CeibaEstacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.ceiba.CeibaEstacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.CeibaEstacionamiento.dominio.dto.MovilDTO;
import co.com.ceiba.CeibaEstacionamiento.servicios.*;

@RestController
@RequestMapping("/estacionamiento")
@CrossOrigin(origins = "http://localhost:3306")

public class EstacionamientoController 
{
	@Autowired
	private FacturaServicio facturaServicio;
	
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
		facturaServicio.registrarMovil(movilDTO);
	}
}
