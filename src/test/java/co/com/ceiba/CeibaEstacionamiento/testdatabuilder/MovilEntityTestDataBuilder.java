package co.com.ceiba.CeibaEstacionamiento.testdatabuilder;

import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.MovilEntity;

public class MovilEntityTestDataBuilder 
{
	private String placa;
	private double cilindraje;
	private String tipoMovil;
	
	public MovilEntityTestDataBuilder withPlaca(String placa) 
	{
		this.placa = placa;
		return this;
	}
	
	public MovilEntityTestDataBuilder withCilindraje(double cilindraje) 
	{
		this.cilindraje = cilindraje;
		return this;
	}
	
	public MovilEntityTestDataBuilder withTipoMovil(String tipoMovil) 
	{
		this.tipoMovil = tipoMovil;
		return this;
	}
	
	public MovilEntity build()
	{
		MovilEntity movilEntity = new MovilEntity();
		movilEntity.setMovPlaca(this.placa);
		movilEntity.setMovCilindraje(this.cilindraje);
		movilEntity.setMovTipoMovil(this.tipoMovil);
		return movilEntity;
	}

}
