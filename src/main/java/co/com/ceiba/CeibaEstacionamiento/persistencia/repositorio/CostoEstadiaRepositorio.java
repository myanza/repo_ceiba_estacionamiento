package co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.*;

@Repository
public interface CostoEstadiaRepositorio extends CrudRepository<CostoEstadiaEntity, Integer> 
{
	public Optional<CostoEstadiaEntity> findByTipoMovilAndTiempoEstadiaAndTipoPago(String tipoMovil, String tipoPago, String tiempoEstadia);
}
