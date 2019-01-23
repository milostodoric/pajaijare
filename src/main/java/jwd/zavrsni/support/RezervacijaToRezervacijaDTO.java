package jwd.zavrsni.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDTO implements Converter<Rezervacija, RezervacijaDTO> {

	@Override
	public RezervacijaDTO convert(Rezervacija arg0) {
		
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(arg0.getId());
		
		return dto;
	}

}
