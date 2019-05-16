package co.com.ceiba.ceibaestacionamiento.persistencia.builders;

import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.MovilEntity;

public class MovilBuilder 
{
	public MovilBuilder() {
		//Constructor sin atributos
	}
	
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
