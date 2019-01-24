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
		dto.setBrojMesta(source.getBrojMesta());
		dto.setCena(source.getCena());
		dto.setVremePolaska(source.getVremePolaska());
		dto.setDestinacija(source.getDestinacija());
		dto.setPrevoznikId(source.getPrevoznik().getId());
		dto.setPrevoznikNaziv(source.getPrevoznik().getNaziv());
		
		return dto;
	}
	
	public List<LinijaDTO> convert(List<Linija> linije){
		List<LinijaDTO> ret = new ArrayList<>();
		
		for(Linija l : linije){
			ret.add(convert(l));
		}
		
		return ret;
	}

}
