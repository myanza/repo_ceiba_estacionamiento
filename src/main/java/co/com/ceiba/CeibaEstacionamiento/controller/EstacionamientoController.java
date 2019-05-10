package co.com.ceiba.CeibaEstacionamiento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.CeibaEstacionamiento.servicios.*;

@RestController
@RequestMapping("/bd_estacionamiento")
@CrossOrigin(origins = "http://localhost:3306")

public class EstacionamientoController 
{
	@Autowired
	private ParqueaderoServicio parqueaderoSevicio;
}
