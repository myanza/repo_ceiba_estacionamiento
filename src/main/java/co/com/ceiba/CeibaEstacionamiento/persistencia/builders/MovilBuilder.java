package co.com.ceiba.CeibaEstacionamiento.persistencia.builders;

import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.MovilEntity;

public class MovilBuilder 
{
	public MovilBuilder() {}
	
	public Movil convertirADominio(MovilEntity movilEntity) 
	{
		Movil movil = new Movil();
		if (movilEntity != null) 
		{			
			String tipoMovil = movilEntity.getMovTipoMovil();
			String placa = movilEntity.getMovPlaca();
			double cilindraje = movilEntity.getMovCilindraje();
			
			movil = new Movil(placa, cilindraje, tipoMovil);
		}
		return movil;
	}
	
	public MovilEntity convertirAEntity(Movil movil) 
	{
		MovilEntity movilEntity = new MovilEntity();
		
		if (movil != null) 
		{
			movilEntity = new MovilEntity();
			movilEntity.setMovPlaca(movil.getPlaca());
			
			if (movil.getTipoMovil() == "MOTO") 
			{
				movilEntity.setMovCilindraje(movil.getCilindraje());
				movilEntity.setMovTipoMovil("MOTO");
			} 
			else 
			{
				movilEntity.setMovCilindraje(-1);
				movilEntity.setMovTipoMovil("CARRO");
			}
		}
		return movilEntity;
	}
}
