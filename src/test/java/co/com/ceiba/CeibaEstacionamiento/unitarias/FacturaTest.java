package co.com.ceiba.CeibaEstacionamiento.unitarias;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;
import co.com.ceiba.CeibaEstacionamiento.dominio.dto.MovilDTO;
import co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio.FacturaRepositorio;
import co.com.ceiba.CeibaEstacionamiento.servicios.CostoEstadiaServicio;
import co.com.ceiba.CeibaEstacionamiento.servicios.FacturaServicio;
import co.com.ceiba.CeibaEstacionamiento.servicios.MovilServicio;
import co.com.ceiba.CeibaEstacionamiento.testdatabuilder.MovilDTOTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FacturaTest 
{
	@Autowired
	@InjectMocks
	private FacturaServicio facturaServicio;
	
	@Mock
	private MovilServicio movilServicio;
	
	@Mock
	private CostoEstadiaServicio costoEstadiaServicio;
	
	@Mock
	private FacturaRepositorio facturaRepositorio;
	
	private static final int CANT_MOVILES_ESTACIONAMIENTO = 0;
	private static final int MOVIL_NO_REGISTRADO = 0;
	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_MOTO_EXTENDIDO = 2000;
	
	
	private void inicializarCostosEstadias()
	{
		costoEstadiaServicio = Mockito.mock(CostoEstadiaServicio.class);
		
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
		}).when(costoEstadiaServicio).obtenerValorTarifa(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
	private void inicializarFacturaServicio()
	{
		facturaServicio = Mockito.mock(FacturaServicio.class);
		//facturaServicio = new FacturaServicio();
		
		Mockito.when( facturaServicio.getCantidadMovilesByTipo(Mockito.anyString())).thenReturn(CANT_MOVILES_ESTACIONAMIENTO);
		Mockito.when(facturaServicio.esDiaPermitido()).thenReturn(true);
		Mockito.when(facturaServicio.getMovilEstacionamientoByPlaca(Mockito.anyString())).thenReturn(MOVIL_NO_REGISTRADO);
		Mockito.when(facturaServicio.crearFactura(Mockito.any())).thenReturn(true);
		
	}
	
	private void inicializarMovil()
	{
		Movil movil = Mockito.mock(Movil.class);
		
		movilServicio = Mockito.mock(MovilServicio.class);
		Mockito.when(movilServicio.registrarMovil(Mockito.any())).thenReturn(true);
		Mockito.when(movilServicio.getMovilByPlaca(Mockito.anyString())).thenReturn(movil);
		Mockito.when(movilServicio.getMovilByPlaca(Mockito.anyString())).thenReturn(null);
	}
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		inicializarCostosEstadias();
		inicializarFacturaServicio();
		inicializarMovil();
	}
	
	@Test
	public void registrarCarro() 
	{
		MovilDTOTestDataBuilder movilDTOTestDataBuilder = new MovilDTOTestDataBuilder();
		// arrange
		MovilDTO carro = movilDTOTestDataBuilder.withPlaca("CXX-001").withCilindraje(-1).withTipoMovil("CARRO").build();
		// act
		boolean resultado;
		
		try
		{
			resultado = facturaServicio.registrarMovil(carro);
			Assert.assertTrue("El carro no pudo ser registrado.", resultado);
		}
		catch(Exception e)
		{
			System.out.println("Error = "+e.getCause());
		}
		// assert
		
	}
}
