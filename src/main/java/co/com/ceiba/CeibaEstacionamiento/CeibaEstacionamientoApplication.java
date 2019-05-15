package co.com.ceiba.CeibaEstacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages={"co.com.ceiba.CeibaEstacionamiento.controller",
		"co.com.ceiba.CeibaEstacionamiento.dbconfiguracion",
		"co.com.ceiba.CeibaEstacionamiento.dominio",
		"co.com.ceiba.CeibaEstacionamiento.domini.dto",
		"co.com.ceiba.CeibaEstacionamiento.persistencia.builders",
		"co.com.ceiba.CeibaEstacionamiento.persistencia.entidades",
		"co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio",
		"co.com.ceiba.CeibaEstacionamiento.servicios",
		"co.com.ceiba.CeibaEstacionamiento.servicios.excepciones"})
@SpringBootApplication
public class CeibaEstacionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeibaEstacionamientoApplication.class, args);
	}

}
