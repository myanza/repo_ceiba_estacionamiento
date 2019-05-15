package co.com.ceiba.CeibaEstacionamiento.testdatabuilder;

import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;

public class MovilTestDataBuilder 
{
	private String placa;
	private String tipoMovil;
	private double cilindraje;
	
	public MovilTestDataBuilder()
	{
		
	}

	public MovilTestDataBuilder withPlaca(String placa) 
	{
		this.placa = placa;
		return this;
	}

	public MovilTestDataBuilder withTipoMovil(String tipoMovil) 
	{
		this.tipoMovil = tipoMovil;
		return this;
	}

	public MovilTestDataBuilder withCilindraje(double cilindraje) 
	{
		this.cilindraje = cilindraje;
		return this;
	}

	public Movil build()
	{
		Movil movil = new Movil();
		movil.setPlaca(this.placa);
		movil.setCilindraje(this.cilindraje);
		movil.setTipoMovil(this.tipoMovil);
		return movil;
	}
	
	//public
}
