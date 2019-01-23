package jwd.zavrsni.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.web.dto.LinijaDTO;

@Component
public class LinijaToLinijaDTO 
	implements Converter<Linija, LinijaDTO> {

	@Override
	public LinijaDTO convert(Linija source) {
		LinijaDTO dto = new LinijaDTO();
		dto.setId(source.getId());
		dto.setModel(source.getBrojMesta());
		dto.setRegistracija(source.getCena());
		dto.setGodiste(source.getVremePolaska());
		dto.setPotrosnja(source.getDestinacija());
		dto.setIznajmljen(source.getIznajmljen());
		dto.setKompanijaId(source.getKompanija().getId());
		dto.setKompanijaNaziv(source.getKompanija().getNaziv());
		
		return dto;
	}
	
	public List<LinijaDTO> convert(List<Linija> automobili){
		List<LinijaDTO> ret = new ArrayList<>();
		
		for(Linija a : automobili){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
