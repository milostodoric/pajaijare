package jwd.zavrsni.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.service.LinijaService;
import jwd.zavrsni.service.RezervacijaService;
import jwd.zavrsni.support.LinijaDTOToLinija;
import jwd.zavrsni.support.LinijaToLinijaDTO;
import jwd.zavrsni.support.RezervacijaToRezervacijaDTO;
import jwd.zavrsni.web.dto.LinijaDTO;
import jwd.zavrsni.web.dto.RezervacijaDTO;

@RestController
@RequestMapping("/api/automobili")
public class ApiAutomobilController {
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private LinijaToLinijaDTO toAutomobilDTO;
	@Autowired
	private RezervacijaToRezervacijaDTO toNajamDTO;
	@Autowired
	private LinijaDTOToLinija toAutomobil;
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LinijaDTO>> get(
			@RequestParam(required=false) String model,
			@RequestParam(required=false) Integer godiste,
			@RequestParam(required=false) Double potrosnja,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Linija> automobili;
		
		if(model != null || godiste != null || potrosnja != null) {
			automobili = linijaService.pretraga(model, godiste, potrosnja, pageNum);
		}else{
			automobili = linijaService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(automobili.getTotalPages()) );
		return  new ResponseEntity<>(
				toAutomobilDTO.convert(automobili.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<LinijaDTO> get(
			@PathVariable Long id){
		Linija linija = linijaService.findOne(id);
		
		if(linija==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toAutomobilDTO.convert(linija),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<LinijaDTO> add(
			@Validated @RequestBody LinijaDTO novAutomobil){
		
		Linija linija = toAutomobil.convert(novAutomobil);
		linija.setIznajmljen(false);
		linijaService.save(linija);
		
		return new ResponseEntity<>(toAutomobilDTO.convert(linija),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<LinijaDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody LinijaDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Linija linija = toAutomobil.convert(izmenjen); 
		linijaService.save(linija);
		
		return new ResponseEntity<>(toAutomobilDTO.convert(linija),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<LinijaDTO> delete(@PathVariable Long id){
		linijaService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/iznajmljivanje")
	public ResponseEntity<RezervacijaDTO> rent(@PathVariable Long id){
		
		Rezervacija n = rezervacijaService.rentACar(id);
		
		if(n != null) {
			return new ResponseEntity<>(toNajamDTO.convert(n), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
