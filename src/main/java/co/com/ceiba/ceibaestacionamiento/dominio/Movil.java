package co.com.ceiba.ceibaestacionamiento.dominio;

public class Movil 
{
	private String placa;
	private double cilindraje;
	private String tipoMovil;
	
	public Movil() {
		//Constructor sin atributos
	}
	
	public Movil(String placa, double cilindraje, String tipoMovil) 
	{
		this.placa = placa;
		this.tipoMovil = tipoMovil;
		
		if(tipoMovil == "CARRO")
		{
			this.cilindraje = -1;
		}
		else
		{
			this.cilindraje = cilindraje;
		}
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public double getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}
	public String getTipoMovil() {
		return tipoMovil;
	}
	public void setTipoMovil(String tipoMovil) {
		this.tipoMovil = tipoMovil;
	}
	
}
