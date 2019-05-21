package co.com.ceiba.ceibaestacionamiento.dominio.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FacturaDTO 
{
	private int facId;
	private String facFechaIngreso;
	private String facFechaSalida;
	private double facValor;
	private String movPlaca;
	private String tipoMovil;
	private double cilindraje;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public FacturaDTO(){
		//Constructor sin atributos
	}

	public FacturaDTO(int facId, String facFechaIngreso, String facFechaSalida, double facValor, String movPlaca,
			String tipoMovil, double cilindraje) 
	{
		this.facId = facId;
		this.facFechaIngreso = facFechaIngreso;
		this.facFechaSalida = facFechaSalida;
		this.facValor = facValor;
		this.movPlaca = movPlaca;
		this.tipoMovil = tipoMovil;
		this.cilindraje = cilindraje;
	}

	public int getFacId() {
		return facId;
	}

	public void setFacId(int facId) {
		this.facId = facId;
	}

	public String getFacFechaIngreso() {
		return facFechaIngreso;
	}

	public void setFacFechaIngreso(Date facFechaIngreso) {
		this.facFechaIngreso = sdf.format(facFechaIngreso);
	}

	public String getFacFechaSalida() {
		return facFechaSalida;
	}

	public void setFacFechaSalida(Date facFechaSalida) {
		if (facFechaSalida != null) {
			this.facFechaSalida = sdf.format(facFechaSalida);
		}
	}

	public double getFacValor() {
		return facValor;
	}

	public void setFacValor(double facValor) {
		this.facValor = facValor;
	}

	public String getMovPlaca() {
		return movPlaca;
	}

	public void setMovPlaca(String movPlaca) {
		this.movPlaca = movPlaca;
	}

	public String getTipoMovil() {
		return tipoMovil;
	}

	public void setTipoMovil(String tipoMovil) {
		this.tipoMovil = tipoMovil;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	
}