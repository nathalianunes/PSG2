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
		String parte = req.getParameter("parte");
		System.out.println("Obj"+obj);
		Double time = 0.0;
		
		System.out.println("Obj "+obj+" Parte "+parte);
		
		if (obj.equals("ponto")){
			Ponto ponto = DaoPonto.INSTANCE.find(parte);
			time = ponto.getTempo();
		}
		else{
			if (obj.equals("decubito")){
				Decubito decubito = DaoDecubito.INSTANCE.find(parte);
				time = decubito.getTempo();
			}
		}
		
		System.out.println("Tempo: "+time);
		
		final String output = time.toString();
		resp.setContentLength(output.length());
		
		resp.getOutputStream().write(output.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
		
		//resp.sendRedirect("/index.html");
	}
}
