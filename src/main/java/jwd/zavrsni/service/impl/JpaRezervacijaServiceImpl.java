package jwd.zavrsni.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.repository.LinijaRepository;
import jwd.zavrsni.repository.RezervacijaRepository;
import jwd.zavrsni.service.RezervacijaService;

@Service
public class JpaRezervacijaServiceImpl implements RezervacijaService{
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	@Autowired
	private LinijaRepository linijaRepository;
	
	@Override
	public Rezervacija autobuskaStanica(Long linijaId) {
		
		if(linijaId == null) {
			throw new IllegalArgumentException("Id of a car cannot be null!");
		}
		
		Linija linija = linijaRepository.findOne(linijaId);
		if(linija == null) {
			throw new IllegalArgumentException("There is no car that has given id!");
		}
		
		
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setLinija(linija);
			
			rezervacijaRepository.save(rezervacija);
			linijaRepository.save(linija);
				
			return rezervacija;
		
	}
}
