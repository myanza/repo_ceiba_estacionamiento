package co.com.ceiba.CeibaEstacionamiento.persistencia.builders;

import java.util.Date;

import co.com.ceiba.CeibaEstacionamiento.dominio.Factura;
import co.com.ceiba.CeibaEstacionamiento.dominio.Movil;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.FacturaEntity;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.MovilEntity;

public class FacturaBuilder 
{
	public FacturaBuilder() {}

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
