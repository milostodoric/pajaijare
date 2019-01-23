package jwd.zavrsni.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.model.Prevoznik;
import jwd.zavrsni.repository.LinijaRepository;
import jwd.zavrsni.service.LinijaService;

@Service
@Transactional
public class JpaLinijaServiceImpl implements LinijaService {
	
	@Autowired
	private LinijaRepository linijaRepository;

	@Override
	public Page<Linija> findAll(int pageNum) {
		return linijaRepository.findAll(
				new PageRequest(pageNum, 5));
	}

	@Override
	public Linija findOne(Long id) {
		return linijaRepository.findOne(id);
	}

	@Override
	public void save(Linija linija) {
		linijaRepository.save(linija);
	}

	@Override
	public void remove(Long id) {
		linijaRepository.delete(id);
	}

	@Override
	public Page<Linija> findByPrevoznikId(int pageNum, Long prevoznikId) {
		
		return linijaRepository.findByPrevoznikId(prevoznikId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Linija> pretraga(String destinacija, Prevoznik prevoznik, Double cena, int page) {
		if(destinacija != null ){
			destinacija = "%" + destinacija + "%";
		}
		return linijaRepository.pretraga(destinacija, cena, new PageRequest(page, 5));
	}



}
