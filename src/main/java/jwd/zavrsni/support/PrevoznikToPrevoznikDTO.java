package jwd.zavrsni.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Prevoznik;
import jwd.zavrsni.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDTO 
	implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik prevoznik) {
		PrevoznikDTO prevoznikDTO = new PrevoznikDTO();
		prevoznikDTO.setId(prevoznik.getId());
		prevoznikDTO.setNaziv(prevoznik.getNaziv());
		prevoznikDTO.setAdresa(prevoznik.getAdresa());
		prevoznikDTO.setPib(prevoznik.getPib());
		return prevoznikDTO;
	}

	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici) {
		List<PrevoznikDTO> ret = new ArrayList<>();
		
		for(Prevoznik p: prevoznici){
			ret.add(convert(p));
		}
		
		return ret;
	}
}
