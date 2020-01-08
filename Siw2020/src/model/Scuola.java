package model;

import java.util.List;

public class Scuola {
	Long id;
	String nome;
	String codiceMeccanografico;
	List<Studente> studentiIscritti;
	
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
	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}
	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}
	
	public List<Studente> getStudentiIscritti() {
		return studentiIscritti;
	}
	
	public void setStudentiIscritti(List<Studente> studentiIscritti) {
		this.studentiIscritti = studentiIscritti;
	}
	
	
}
