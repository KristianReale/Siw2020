package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.Scuola;
import model.Studente;
import pensistence.DBManager;


public class DettagliScuola extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));		
		String line = reader.readLine();
		while (line != null){
			jsonReceived.append(line);
			line = reader.readLine();
		}		
		System.out.println(jsonReceived.toString());
		try {
			JSONObject json = new JSONObject(jsonReceived.toString());				
			Studente studente = new Studente();
			studente.setMatricola(json.getString("matricola"));
			studente.setCognome(json.getString("cognome"));			
			studente.setNome(json.getString("nome"));
		
			Studente dbStudente  = DBManager.getInstance().getStudenteDAO().findByPrimaryKey(studente.getMatricola());
			Scuola scuola = dbStudente.getScuolaDiDiploma(); 
			
			JSONObject jsonIndirizzo = new JSONObject(scuola);			
			PrintWriter out = resp.getWriter();
			System.out.println(jsonIndirizzo.toString());
			out.println(jsonIndirizzo.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
