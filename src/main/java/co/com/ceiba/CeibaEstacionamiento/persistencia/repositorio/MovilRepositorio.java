package co.com.ceiba.CeibaEstacionamiento.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.CeibaEstacionamiento.persistencia.entidades.*;

@Repository
public interface MovilRepositorio extends CrudRepository<MovilEntity, String>{

}