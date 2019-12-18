package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IscriviStudente extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricola = req.getParameter("matricola");
		String cognome = req.getParameter("cognome");
		String nome = req.getParameter("nome");
		String dataNascita = req.getParameter("dataNascita");
		String scuola = req.getParameter("scuola");
		
		System.out.println(matricola + " "  + nome + " " + cognome);
	}
}
