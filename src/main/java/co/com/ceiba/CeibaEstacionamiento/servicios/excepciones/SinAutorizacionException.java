package co.com.ceiba.CeibaEstacionamiento.servicios.excepciones;

public class SinAutorizacionException extends RuntimeException 
{
	private static final long serialVersionUID = 200L;

	public SinAutorizacionException(String mensaje) {
		super(mensaje);
	}

	public long getCodigoError() {
		return serialVersionUID;
	}
}