package co.com.ceiba.ceibaestacionamiento.dominio.repositorio;

import java.util.Optional;

import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.CostoEstadiaEntity;

@FunctionalInterface
public interface CostoEstadiaRepositorio 
{
	public Optional<CostoEstadiaEntity> findBy(String tipoMovil, String tipoPago, String tiempoEstadia);
}
