package jwd.zavrsni;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.model.Prevoznik;
import jwd.zavrsni.service.LinijaService;
import jwd.zavrsni.service.PrevoznikService;


@Component
public class TestData {

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaService linijaService;
	
	@PostConstruct
	public void init() {
		
		Prevoznik p1 = new Prevoznik();
		p1.setNaziv("Prevoznik 1");
		p1.setAdresa("Adresa 1");
		p1.setPib("101000111");
		prevoznikService.save(p1);
		
		Prevoznik p2 = new Prevoznik();
		p2.setNaziv("NSRent");
		p2.setAdresa("Maksima Gorkog 2");
		p2.setPib("110110110");
		prevoznikService.save(p2);
		
		Linija l1 = new Linija();
		l1.setBrojMesta(50);
		l1.setCena(200.00);
		l1.setVremePolaska("09:00");
		l1.setDestinacija("Bec");
		l1.setPrevoznik(p1);
		
		linijaService.save(l1);
	}
}