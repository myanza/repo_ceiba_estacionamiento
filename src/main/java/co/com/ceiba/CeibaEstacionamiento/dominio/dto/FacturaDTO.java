package co.com.ceiba.CeibaEstacionamiento.dominio.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FacturaDTO 
{
	private int fac_id;
	private String fac_fechaIngreso;
	private String fac_fechaSalida;
	private double fac_valor;
	private String mov_placa;
	private String tipoMovil;
	private double cilindraje;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public FacturaDTO(){}

	public FacturaDTO(int fac_id, String fac_fechaIngreso, String fac_fechaSalida, double fac_valor, String mov_placa,
			String tipoMovil, double cilindraje) 
	{
		this.fac_id = fac_id;
		this.fac_fechaIngreso = fac_fechaIngreso;
		this.fac_fechaSalida = fac_fechaSalida;
		this.fac_valor = fac_valor;
		this.mov_placa = mov_placa;
		this.tipoMovil = tipoMovil;
		this.cilindraje = cilindraje;
	}

	public int getFac_id() {
		return fac_id;
	}

	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}

	public String getFac_fechaIngreso() {
		return fac_fechaIngreso;
	}

	public void setFac_fechaIngreso(Date fac_fechaIngreso) {
		this.fac_fechaIngreso = sdf.format(fac_fechaIngreso);
	}

	public String getFac_fechaSalida() {
		return fac_fechaSalida;
	}

	public void setFac_fechaSalida(Date fac_fechaSalida) {
		if (fac_fechaSalida != null) {
			this.fac_fechaSalida = sdf.format(fac_fechaSalida);
		}
	}

	public double getFac_valor() {
		return fac_valor;
	}

	public void setFac_valor(double fac_valor) {
		this.fac_valor = fac_valor;
	}

	public String getMov_placa() {
		return mov_placa;
	}

	public void setMov_placa(String mov_placa) {
		this.mov_placa = mov_placa;
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