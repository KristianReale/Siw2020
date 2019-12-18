package model;

public class Studente {
	private String matricola;
	private String cognome;
	private String nome;
	private String dataNascita;
	private Scuola scuola;
	
	public String getMatricola() {
		return matricola;
	}
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public Scuola getScuola() {
		return scuola;
	}
	public void setScuola(Scuola scuola) {
		this.scuola = scuola;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
