package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Buffet {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	String nome;
	
	@NotBlank
	String descrizione;

	@OneToMany(cascade = {CascadeType.PERSIST})
	List<Piatto> piatti;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	Chef chef;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
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

	
}