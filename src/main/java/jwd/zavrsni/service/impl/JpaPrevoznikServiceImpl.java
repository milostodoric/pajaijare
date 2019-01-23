package jwd.zavrsni.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Prevoznik;
import jwd.zavrsni.repository.PrevoznikRepository;
import jwd.zavrsni.service.PrevoznikService;

@Service
@Transactional
public class JpaPrevoznikServiceImpl implements PrevoznikService {
	@Autowired
	private PrevoznikRepository prevoznikRepository;

	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik findOne(Long id) {
		return prevoznikRepository.findOne(id);
	}

	@Override
	public void save(Prevoznik prevoznik) {
		prevoznikRepository.save(prevoznik);
	}

	@Override
	public void remove(Long id) {
		prevoznikRepository.delete(id);
	}
}
