package jwd.zavrsni.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.zavrsni.model.Linija;

@Repository
public interface LinijaRepository 
	extends JpaRepository<Linija, Long> {

	Page<Linija> findByPrevoznikId(Long prevoznikId, Pageable pageRequest);

	@Query("SELECT l FROM Linija l WHERE "
			+ "(:destinacija IS NULL or l.destinacija like :destinacija ) AND "
			+ "(:cena IS NULL OR l.cena <= :cena)"
			)
	Page<Linija> pretraga(
			@Param("destinacija") String destinacija, 
			@Param("cena") Double cena,
			Pageable pageRequest);

}
