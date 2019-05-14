package co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.*;

@Repository
public interface FacturaRepositorio extends CrudRepository<FacturaEntity, Integer> 
{	
	@Query(value = "SELECT * FROM factura WHERE fac_fecha_salida IS NULL", nativeQuery = true)
	public List<FacturaEntity> getListadoMovilesEstacionamiento();
	
	@Query(value = "SELECT COUNT(*) FROM factura JOIN movil ON factura.mov_placa = movil.mov_placa WHERE movil.mov_tipo_movil = :tipoMovil AND factura.fac_fecha_salida IS NULL", nativeQuery = true)
	public Integer getCantidadMovilesByTipo(@Param("tipoMovil") String tipoMovil);
	
	@Query(value = "SELECT COUNT(*) FROM factura WHERE mov_placa = :placa AND fac_fecha_salida IS NULL", nativeQuery = true)
	public Integer getMovilEstacionamientoByPlaca(@Param("placa") String placa);
	
	@Query(value = "SELECT * FROM factura WHERE mov_placa = :placa AND fac_fecha_salida IS NULL", nativeQuery = true)
	public Optional<FacturaEntity> getFacturaByPlaca(@Param("placa") String placa);
}
