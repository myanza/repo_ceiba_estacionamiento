package co.com.ceiba.CeibaEstacionamiento.unitarias;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/*import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;*/
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;*/

import co.com.ceiba.CeibaEstacionamiento.dominio.Estacionamiento;
import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;
import co.com.ceiba.CeibaEstacionamiento.servicios.CostoEstadiaServicioImpl;
import co.com.ceiba.CeibaEstacionamiento.servicios.FacturaServicioImpl;
import co.com.ceiba.CeibaEstacionamiento.servicios.MovilServicioImpl;
import co.com.ceiba.CeibaEstacionamiento.servicios.excepciones.SinAutorizacionException;
import co.com.ceiba.CeibaEstacionamiento.servicios.excepciones.SinEspacioException;
import co.com.ceiba.CeibaEstacionamiento.testdatabuilder.MovilTestDataBuilder;

//@AutoConfigureTestDatabase(replace=Replace.NONE)
//@RunWith(SpringRunner.class)
//@DataJpaTest
public class FacturaTest 
{
	//@Autowired
	//@InjectMocks
	private FacturaServicioImpl facturaServicio;
	
	//@Mock
	private MovilServicioImpl movilServicio;
	
	//@Mock
	private CostoEstadiaServicioImpl costoEstadiaServicio;
	
	private Estacionamiento estacionamiento;
	
	private static final int CANT_MOVILES_ESTACIONAMIENTO = 0;
	private static final int MOVIL_NO_REGISTRADO = 0;
	public static final int CANTIDAD_MAXIMA_CARROS = 20;
	public static final int CANTIDAD_MAXIMA_MOTOS = 10;
	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_MOTO_EXTENDIDO = 2000;
	public static final String SIN_ESPACIO_ESTACIONAMIENTO = "El estacionamiento no cuenta con espacio disponible.";
	public static final String SIN_AUTORIZACION_INGRESO = "No esta autorizado a ingresar.";
	public static final String MOVIL_REGISTRADO = "Este movil ya se encuentra en el estacionamiento.";
	
	
	private void inicializarCostosEstadias()
	{
		costoEstadiaServicio = Mockito.mock(CostoEstadiaServicioImpl.class);
		
		Mockito.doAnswer(new Answer<Double>()
		{
			@Override
			public Double answer(InvocationOnMock invocation)
			{
				Double valor = 0.0;
				
				Object[] arguments = invocation.getArguments();
				String tipoMovil = arguments[0].toString();
				String tipoPago = arguments[1].toString();
				String tiempoEstadia = arguments[2].toString();
				
				if (tipoMovil == "CARRO" && tipoPago == "NORMAL" && tiempoEstadia == "HORA")
					valor = VALOR_HORA_CARRO;
				else if(tipoMovil == "MOTO" && tipoPago == "NORMAL" && tiempoEstadia == "HORA")
					valor = VALOR_HORA_MOTO;
				else if(tipoMovil == "CARRO" && tipoPago == "NORMAL" && tiempoEstadia == "DIA")
					valor = VALOR_DIA_CARRO;
				else if(tipoMovil == "MOTO" && tipoPago == "NORMAL" && tiempoEstadia == "DIA")
					valor = VALOR_DIA_MOTO;
				else if(tipoMovil == "MOTO" && tipoPago == "EXTENDIDO")
					valor = VALOR_MOTO_EXTENDIDO;
				return valor;
			}
		}).when(costoEstadiaServicio).getCostoEstadiaBy(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
	private void inicializarFacturaServicio()
	{
		facturaServicio = Mockito.mock(FacturaServicioImpl.class);
		//facturaServicio = new FacturaServicio();
		
		Mockito.when(facturaServicio.getCantidadMovilesByTipo(Mockito.anyString())).thenReturn(CANT_MOVILES_ESTACIONAMIENTO);
		//Mockito.when(facturaServicio.esDiaPermitido()).thenReturn(true);
		Mockito.when(facturaServicio.getMovilEstacionamientoByPlaca(Mockito.anyString())).thenReturn(MOVIL_NO_REGISTRADO);
		Mockito.when(facturaServicio.crearFactura(Mockito.any())).thenReturn(true);
		
	}
	
	private void inicializarMovil()
	{
		Movil movil = Mockito.mock(Movil.class);
		
		movilServicio = Mockito.mock(MovilServicioImpl.class);
		Mockito.when(movilServicio.registrarMovil(Mockito.any())).thenReturn(true);
		Mockito.when(movilServicio.getMovilByPlaca(Mockito.anyString())).thenReturn(movil);
		//Mockito.when(movilServicio.getMovilByPlaca(Mockito.anyString())).thenReturn(null);
	}
	
	@Before
	public void setUp()
	{
		//MockitoAnnotations.initMocks(this);
		inicializarCostosEstadias();
		inicializarFacturaServicio();
		inicializarMovil();
		estacionamiento = new Estacionamiento(facturaServicio, movilServicio, costoEstadiaServicio);
	}
	
	@Test
	public void registroCarroExitosoTest() 
	{
		// arrange
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil carro = movilTestDataBuilder.withPlaca("FGH-123").withCilindraje(-1).withTipoMovil("CARRO").build();

		boolean resultado = estacionamiento.registrarMovil(carro);

		Assert.assertTrue("No se pudo registrar el carro.", resultado);
	}
	
	@Test
	public void registroMotoExitosoTest() 
	{
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil moto = movilTestDataBuilder.withPlaca("QWE-678").withCilindraje(250).withTipoMovil("MOTO").build();

		boolean resultado = estacionamiento.registrarMovil(moto);

		Assert.assertTrue("No se pudo registrar la moto.", resultado);
	}
	
	@Test
	public void noHayEspacioParaCarroTest() 
	{
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil carro = movilTestDataBuilder.withPlaca("FGH-123").withCilindraje(-1).withTipoMovil("CARRO").build();
		
		Mockito.when(facturaServicio.getCantidadMovilesByTipo(Mockito.anyString())).thenReturn(CANTIDAD_MAXIMA_CARROS);

		try 
		{
			estacionamiento.registrarMovil(carro);
			fail();
		} 
		catch (SinEspacioException e) 
		{
			Assert.assertEquals(SIN_ESPACIO_ESTACIONAMIENTO, e.getMessage());
		}
	}
	
	@Test
	public void noHayEspacioParaMotoTest() 
	{
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil moto = movilTestDataBuilder.withPlaca("FGH-123").withCilindraje(150).withTipoMovil("MOTO").build();
		
		Mockito.when(facturaServicio.getCantidadMovilesByTipo(Mockito.anyString())).thenReturn(CANTIDAD_MAXIMA_MOTOS);

		try 
		{
			estacionamiento.registrarMovil(moto);
			fail();
		} 
		catch (SinEspacioException e) 
		{
			Assert.assertEquals(SIN_ESPACIO_ESTACIONAMIENTO, e.getMessage());
		}
	}
	
	@Test
	public void noIngresaEnDiaPermitidoTest() 
	{
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil carro = movilTestDataBuilder.withPlaca("AGH-123").withCilindraje(-1).withTipoMovil("MOTO").build();
		
		Estacionamiento esta = Mockito.mock(Estacionamiento.class);
		
		Mockito.when(esta.esDiaPermitido()).thenReturn(false);

		try 
		{
			estacionamiento.registrarMovil(carro);
			fail();
		} 
		catch (SinAutorizacionException e) 
		{
			Assert.assertEquals(SIN_AUTORIZACION_INGRESO, e.getMessage());
		}
	}
}
