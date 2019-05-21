package co.com.ceiba.ceibaestacionamiento.testdatabuilder;

import java.util.Date;

import co.com.ceiba.ceibaestacionamiento.dominio.Factura;
import co.com.ceiba.ceibaestacionamiento.dominio.Movil;

public class FacturaTestDataBuilder 
{
	private Date fechaIngreso;
	private Date fechaSalida;
	private double valor;
	private Movil movil;
	
	public FacturaTestDataBuilder withFechaIngreso(Date fechaIngreso)
	{
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public FacturaTestDataBuilder withFechaSalida(Date fechaSalida)
	{
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public FacturaTestDataBuilder withValor(double valor)
	{
		this.valor = valor;
		return this;
	}
	
	public FacturaTestDataBuilder withMovil(Movil movil)
	{
		this.movil = movil;
		return this;
	}
	
	public Factura build()
	{
		return new Factura(this.fechaIngreso, this.fechaSalida, this.valor, this.movil);
	}
}
