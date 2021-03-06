package service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoPontosDecubito;

import model.PontosDecubito;

public class AddPontosDecubito extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		      throws IOException {
		String ponto = req.getParameter("ponto");
		String decubito = req.getParameter("decubito");
		
		PontosDecubito pontosDecubito = new PontosDecubito(decubito, ponto);
		
		DaoPontosDecubito.INSTANCE.add(pontosDecubito);
		
		resp.sendRedirect("/PontosDecubitoApplication.jsp");
	}
}
