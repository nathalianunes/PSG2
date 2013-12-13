<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Ponto" %>
<%@ page import="dao.DaoPonto" %>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Pontos</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
<%
DaoPonto dao = DaoPonto.INSTANCE;

List<Ponto> pontos = new ArrayList<Ponto>();
pontos = dao.listPontos();
%>
  <div style="width: 100%;">
    <div class="line"></div>
    <div class="topLine">
      
      <div style="float: left;" class="headline">Pontos</div>
     
    </div>
  </div>

<div style="clear: both;"/>  
Existem <%=pontos.size() %>  Pontos.

<table>
  <tr>
      <th>Nome</th>
      <th>Tempo</th>
      <th>Remove</th>
    </tr>
 
<% for (Ponto ponto : pontos) {%>
<tr> 
<td>
<%=ponto.getNome() %>
</td>
<td>
 <%=ponto.getTempo() %>
</td>
<td>
<a class="remover" href="/removePonto?id=<%=ponto.getId()%>" >Remover</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">Novo Ponto</div>

<form action="/newPonto" method="post" accept-charset="utf-8">
  <table>
    <tr>
      <td><label for="nome">Nome</label></td>
      <td><input type="text" name="nome" id="nome" size="65"/></td>
    </tr>
    
  <tr>
      <td colspan="2" align="right"><input type="submit" value="Create"/></td>
    </tr>
  </table>
</form>

<div class="headline">Update Ponto</div>
<form action="/updatePonto" method="post" accept-charset="utf-8">
  <table>
    <tr>
      <td><label for="nome">Ponto</label></td>
      <td>
      	<select name="nome" id="nome">
      	<% for (Ponto ponto : pontos) {%>
  			<option value="<%=ponto.getNome() %>"><%=ponto.getNome() %></option>
  		<%}
		%>
		</select>
	  </td>
	  <td>
	  <label for="tempo">Tempo</label>
	  </td>
	  <td><input type="text" name="tempo" id="tempo" size="65"/></td>
    </tr>
    
  <tr>
      <td colspan="2" align="right"><input type="submit" value="Update"/></td>
    </tr>
  </table>
</form>

</div>

</div>
</body>
</html> 