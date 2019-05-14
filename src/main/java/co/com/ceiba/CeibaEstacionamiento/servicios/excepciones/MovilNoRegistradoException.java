package co.com.ceiba.CeibaEstacionamiento.servicios.excepciones;

public class MovilNoRegistradoException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 400L;
	
	public MovilNoRegistradoException(String mensaje) {
		super(mensaje);
	}
	
	public long getCodigoError(){
		return serialVersionUID;
	}
}
