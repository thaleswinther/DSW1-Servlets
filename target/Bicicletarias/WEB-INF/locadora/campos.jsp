<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1">
	<caption>
   		<c:choose>
   			<c:when test="${editora != null}">
                               Edição
                           </c:when>
   			<c:otherwise>
                               Cadastro
                           </c:otherwise>
   		</c:choose>
	</caption>
 	<c:if test="${editora != null}">
   		<input type="hidden" name="id" value="${editora.id}" />
   	</c:if>
   	<tr>
   		<td><label for="nome">nome</label></td>
   		<td><input type="text" id="nome" name="nome" size="45"
   			required value="${editora.nome}" /></td>
   	</tr>
   	<tr>
   		<td><label for="CNPJ">CNPJ</label></td>
   		<td><input type="text" id="CNPJ" name="CNPJ" size="45" required
   			value="${editora.CNPJ}" /></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>