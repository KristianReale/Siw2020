package model;

public class Studente {
	private String matricola;
	private String cognome;
	private String nome;
	private String dataNascita;
	private Scuola scuolaDiDiploma;
	private CorsoDiLaurea corsoDiLaurea;
	
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
	
	public Scuola getScuolaDiDiploma() {
		return scuolaDiDiploma;
	}
	public void setScuolaDiDiploma(Scuola scuolaDiDiploma) {
		this.scuolaDiDiploma = scuolaDiDiploma;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public CorsoDiLaurea getCorsoDiLaurea() {
		return corsoDiLaurea;
	}
	
	public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea) {
		this.corsoDiLaurea = corsoDiLaurea;
	}
}
