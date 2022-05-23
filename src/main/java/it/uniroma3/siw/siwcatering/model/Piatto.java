package it.uniroma3.siw.siwcatering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Piatto {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@NotBlank
	String nome;
	
	@NotBlank
	String descrizione;
	
	@OneToMany(cascade = {CascadeType.PERSIST})
	List<Ingradiente> ingradienti;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Ingradiente> getIngradienti() {
		return ingradienti;
	}

	public void setIngradienti(List<Ingradiente> ingradienti) {
		this.ingradienti = ingradienti;
	}
	
	
}
