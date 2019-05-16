package co.com.ceiba.ceibaestacionamiento.integracion;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.CeibaEstacionamientoApplication;
import co.com.ceiba.ceibaestacionamiento.dbconfiguracion.H2JpaConfig;
import co.com.ceiba.ceibaestacionamiento.dominio.Estacionamiento;
import co.com.ceiba.ceibaestacionamiento.servicios.CostoEstadiaServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.FacturaServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.MovilServicioImpl;
import co.com.ceiba.estacionamiento.dominio.CalendarioVigilante;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CeibaEstacionamientoApplication.class, H2JpaConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstacionamientoServiciosTest 
{
	@Autowired
	FacturaServicioImpl facturaServicio;

	@Autowired
	MovilServicioImpl movilServicio;

	@Autowired
	CostoEstadiaServicioImpl costoEstadiaServicio;
	
	private final static int CANT_MAX_CARROS = 20;
	private final static int CANT_MAX_MOTOS = 10;
	
	@Test
	public void ingresar20Carros() 
	{
		// Assert
		Estacionamiento estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);
		
		for(int i = 0; i < CANT_MAX_CARROS; i++)
		{
			MovilTestDataBuilder movilTestDataBuildr
			Movil carro = 
		}
		
		for (int numeroCarro = 0; numeroCarro < MAX_NUM_CARROS; numeroCarro++) 
		{

			Vehiculo carro = new CarroTestDataBuilder().withPlaca("CXX-" + numeroCarro).build();

			vigilante.ingresarVehiculo(carro);
		}
		// Act
		int cantidadCarros = ticketParqueaderoServicio.verificarCupoVehiculo(EnumTipoVehiculo.CARRO.name());
		// Assert
		Assert.assertEquals("La cantidad de carros ingresados no coincide con los registrados.", MAX_NUM_CARROS,
				cantidadCarros);
	}
}
