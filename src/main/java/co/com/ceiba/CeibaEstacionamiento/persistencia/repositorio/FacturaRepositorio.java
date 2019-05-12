package co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.*;

@Repository
public interface FacturaRepositorio extends CrudRepository<FacturaEntity, Integer> 
{
	@Query(value = "SELECT * FROM factura WHERE fac_fecha_salida IS NULL", nativeQuery = true)
	public List<FacturaEntity> getListadoMovilesEstacionamiento();
	
	@Query(value = "SELECT COUNT(*) FROM factura JOIN movil ON factura.mov_placa = movil.mov_placa WHERE movil.mov_tipoMovil = :tipoMovil AND factura.fac_fechaSalida IS NULL", nativeQuery = true)
	public Integer getCantidadMovilesByTipo(@Param("tipoMovil") String tipoMovil);
	
	@Query(value = "SELECT COUNT(*) FROM factura WHERE mov_placa = :placa AND fac_fechaSalida IS NULL", nativeQuery = true)
	public Integer getMovilEstacionamientoByPlaca(@Param("placa") String placa);
}
