	package jwd.zavrsni.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.model.Prevoznik;

public interface LinijaService {
	Page<Linija> findAll(int pageNum);
	Linija findOne(Long id);
	void save(Linija linija);
	void remove(Long id);
	Page<Linija> findByPrevoznikId(int pageNum, Long prevoznikId);
	Page<Linija> pretraga(
			@Param("destinacija") String destinacija, 
			@Param("prevoznik") Prevoznik prevoznik,
			@Param("cena") Double cena,
			int page);
}
