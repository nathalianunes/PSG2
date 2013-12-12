package service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoPonto;

public class UpdatePonto extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		      throws IOException {
		String nome = req.getParameter("nome");
		String tempoS = req.getParameter("tempo");
		
		double tempo;
		if (tempoS == null)
			tempo = 0;
		else
			tempo = Double.parseDouble(tempoS);
		
		DaoPonto.INSTANCE.update(nome, tempo);
		
		resp.sendRedirect("/PontoApplication.jsp");
	}
}
