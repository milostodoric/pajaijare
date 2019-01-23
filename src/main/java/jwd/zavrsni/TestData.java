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
		
		Prevoznik k1 = new Prevoznik();
		k1.setNaziv("SuRent");
		k1.setAdresa("Balzakova 1");
		k1.setTelefon("024/151-363");
		prevoznikService.save(k1);
		
		Prevoznik k2 = new Prevoznik();
		k2.setNaziv("NSRent");
		k2.setAdresa("Maksima Gorkog 2");
		k2.setTelefon("021/4141-515");
		prevoznikService.save(k2);
		
		Linija a1 = new Linija();
		a1.setBrojMesta("Nissan Prairie");
		a1.setRegistracija("SU82404");
		a1.setGodiste(1991);
		a1.setPotrosnja(10.3);
		a1.setKompanija(k1);
		
		linijaService.save(a1);
	}
}