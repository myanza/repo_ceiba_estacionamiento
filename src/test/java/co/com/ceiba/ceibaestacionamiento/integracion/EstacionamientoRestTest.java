package co.com.ceiba.ceibaestacionamiento.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.ceibaestacionamiento.CeibaEstacionamientoApplication;
import co.com.ceiba.ceibaestacionamiento.dbconfiguracion.H2JpaConfig;
import co.com.ceiba.ceibaestacionamiento.dominio.dto.MovilDTO;
import co.com.ceiba.ceibaestacionamiento.dominio.repositorio.FacturaRepositorio;
import co.com.ceiba.ceibaestacionamiento.testdatabuilder.MovilDTOTestDataBuilder;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CeibaEstacionamientoApplication.class, H2JpaConfig.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstacionamientoRestTest 
{
	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private FacturaRepositorio facturaRepositorio;
	
	private static final int SIN_CILINDRAJE = -1;
	private static final String CARRO = "CARRO";

	@Test
	public void primerTestRegistrarMovil() throws Exception 
	{
		String placa = "DFG-897";
		MovilDTOTestDataBuilder movilDTOTestDataBuilder = new MovilDTOTestDataBuilder();
		MovilDTO movilDTO = movilDTOTestDataBuilder.withPlaca(placa).withCilindraje(SIN_CILINDRAJE).withTipoMovil(CARRO).build();
		
		String movilDTOJsonString = json(movilDTO);

		mockMVC.perform(
				post("/estacionamiento/registrarmovil").contentType(MediaType.APPLICATION_JSON).content(movilDTOJsonString))
				.andExpect(status().isOk());

	}

	@Test
	public void segundoTestEliminarMovil() throws Exception 
	{
		String placa = "DFG-897";
		mockMVC.perform(
				post("/estacionamiento/eliminarmovil").contentType(MediaType.TEXT_PLAIN).content(placa))
				.andExpect(status().isOk());
	}
	
	@Test
	public void tercerTestGetListadoMovilesEstacionamiento() throws Exception 
	{
		Integer cantMovEstacionamiento = facturaRepositorio.getCantMovilesEstacionamiento();

		mockMVC.perform(
				get("/estacionamiento/listadomoviles")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",  hasSize(cantMovEstacionamiento)));
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
