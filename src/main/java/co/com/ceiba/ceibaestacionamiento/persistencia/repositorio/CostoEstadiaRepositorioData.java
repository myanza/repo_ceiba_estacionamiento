package co.com.ceiba.ceibaestacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.*;

@Repository
public interface CostoEstadiaRepositorioData extends CrudRepository<CostoEstadiaEntity, Integer> 
{
	@Query(value = "SELECT * FROM costoestadia WHERE cos_tipo_movil = :tipoMovil AND cos_tipo_pago = :tipoPago AND cos_tiempo_estadia = :tiempoEstadia", nativeQuery = true)
	Optional<CostoEstadiaEntity> findBy(@Param("tipoMovil") String tipoMovil, @Param("tipoPago") String tipoPago, @Param("tiempoEstadia") String tiempoEstadia);
}
