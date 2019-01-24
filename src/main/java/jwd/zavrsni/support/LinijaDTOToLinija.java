package jwd.zavrsni.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.model.Prevoznik;
import jwd.zavrsni.service.LinijaService;
import jwd.zavrsni.service.PrevoznikService;
import jwd.zavrsni.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija 
	implements Converter<LinijaDTO, Linija>{
	
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private PrevoznikService prevoznikService;
	
	
	@Override
	public Linija convert(LinijaDTO source) {
		Prevoznik prevoznik = null;
			if(source.getPrevoznikId() != null) {
				prevoznik = prevoznikService.findOne(source.getPrevoznikId());
			}
			
		if (prevoznik != null) {
		
		Linija linija;
		if(source.getId()==null){
			linija = new Linija();
			linija.setPrevoznik(
					prevoznikService.findOne(
							source.getPrevoznikId()));
		}else{
			linija = linijaService.findOne(source.getId());
		}
		
		
		linija.setBrojMesta(source.getBrojMesta());
		linija.setCena(source.getCena());
		linija.setVremePolaska(source.getVremePolaska());
		linija.setDestinacija(source.getDestinacija());
		linija.setPrevoznik(prevoznik);
		
		return linija;
	} else {
		throw new IllegalStateException("Pokušavate da promenite nepostojeću liniju!!!");
		}
	}
}