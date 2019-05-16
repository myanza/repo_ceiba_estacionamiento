package co.com.ceiba.ceibaestacionamiento.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.*;

@Repository
public interface MovilRepositorio extends CrudRepository<MovilEntity, String>{

}
