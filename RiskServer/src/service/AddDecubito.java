package service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoDecubito;

import model.Decubito;

public class AddDecubito extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		      throws IOException {
		String nome = req.getParameter("nome");
		String tempoS = req.getParameter("tempo");
		long tempo;
		if (tempoS == null)
			tempo = 0;
		else
			tempo = Long.parseLong(tempoS);
		
		
		Decubito decubito = new Decubito(nome, tempo);
		DaoDecubito.INSTANCE.add(decubito);
		
		resp.sendRedirect("/DecubitoApplication.jsp");
	}
}
