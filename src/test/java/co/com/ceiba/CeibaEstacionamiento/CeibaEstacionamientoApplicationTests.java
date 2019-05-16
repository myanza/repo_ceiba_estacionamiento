package co.com.ceiba.ceibaestacionamiento;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.CeibaEstacionamientoApplication;
import co.com.ceiba.ceibaestacionamiento.dbconfiguracion.H2JpaConfig;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CeibaEstacionamientoApplication.class, H2JpaConfig.class })
public class CeibaestacionamientoApplicationTests 
{
	@Test
	public void whenValidName_thenEmployeeShouldBeFound()
	{
	    String name = "alex";   
	  
	     assertThat("alex")
	      .isEqualTo(name);
	 }
}
