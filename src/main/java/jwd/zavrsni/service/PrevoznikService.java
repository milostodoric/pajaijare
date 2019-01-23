package jwd.zavrsni.service;

import java.util.List;

import jwd.zavrsni.model.Prevoznik;


public interface PrevoznikService {
	List<Prevoznik> findAll();
	Prevoznik findOne(Long id);
	void save(Prevoznik prevoznik);
	void remove(Long id);

}
