package jwd.zavrsni.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Linija {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false)
	private Integer brojMesta;
	@Column(nullable=false, unique=true)
	private Double cena;
	@Column(nullable=false)
	private String vremePolaska;
	@Column
	private String destinacija;
	//jedan automobil može pripadati samo jednoj kompaniji, a jedna prevoznik može imati više automobila
	@ManyToOne(fetch=FetchType.EAGER)
	private Prevoznik prevoznik;
	//??
	@OneToMany(mappedBy="linija",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Rezervacija> rezervacije = new ArrayList<>();

	
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
	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	public void setPotrosnja(String destinacija) {
		this.destinacija = destinacija;
	}
	public Prevoznik getPrevoznik() {
		return prevoznik;
	}
	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if(prevoznik!=null && !prevoznik.getLinije().contains(this)){
			prevoznik.getLinije().add(this);
		}
	}
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	public void setNajmovi(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	//??
	public void addNajam(Rezervacija rezervacija){
		this.rezervacije.add(rezervacija);
		
		if(!this.equals(rezervacija.getLinija())){
			rezervacija.setLinija(this);
		}
	}
	
}
