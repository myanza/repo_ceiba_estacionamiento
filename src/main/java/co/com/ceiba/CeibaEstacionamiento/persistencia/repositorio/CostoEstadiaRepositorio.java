package co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.*;

@Repository
public interface CostoEstadiaRepositorio extends CrudRepository<CostoEstadiaEntity, Integer> 
{
	@Query(value = "SELECT * FROM costoestadia WHERE cos_tipo_movil = :tipoMovil AND cos_tipo_pago = :tipoPago AND cos_tiempo_estadia = :tiempoEstadia", nativeQuery = true)
	public Optional<CostoEstadiaEntity> findByTipoMovilAndTiempoEstadiaAndTipoPago(String tipoMovil, String tipoPago, String tiempoEstadia);
}
