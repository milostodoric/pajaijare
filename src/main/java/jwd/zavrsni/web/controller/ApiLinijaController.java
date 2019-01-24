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
import jwd.zavrsni.model.Prevoznik;
import jwd.zavrsni.service.LinijaService;
import jwd.zavrsni.support.LinijaDTOToLinija;
import jwd.zavrsni.support.LinijaToLinijaDTO;
import jwd.zavrsni.web.dto.LinijaDTO;

@RestController
@RequestMapping("/api/linije")
public class ApiLinijaController {
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private LinijaToLinijaDTO toLinijaDTO;
//	@Autowired
//	private RezervacijaToRezervacijaDTO toRezervacijaDTO;
	@Autowired
	private LinijaDTOToLinija toLinija;
//	@Autowired
//	private RezervacijaService rezervacijaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LinijaDTO>> get(
			@RequestParam(required=false) String destinacija,
			@RequestParam(required=false) Prevoznik prevoznik,
			@RequestParam(required=false) Double cena,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Linija> linije;
		
		if(destinacija != null || prevoznik != null || cena != null) {
			linije = linijaService.pretraga(destinacija, prevoznik, cena, pageNum);
		}else{
			linije = linijaService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()) );
		return  new ResponseEntity<>(
				toLinijaDTO.convert(linije.getContent()),
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
				toLinijaDTO.convert(linija),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<LinijaDTO> add(
			@Validated @RequestBody LinijaDTO novaLinija){
		
		Linija linija = toLinija.convert(novaLinija);
//		linija.setIznajmljen(false);
		linijaService.save(linija);
		
		return new ResponseEntity<>(toLinijaDTO.convert(linija),
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
		
		Linija linija = toLinija.convert(izmenjen); 
		linijaService.save(linija);
		
		return new ResponseEntity<>(toLinijaDTO.convert(linija),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<LinijaDTO> delete(@PathVariable Long id){
		linijaService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
/*	@RequestMapping(method=RequestMethod.POST, value="/{id}/iznajmljivanje")
	public ResponseEntity<RezervacijaDTO> rent(@PathVariable Long id){
		
		Rezervacija n = rezervacijaService.rentACar(id);
		
		if(n != null) {
			return new ResponseEntity<>(toNajamDTO.convert(n), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}*/
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
