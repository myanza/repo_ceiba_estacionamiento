package co.com.ceiba.ceibaestacionamiento.integracion;

import static org.junit.Assert.fail;

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
import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.servicios.CostoEstadiaServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.FacturaServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.MovilServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.excepciones.SinEspacioException;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.MovilTestDataBuilder;


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
	private final static int SIN_CILINDRAJE = -1;
	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_MOTO_EXTENDIDO = 2000;
	private static final String CARRO = "CARRO";
	private static final String MOTO = "MOTO";
	public static final String SIN_ESPACIO_ESTACIONAMIENTO = "El estacionamiento no cuenta con espacio disponible.";
	
	@Test
	public void primerRegistro20CarrosExitosoTest() 
	{
		// Assert
		Estacionamiento estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);
		
		for(int i = 0; i < CANT_MAX_CARROS; i++)
		{
			MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
			Movil carro = movilTestDataBuilder.withPlaca("VBN-"+i).withCilindraje(SIN_CILINDRAJE).withTipoMovil(CARRO).build();
			estacionamiento.registrarMovil(carro);
		}
		// Act
		int cantCarros = facturaServicio.getCantidadMovilesByTipo(CARRO);
		// Assert
		Assert.assertEquals("No fue posible registrar todos los carros.", CANT_MAX_CARROS, cantCarros);
	}
	
	@Test
	public void primerRegistro10MotosExitosoTest() 
	{
		// Assert
		Estacionamiento estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);
		
		for(int i = 0; i < CANT_MAX_MOTOS; i++)
		{
			MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
			Movil moto = movilTestDataBuilder.withPlaca("SWH-"+i).withCilindraje(150).withTipoMovil(MOTO).build();
			estacionamiento.registrarMovil(moto);
		}
		// Act
		int cantMotos = facturaServicio.getCantidadMovilesByTipo(MOTO);
		// Assert
		Assert.assertEquals("No fue posible registrar todas las motos", CANT_MAX_MOTOS, cantMotos);
	}
	
	@Test
	public void primerSinEspacioParaCarroTest()
	{
		// arrange
		Estacionamiento estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);
		
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil carro = movilTestDataBuilder.withPlaca("VBN-456").withCilindraje(SIN_CILINDRAJE).withTipoMovil(CARRO).build();
		// act
		try 
		{
			estacionamiento.registrarMovil(carro);
			fail();
		} 
		catch (SinEspacioException e) 
		{
			// assert
			Assert.assertEquals(SIN_ESPACIO_ESTACIONAMIENTO, e.getMessage());
		}
	}
	
	@Test
	public void primerSinEspacioParaMotoTest()
	{
		// arrange
		Estacionamiento estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);
		
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil moto = movilTestDataBuilder.withPlaca("GGH-123").withCilindraje(150).withTipoMovil(MOTO).build();
		// act
		try 
		{
			estacionamiento.registrarMovil(moto);
			fail();
		} 
		catch (SinEspacioException e) 
		{
			// assert
			Assert.assertEquals(SIN_ESPACIO_ESTACIONAMIENTO, e.getMessage());
		}
	}
	
	@Test
	public void segundoEliminar20CarrosTest() 
	{
		Estacionamiento estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);

		for (int i = 0; i < CANT_MAX_CARROS; i++) 
		{
			estacionamiento.eliminarMovil("VBN-" + i);
		}
		// Act
		int cantCarros = facturaServicio.getCantidadMovilesByTipo(CARRO);
		// Assert
		Assert.assertEquals("No fue posible eliminar todos los carros", 0, cantCarros);
	}

	@Test
	public void segundoEliminar10MotosTets() 
	{		
		Estacionamiento estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);

		for (int i = 0; i < CANT_MAX_MOTOS; i++) 
		{
			estacionamiento.eliminarMovil("SWH-" + i);
		}
		// Act
		int cantMotos = facturaServicio.getCantidadMovilesByTipo(MOTO);
		// Assert
		Assert.assertEquals("No fue posible eliminar todas las motos", 0, cantMotos);
	}
	
	@Test
	public void segundoObtenerValorHoraCarroTest() 
	{
		double valor = costoEstadiaServicio.getCostoEstadiaBy(CARRO, "NORMAL", "HORA");
		// assert
		Assert.assertEquals("El valor de la hora carro no coincide.", VALOR_HORA_CARRO, valor, 0.0);
	}
	
	@Test
	public void tercerObtenerValorDiaCarroTest() 
	{
		double valor = costoEstadiaServicio.getCostoEstadiaBy(CARRO, "NORMAL", "DIA");
		// assert
		Assert.assertEquals("El valor del dia carro no coincide.",VALOR_DIA_CARRO, valor, 0.0);
	}

	@Test
	public void tercerObtenerValorHoraMotoTest() 
	{
		double valor = costoEstadiaServicio.getCostoEstadiaBy(MOTO, "NORMAL", "HORA");
		// assert
		Assert.assertEquals("El valor de la hora moto no coincide.",VALOR_HORA_MOTO, valor, 0.0);

	}

	@Test
	public void tercerObtenerValorDiaMotoTest() 
	{
		double valor = costoEstadiaServicio.getCostoEstadiaBy(MOTO, "NORMAL", "DIA");
		// assert
		Assert.assertEquals("El valor del dia carro no coincide.",VALOR_DIA_MOTO, valor, 0.0);
	}

	@Test
	public void tercerObteneerValorMotoMayor500CCTest() 
	{
		double valor = costoEstadiaServicio.getCostoEstadiaBy(MOTO, "EXTENDIDO", "NO_APLICA");
		// assert
		Assert.assertEquals("El valor extendido de una moto con cilindraje mayor a 500 no coincide.",VALOR_MOTO_EXTENDIDO, valor, 0.0);
	}
}
