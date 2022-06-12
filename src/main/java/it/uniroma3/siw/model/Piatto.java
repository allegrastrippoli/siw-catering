package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Piatto {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;

	@ManyToMany
	private List<Ingrediente> ingredienti;


	public Long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingradienti) {
		this.ingredienti = ingradienti;
	}
	
	
}
