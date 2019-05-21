package co.com.ceiba.ceibaestacionamiento.servicios.excepciones;

public class SinEspacioException extends RuntimeException
{
	private static final long serialVersionUID = 100L;
	
	public SinEspacioException(String mensaje) 
	{
		super(mensaje);
	}
	
	public long getCodigoError()
	{
		return serialVersionUID;
	}
}
