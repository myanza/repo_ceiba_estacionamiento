package co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.*;

@Repository
public interface MovilRepositorio extends PagingAndSortingRepository<MovilEntity, String>{

}
