package jwd.zavrsni.web.dto;



import org.hibernate.validator.constraints.NotBlank;


public class LinijaDTO {

	private Long id;
	@NotBlank()
	private Integer brojMesta;
	private Double cena;
	private String vremePolaska;
	@NotBlank()
	private String destinacija;
	private Long prevoznikId;
	private String prevoznikNaziv;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getBrojMesta() {
		return brojMesta;
	}
	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public String getVremePolaska() {
		return vremePolaska;
	}
	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}
	public String getDestinacija() {
		return destinacija;
	}
	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}
	public Long getPrevoznikId() {
		return prevoznikId;
	}
	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}
	public String getPrevoznikNaziv() {
		return prevoznikNaziv;
	}
	public void setPrevoznikNaziv(String prevoznikNaziv) {
		this.prevoznikNaziv = prevoznikNaziv;
	}

	
	

}
