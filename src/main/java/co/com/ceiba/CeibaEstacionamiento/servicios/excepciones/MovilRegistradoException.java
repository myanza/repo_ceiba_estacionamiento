package co.com.ceiba.CeibaEstacionamiento.servicios.excepciones;

public class MovilRegistradoException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 300L;
	
	public MovilRegistradoException(String mensaje) {
		super(mensaje);
	}
	
	public long getCodigoError(){
		return serialVersionUID;
	}
}
