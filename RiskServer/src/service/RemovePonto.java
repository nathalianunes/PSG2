package service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ponto;
import dao.DaoPonto;

public class RemovePonto extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		      throws IOException {
		String idS = req.getParameter("id");
		int id = Integer.parseInt(idS);
		
		DaoPonto.INSTANCE.remove(id);
		
		resp.sendRedirect("/PontoApplication.jsp");
	}
}
