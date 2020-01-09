package model;

import java.util.List;

public class Dipartimento {
	Long id;
	String nome;
	List<CorsoDiLaurea> corsiDiLaurea;
	
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
	public List<CorsoDiLaurea> getCorsiDiLaurea() {
		return corsiDiLaurea;
	}
	public void setCorsiDiLaurea(List<CorsoDiLaurea> corsiDiLaurea) {
		this.corsiDiLaurea = corsiDiLaurea;
	}
	
	
}
