package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CorsoDiLaurea;
import model.Dipartimento;
import pensistence.DBManager;

public class CorsiDiLaurea extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dip = req.getParameter("dipartimento");
		
		Dipartimento dipartimento = DBManager.getInstance()
				.getDipartimentoDAO().findByPrimaryKey(Long.parseLong(dip));
	
		resp.getOutputStream().println("<option>---</option>");
		for (CorsoDiLaurea cdl: dipartimento.getCorsiDiLaurea()) {
			resp.getOutputStream().println
			("<option value=\"" + cdl.getId() + "\"> "
					+ cdl.getNome() + "</option>");
		}
	
//		resp.getOutputStream()
	}
}
