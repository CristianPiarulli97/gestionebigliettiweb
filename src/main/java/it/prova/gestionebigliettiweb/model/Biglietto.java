package it.prova.gestionebigliettiweb.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "biglietto")
public class Biglietto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")	
	private Long id;
	
	@Column(name = "provenienza")
	private String provenzienza;
	@Column(name = "destinazione")
	private String destinazione;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "prezzo")
	private Integer prezzo;
	
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	
	
	
	
	public Biglietto(String provenzienza, String destinazione) {
		super();
		this.provenzienza = provenzienza;
		this.destinazione = destinazione;
	}

	public Biglietto(String provenzienza, String destinazione, LocalDate data, Integer prezzo,
			LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		super();
		this.provenzienza = provenzienza;
		this.destinazione = destinazione;
		this.data = data;
		this.prezzo = prezzo;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}
	
	public Biglietto(Long id, String provenzienza, String destinazione, LocalDate data, Integer prezzo,
			LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		super();
		this.id = id;
		this.provenzienza = provenzienza;
		this.destinazione = destinazione;
		this.data = data;
		this.prezzo = prezzo;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}
	
	
	
	public Biglietto(Long id, String provenzienza, String destinazione, LocalDate data, Integer prezzo) {
		super();
		this.id = id;
		this.provenzienza = provenzienza;
		this.destinazione = destinazione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public Biglietto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvenzienza() {
		return provenzienza;
	}

	public void setProvenzienza(String provenzienza) {
		this.provenzienza = provenzienza;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	
	
}
