package pensistence;

import java.util.ArrayList;
import java.util.List;

import model.Scuola;
import model.Studente;
import model.Utente;

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
		
		Studente st1 = new Studente();
		st1.setMatricola("45456");
		st1.setCognome("Bianchi");
		st1.setNome("Mario");
		studenti.add(st1);
		
		Studente st2 = new Studente();
		st2.setMatricola("21156");
		st2.setCognome("Rossi");
		st2.setNome("Rocco");
		studenti.add(st2);
		
	}
	
	public void inserisciStudente(Studente s) {
		studenti.add(s);
	}

	public Utente login(String username, String password) {
		if (username.equals("kristian") && password.equals("kristian")) {
			Utente u = new Utente();
			u.setUsername("kristian");
			u.setPassword("kristian");
			return u;
		}
		return null;
	}

	public List<Studente> dammiStudenti() {
		return studenti;
	}
}
