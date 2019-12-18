package pensistence;

import java.util.ArrayList;
import java.util.List;

import model.Scuola;
import model.Studente;

public class DBManager {
	private static DBManager instance = null;
	
	List<Studente> studenti;
	List<Scuola> scuole;
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() {
		studenti = new ArrayList<Studente>();
		
		scuole = new ArrayList<Scuola>();
		Scuola s1 = new Scuola();
		s1.setId((long)1);
		s1.setCodiceMeccanografico("RCI3453453445");
		s1.setNome("Istituto Professionale");
		
		// TODO Auto-generated constructor stub
	}
	
	public void inserisciStudente(Studente s) {
		studenti.add(s);
	}
}
