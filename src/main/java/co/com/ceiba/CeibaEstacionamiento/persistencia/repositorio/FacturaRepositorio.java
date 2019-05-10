package co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.*;

@Repository
public interface FacturaRepositorio extends PagingAndSortingRepository<FacturaEntity, Integer> 
{
	@Query(value = "SELECT COUNT(*) FROM factura JOIN movil ON factura.mov_placa = movil.mov_placa WHERE movil.mov_tipoMovil = :tipoMovil AND factura.fac_fechaSalida IS NULL", nativeQuery = true)
	public Integer getCantidadMovilesByTipo(@Param("tipoVehiculo") String tipoVehiculo);
	
	@Query(value = "SELECT COUNT(*) FROM factura WHERE mov_placa = :placa AND fac_fechaSalida IS NULL", nativeQuery = true)
	public Integer getMovilEstacionamientoByPlaca(@Param("placa") String placa);
	
	@Query(value = "SELECT * FROM factura WHERE fac_fechaSalida IS NULL", countQuery = "SELECT COUNT(*) FROM factura WHERE fac_fechaSalida IS NULL", nativeQuery = true)
	public Page<FacturaEntity> getListadoMovilesEstacionamiento(Pageable pageable);
}
