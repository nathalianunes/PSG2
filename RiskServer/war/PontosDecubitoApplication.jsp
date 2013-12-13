<%@page import="model.PontosDecubito"%>
<%@page import="dao.DaoPontosDecubito"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Decubito" %>
<%@ page import="model.Ponto" %>
<%@ page import="dao.DaoDecubito" %>
<%@ page import="dao.DaoPonto" %>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Pontos Dec�bitos</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
<%
DaoDecubito daoD = DaoDecubito.INSTANCE;
DaoPonto daoP = DaoPonto.INSTANCE;
DaoPontosDecubito daoPD = DaoPontosDecubito.INSTANCE;

List<Decubito> decubitos = new ArrayList<Decubito>();
decubitos = daoD.listDecubitos();

List<Ponto> pontos = new ArrayList<Ponto>();
pontos = daoP.listPontos();

List<PontosDecubito> pontosDecubitos = new ArrayList<PontosDecubito>();
pontosDecubitos = daoPD.listAll();
%>
  <div style="width: 100%;">
    <div class="line"></div>
    <div class="topLine">
      
      <div style="float: left;" class="headline">Pontos Dec�bitos</div>
     
    </div>
  </div>

<div style="clear: both;"/>  
Existem <%=pontosDecubitos.size() %>  Pontos associados a Dec�bitos.

<table>
  <tr>
      <th>Ponto</th>
      <th>Decubito</th>
      <th>Remove</th>
    </tr>
 
<% for (PontosDecubito pontosDecubito : pontosDecubitos) {%>
<tr> 
<td>
<%=pontosDecubito.getPonto() %>
</td>
<td>
 <%=pontosDecubito.getDecubito() %>
</td>
<td>
<a class="remover" href="/removePontosDecubito?id=<%=pontosDecubito.getId()%>" >Remover</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">Novo PontoDecubito</div>

<form action="/newPontosDecubito" method="post" accept-charset="utf-8">
  <table>
    <tr>
      <td><label for="ponto">Ponto</label></td>
      <td>
      	<select name="ponto" id="ponto">
	      <% for (Ponto ponto : pontos) {%>
	  		<option value="<%=ponto.getNome() %>"><%=ponto.getNome() %></option>
	  		<%}
			%>
		</select>
	  </td>
    </tr>
    <tr>
      <td><label for="decubito">Decubito</label></td>
      <td>
      	<select name="decubito" id="decubito">
	      <% for (Decubito decubito : decubitos) {%>
	  		<option value="<%=decubito.getNome() %>"><%=decubito.getNome() %></option>
	  		<%}
			%>
		</select>
	  </td>
    </tr>
  	<tr>
      <td colspan="2" align="right"><input type="submit" value="Create"/></td>
    </tr>
  </table>
</form>
</div>

</div>
</body>
</html> 