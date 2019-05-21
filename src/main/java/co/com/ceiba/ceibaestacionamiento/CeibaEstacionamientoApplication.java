package co.com.ceiba.ceibaestacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"co.com.ceiba.ceibaestacionamiento.controller",
		"co.com.ceiba.ceibaestacionamiento.dbconfiguracion",
		"co.com.ceiba.ceibaestacionamiento.dominio",
		"co.com.ceiba.ceibaestacionamiento.dominio.dto",
		"co.com.ceiba.ceibaestacionamiento.dominio.repositorio",
		"co.com.ceiba.ceibaestacionamiento.dominio.servicios",
		"co.com.ceiba.ceibaestacionamiento.persistencia.builders",
		"co.com.ceiba.ceibaestacionamiento.persistencia.entidades",
		"co.com.ceiba.ceibaestacionamiento.persistencia.repositorio",
		"co.com.ceiba.ceibaestacionamiento.servicios",
		"co.com.ceiba.ceibaestacionamiento.servicios.excepciones"})
@SpringBootApplication
public class CeibaEstacionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeibaEstacionamientoApplication.class, args);
	}

}