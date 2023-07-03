<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% System.out.println("PASSEI POR: WEB-INF/locacao/campos.jsp"); %> 

<table border="1">
    <c:if test="${locadora != null}">
		<input type="hidden" name="id" value="${locacao.cliente.usuario.id}" />
	</c:if>
 	
   	<tr>
   		<td><label for="email">Email</label></td>
   		<td><input type="email" id="email" name="email" size="45"
   			required value="${cliente.email}" /></td>
   	</tr>
   	
    <tr>
		<td><label for="data_nascimento">Data de nascimento</label></td>
		<td><input type="date" id="data_nascimento" name="data_nascimento" required value="${cliente.dataNascimento}" /></td>
	</tr>
    
    
   	
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>