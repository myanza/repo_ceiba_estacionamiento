package co.com.ceiba.CeibaEstacionamiento.testdatabuilder;

import java.util.Date;

import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.FacturaEntity;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.MovilEntity;

public class FacturaEntityTestDataBuilder 
{
	private int id;
	private Date fechaIngreso;
	private Date fechaSalida;
	private double valor;
	private MovilEntity movilEntity;
	
	public FacturaEntityTestDataBuilder withId(int id) 
	{
		this.id = id;
		return this;
	}
	
	public FacturaEntityTestDataBuilder withFechaIngreso(Date fechaIngreso)
	{
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public FacturaEntityTestDataBuilder withFechaSalida(Date fechaSalida)
	{
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public FacturaEntityTestDataBuilder withValor(double valor)
	{
		this.valor = valor;
		return this;
	}
	
	public FacturaEntityTestDataBuilder withMovilEntity(MovilEntity movilEntity)
	{
		this.movilEntity = movilEntity;
		return this;
	}
	
	public FacturaEntity build() 
	{
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setFacId(this.id);
		facturaEntity.setFacFechaIngreso(this.fechaIngreso);
		facturaEntity.setFacFechaSalida(this.fechaSalida);
		facturaEntity.setFacValor(this.valor);
		facturaEntity.setMovil(this.movilEntity);
		return facturaEntity;
	}
}
