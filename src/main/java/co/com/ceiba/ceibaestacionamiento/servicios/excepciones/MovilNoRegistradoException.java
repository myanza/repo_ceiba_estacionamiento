package co.com.ceiba.ceibaestacionamiento.servicios.excepciones;

public class MovilNoRegistradoException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 400L;
	
	public MovilNoRegistradoException(String mensaje) {
		super(mensaje);
	}
}
