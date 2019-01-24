package jwd.zavrsni.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.zavrsni.model.Linija;
import jwd.zavrsni.model.Prevoznik;
import jwd.zavrsni.service.LinijaService;
import jwd.zavrsni.service.PrevoznikService;
import jwd.zavrsni.support.LinijaToLinijaDTO;
import jwd.zavrsni.support.PrevoznikToPrevoznikDTO;
import jwd.zavrsni.web.dto.LinijaDTO;
import jwd.zavrsni.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value="/api/kompanije")
public class ApiPrevoznikController {
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private PrevoznikToPrevoznikDTO toDTO;
	@Autowired
	private LinijaToLinijaDTO toAutomobilDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PrevoznikDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(prevoznikService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PrevoznikDTO> get(
			@PathVariable Long id){
		
		Prevoznik prevoznik = prevoznikService.findOne(id);
		
		if(prevoznik == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(prevoznik),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{prevoznikId}/linije")
	public ResponseEntity<List<LinijaDTO>> linije(
			@PathVariable Long prevoznikId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Linija> linije = linijaService.findByPrevoznikId(pageNum, prevoznikId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()) );
		return  new ResponseEntity<>(
				toAutomobilDTO.convert(linije.getContent()),
				headers,
				HttpStatus.OK);
	}
}
