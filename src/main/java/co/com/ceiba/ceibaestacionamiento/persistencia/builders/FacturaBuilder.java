package co.com.ceiba.ceibaestacionamiento.persistencia.builders;

import java.util.Date;

import co.com.ceiba.ceibaestacionamiento.dominio.Factura;
import co.com.ceiba.ceibaestacionamiento.dominio.Movil;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.FacturaEntity;
import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.MovilEntity;

public class FacturaBuilder 
{
	public FacturaBuilder() {
		//Constructor sin atributos
	}

	public Factura convertirADominio(FacturaEntity facturaEntity) 
	{
		Factura factura = null;
		if (facturaEntity != null) 
		{
			Movil movil;
			MovilBuilder movilBuilder = new MovilBuilder();
			MovilEntity movilEntity = facturaEntity.getMovil();
			movil = movilBuilder.convertirADominio(movilEntity);
			
			Date fechaIngreso = facturaEntity.getFacFechaIngreso();
			Date fechaSalida = facturaEntity.getFacFechaSalida();
			double valor = facturaEntity.getFacValor();
			factura = new Factura(fechaIngreso, fechaSalida, valor, movil);
		}
		return factura;
	}

	public FacturaEntity convertirAEntity(Factura factura) 
	{
		FacturaEntity facturaEntity = new FacturaEntity();
		MovilBuilder movilBuilder = new MovilBuilder();
		
		if (facturaEntity != null) 
		{
			facturaEntity.setFacFechaIngreso(factura.getFechaIngreso());
			facturaEntity.setFacFechaSalida(factura.getFechaSalida());
			facturaEntity.setFacValor(factura.getValor());
			MovilEntity movilEntity = movilBuilder.convertirAEntity(factura.getMovil());
			movilEntity.agregarFactura(facturaEntity);
			facturaEntity.setMovil(movilEntity);
		}
		return facturaEntity;
	}
}
