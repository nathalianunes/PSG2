package service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Decubito;
import model.Ponto;

import dao.DaoDecubito;
import dao.DaoPonto;

public class GetTime extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		      throws IOException {
		String obj = req.getParameter("obj");
		
		if (obj.equals("ponto")){
			Ponto ponto = DaoPonto.INSTANCE.find(obj);
			double time = ponto.getTempo();
		}
		else{
			Decubito decubito = DaoDecubito.INSTANCE.find(obj);
			double time = decubito.getTempo();
		}
		
		
		
		resp.sendRedirect("/index.html");
	}
}
