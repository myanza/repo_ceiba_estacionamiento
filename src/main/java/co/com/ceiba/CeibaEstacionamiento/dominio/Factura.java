package co.com.ceiba.CeibaEstacionamiento.dominio;

import java.util.Date;

public class Factura 
{
	private Date fechaIngreso;
	private Date fechaSalida;
	private double valor;
	private Movil movil;
	
	public Factura(Date fechaIngreso, Date fechaSalida, double valor, Movil movil) 
	{
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.movil = movil;
	}
	
	public Factura(Date fechaIngreso, Movil movil) 
	{
		this.fechaIngreso = fechaIngreso;
		this.movil = movil;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}
	
}
