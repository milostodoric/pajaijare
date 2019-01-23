package jwd.zavrsni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.zavrsni.model.Prevoznik;

@Repository
public interface PrevoznikRepository 
	extends JpaRepository<Prevoznik, Long> {

}
