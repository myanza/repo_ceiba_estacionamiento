package co.com.ceiba.CeibaEstacionamiento.testdatabuilder;

import co.com.ceiba.CeibaEstacionamiento.dominio.dto.MovilDTO;

public class MovilDTOTestDataBuilder 
{
	private String placa;
	private double cilindraje;
	private String tipoMovil;

	public MovilDTOTestDataBuilder withPlaca(String placa) 
	{
		this.placa = placa;
		return this;
	}

	public MovilDTOTestDataBuilder withCilindraje(double cilindraje) 
	{
		this.cilindraje = cilindraje;
		return this;
	}
	
	public MovilDTOTestDataBuilder withTipoMovil(String tipoMovil) 
	{
		this.tipoMovil = tipoMovil;
		return this;
	}

	public MovilDTO build()
	{
		MovilDTO movilDTO = new MovilDTO();
		movilDTO.setPlaca(this.placa);
		movilDTO.setCilindraje(this.cilindraje);
		movilDTO.setTipoMovil(this.tipoMovil);
		return movilDTO;
	}
	
}
