package co.com.ceiba.ceibaestacionamiento.unitarias;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.ceibaestacionamiento.CeibaEstacionamientoApplication;
import co.com.ceiba.ceibaestacionamiento.controller.EstacionamientoController;
import co.com.ceiba.ceibaestacionamiento.dominio.Factura;
import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.FacturaDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.MovilDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.FacturaRepositorio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.CostoEstadiaServicio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.MovilServicio;
import co.com.ceiba.ceibaestacionamiento.persistencia.builders.FacturaDTOBuilder;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.FacturaEntity;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.MovilEntity;
import co.com.ceiba.ceibaestacionamiento.servicios.FacturaServicioImpl;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.FacturaEntityTestDataBuilder;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.FacturaTestDataBuilder;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.MovilDTOTestDataBuilder;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.MovilEntityTestDataBuilder;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.MovilTestDataBuilder;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CeibaEstacionamientoApplication.class,  FacturaRepositorio.class})
@WebMvcTest(EstacionamientoController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstacionamientoRestTest 
{
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private FacturaServicioImpl facturaServicio;
	
	@MockBean
	private MovilServicio movilServicio;
	
	@MockBean
	private CostoEstadiaServicio costoEstadiaServicio;
	
	private static final int SIN_CILINDRAJE= -1;
	private static final String CARRO = "CARRO";
	private static final String MOTO = "MOTO";
	
	@Test
	public void primerGetListadoMovilesEstacionamiento() throws Exception 
	{
		MovilEntityTestDataBuilder movilEntityTestDataBuilder = new MovilEntityTestDataBuilder();
		MovilEntity movilEntity = movilEntityTestDataBuilder.withPlaca("DFG-456").withCilindraje(SIN_CILINDRAJE).withTipoMovil(CARRO).build();
		
		FacturaEntityTestDataBuilder facturaEntityTestDataBuilder = new FacturaEntityTestDataBuilder();
		FacturaEntity facturaEntity = facturaEntityTestDataBuilder.withId(1).withFechaIngreso(new Date()).withFechaSalida(new Date()).
				withValor(0).withMovilEntity(movilEntity).build();
		
		FacturaDTOBuilder facturaDTOBuilder = new FacturaDTOBuilder();
		FacturaDTO facturaDTO = facturaDTOBuilder.convertToDTO(facturaEntity);
		
		List<FacturaDTO> facturasDTO = Arrays.asList(facturaDTO);
		
		given(facturaServicio.getListadoMovilesEstacionamiento()).willReturn(facturasDTO);
		
		mockMVC.perform(
					get("/estacionamiento/listadomoviles").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					//.andExpect( jsonPath("$.id").value("1"));
					.andExpect(MockMvcResultMatchers.jsonPath("$.listadomoviles").exists())
					.andExpect(MockMvcResultMatchers.jsonPath("$.listadomoviles[*].mov_id").isNotEmpty());
				
	}
	
	@Test
	public void segundoRegistrarMovil() throws Exception
	{
		MovilDTOTestDataBuilder movilDTOTestDataBuilder = new MovilDTOTestDataBuilder();
		MovilDTO movilDTO = movilDTOTestDataBuilder.withPlaca("DFG-345").withCilindraje(150).withTipoMovil(MOTO).build();
		
		String movilDTOJson = json(movilDTO);
		
		given(facturaServicio.registrarMovil(movilDTO, movilServicio, costoEstadiaServicio)).willReturn(true);
		
		mockMVC.perform(
				post("/parqueadero/registrarmovil").contentType(MediaType.APPLICATION_JSON).content(movilDTOJson))
				.andExpect(status().isOk());
	}
	
	@Test
	public void tercerEliminarMovil() throws Exception 
	{
		MovilTestDataBuilder movilTestDataBuilder = new MovilTestDataBuilder();
		Movil carro = movilTestDataBuilder.withPlaca("TYU-567").build();
		
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder();		
		Factura factura = facturaTestDataBuilder.withMovil(carro).withFechaIngreso(new Date()).build();
		
		FacturaDTOBuilder facturaDTOBuilder = new FacturaDTOBuilder();
		FacturaDTO facturaDTO = facturaDTOBuilder.convertToDTO(factura);
		
		given(facturaServicio.eliminarMovil(carro.getPlaca(), movilServicio, costoEstadiaServicio)).willReturn(facturaDTO);

		mockMVC.perform(
				post("/parqueadero/eliminarmovil").contentType(MediaType.TEXT_PLAIN).content(carro.getPlaca()))
				.andExpect(status().isOk());
	}
	
	public static String json(final Object obj) 
	{
		try 
		{
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} 
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
