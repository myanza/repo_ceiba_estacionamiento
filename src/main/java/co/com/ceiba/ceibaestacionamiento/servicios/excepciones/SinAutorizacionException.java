package co.com.ceiba.ceibaestacionamiento.servicios.excepciones;

public class SinAutorizacionException extends RuntimeException 
{
	private static final long serialVersionUID = 200L;

	public SinAutorizacionException(String mensaje) {
		super(mensaje);
	}
}
