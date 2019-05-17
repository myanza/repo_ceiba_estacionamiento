package co.com.ceiba.ceibaestacionamiento.unitarias;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.testdatabuilder.FacturaTestDataBuilder;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.MovilTestDataBuilder;
import co.com.ceiba.ceibaestacionamiento.dominio.Estacionamiento;
import co.com.ceiba.ceibaestacionamiento.dominio.Factura;
import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.servicios.CostoEstadiaServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.FacturaServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.MovilServicioImpl;
import co.com.ceiba.ceibaestacionamiento.servicios.excepciones.SinAutorizacionException;
import co.com.ceiba.ceibaestacionamiento.servicios.excepciones.SinEspacioException;

//@AutoConfigureTestDatabase(replace=Replace.NONE)
@RunWith(SpringRunner.class)
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
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_MOTO_EXTENDIDO = 2000;
	public static final String SIN_ESPACIO_ESTACIONAMIENTO = "El estacionamiento no cuenta con espacio disponible.";
	public static final String SIN_AUTORIZACION_INGRESO = "No esta autorizado a ingresar.";
	public static final String MOVIL_REGISTRADO = "Este movil ya se encuentra en el estacionamiento.";
	public static final int MOVIL_ENCONTRADO = 1;
	public static final int UNA_HORA_MENOS = -1;
	public static final int UN_DIA_MENOS = -24;
	public static final int SIN_CILINDRAJE = -1;
	public static final String MOTO = "MOTO";
	public static final String CARRO = "CARRO";
	
	
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
				
				if (tipoMovil == CARRO && tipoPago == "NORMAL" && tiempoEstadia == "HORA")
					valor = VALOR_HORA_CARRO;
				else if(tipoMovil == MOTO && tipoPago == "NORMAL" && tiempoEstadia == "HORA")
					valor = VALOR_HORA_MOTO;
				else if(tipoMovil == CARRO && tipoPago == "NORMAL" && tiempoEstadia == "DIA")
					valor = VALOR_DIA_CARRO;
				else if(tipoMovil == MOTO && tipoPago == "NORMAL" && tiempoEstadia == "DIA")
					valor = VALOR_DIA_MOTO;
				else if(tipoMovil == MOTO && tipoPago == "EXTENDIDO" && tiempoEstadia == "NO_APLICA")
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
		//Mockito.when(movilServicio.registrarMovil(Mockito.any())).thenReturn(true);
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
		Movil carro = movilTestDataBuilder.withPlaca("FGH-123").withCilindraje(-1).withTipoMovil(CARRO).build();
		
		//Estacionamiento spyEstacionamiento = Mockito.spy(estacionamiento);
		
		boolean resultado = estacionamiento.registrarMovil(carro);

		Assert.assertTrue("No se pudo registrar el carro.", resultado);
		
		//verify(spyEstacionamiento, times(1)).registrarMovil(carro);
	}
	
	@Test
	public void registroMotoExitosoTest() 
	{
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil moto = movilTestDataBuilder.withPlaca("QWE-678").withCilindraje(250).withTipoMovil(MOTO).build();

		boolean resultado = estacionamiento.registrarMovil(moto);

		Assert.assertTrue("No se pudo registrar la moto.", resultado);
	}
	
	@Test
	public void noHayEspacioParaCarroTest() 
	{
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil carro = movilTestDataBuilder.withPlaca("FGH-123").withCilindraje(-1).withTipoMovil(CARRO).build();
		
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
		Movil moto = movilTestDataBuilder.withPlaca("FGH-123").withCilindraje(150).withTipoMovil(MOTO).build();
		
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
		Movil carro = movilTestDataBuilder.withPlaca("AGH-123").withCilindraje(-1).withTipoMovil(MOTO).build();
		
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
	
	@Test
	public void cobroHoraCarroTest() 
	{
		// arrange
	    Movil movil = arrangeEliminarMovil(CARRO, "WRR-678", SIN_CILINDRAJE, UNA_HORA_MENOS);
		// act
		Factura factObtenida = estacionamiento.eliminarMovil(movil.getPlaca());
		// assert
		Assert.assertEquals(VALOR_HORA_CARRO, factObtenida.getValor(), 0.0);
	}
	
	@Test
	public void cobroHoraMotoTest()
	{
		// arrange
		Movil movil = arrangeEliminarMovil(MOTO, "BVN-123", 150, UNA_HORA_MENOS);		
		// act
		Factura factObtenida = estacionamiento.eliminarMovil(movil.getPlaca());
		// assert
		Assert.assertEquals(VALOR_HORA_MOTO, factObtenida.getValor(), 0.0);
	}
	
	
	@Test
	public void cobroExtendidoMoto500Test() 
	{
		// arrange
		Movil movil = arrangeEliminarMovil(MOTO, "TTY-678", 550, 0);	
		// act
		Factura factObtenida = estacionamiento.eliminarMovil(movil.getPlaca());
		// assert
		Assert.assertEquals(VALOR_MOTO_EXTENDIDO, factObtenida.getValor(), 0.0);
	}
	
	@Test
	public void cobroDiaCarroTest() 
	{
		// arrange
	    Movil movil = arrangeEliminarMovil(CARRO, "HJG-345", SIN_CILINDRAJE, UN_DIA_MENOS);
		// act
		Factura factObtenida = estacionamiento.eliminarMovil(movil.getPlaca());
		// assert
		Assert.assertEquals(VALOR_DIA_CARRO, factObtenida.getValor(), 0.0);
	}
	
	@Test
	public void cobroDiaMotoTest() 
	{
		// arrange
	    Movil movil = arrangeEliminarMovil(MOTO, "KJL-670", 200, UN_DIA_MENOS);
		// act
		Factura factObtenida = estacionamiento.eliminarMovil(movil.getPlaca());
		// assert
		Assert.assertEquals(VALOR_DIA_MOTO, factObtenida.getValor(), 0.0);
	}
	
	@Test
	public void cobroCarroUnDiaTresHorasTest() 
	{
		// arrange
		int undiatreshorasMenos = -27;
		int valor_esperado = 11000;
	    Movil movil = arrangeEliminarMovil(CARRO, "KJL-670", SIN_CILINDRAJE, undiatreshorasMenos);
		// act
		Factura factObtenida = estacionamiento.eliminarMovil(movil.getPlaca());
		// assert
		Assert.assertEquals(valor_esperado, factObtenida.getValor(), 0.0);
	}
	
	@Test
	public void cobroMotoDiezHoras650CCTest() 
	{
		// arrange
		int diezhorasMenos = -10;
		int valor_esperado = 6000;
	    Movil movil = arrangeEliminarMovil(MOTO, "VBN-123", 650, diezhorasMenos);
		// act
		Factura factObtenida = estacionamiento.eliminarMovil(movil.getPlaca());
		// assert
		Assert.assertEquals(valor_esperado, factObtenida.getValor(), 0.0);
	}
	
	private Movil arrangeEliminarMovil(String tipoMovil, String placa, double cilindraje, int tiempoLapso)
	{
		// arrange
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil movil = movilTestDataBuilder.withTipoMovil(tipoMovil).withPlaca(placa).withCilindraje(cilindraje).build();
		
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder();
		
		Date fecha = new Date();
		
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fecha);
	    calendar.add(Calendar.HOUR_OF_DAY, tiempoLapso);
	    fecha = calendar.getTime();
	    
		Factura factura = facturaTestDataBuilder.withFechaIngreso(fecha).withMovil(movil).build();
		
		Mockito.when(facturaServicio.getMovilEstacionamientoByPlaca(Mockito.anyString())).thenReturn(MOVIL_ENCONTRADO);
		
		Mockito.when(facturaServicio.getFacturaByPlaca(Mockito.anyString())).thenReturn(factura);
		
		Mockito.when(facturaServicio.actualizarFactura(Mockito.any()))
		.thenAnswer(new Answer<Factura>() 
		{
			@Override
			public Factura answer(InvocationOnMock invocation) throws Throwable 
			{
				Object[] args = invocation.getArguments();
				return (Factura) args[0];
			}
		});
		
		return movil;
	}
	
}
