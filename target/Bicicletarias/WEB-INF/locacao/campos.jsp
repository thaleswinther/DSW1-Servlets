<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% System.out.println("PASSEI POR: WEB-INF/locacao/campos.jsp"); %> 

<table border="1">
	<caption>
   		<c:choose>
   			<c:when test="${cliente != null}">
                               Edição
                           </c:when>
   			<c:otherwise>
                               Cadastro
                           </c:otherwise>
   		</c:choose>
	</caption>
 	<c:if test="${cliente != null}">
   		<input type="hidden" name="id" value="${cliente.id}" />
   	</c:if>
   	<tr>
   		<td><label for="email">Email</label></td>
   		<td><input type="email" id="email" name="email" size="45"
   			required value="${cliente.email}" /></td>
   	</tr>
   	<tr>
   		<td><label for="senha">Senha</label></td>
   		<td><input type="text" id="senha" name="senha" size="45" required
   			value="${cliente.senha}" /></td>
   	</tr>
   	<tr>
   		<td><label for="nome">Nome</label></td>
   		<td><input type="text" id="nome" name="nome" size="45" required
   			value="${cliente.nome}" /></td>
   	</tr>
    <tr>
        <td><label for="CPF">CPF</label></td>
        <td><input type="text" id="CPF" name="CPF" size="45" required
            value="${cliente.CPF}" /></td>
    </tr>
    <tr>
        <td><label for="sexo">Sexo</label></td>
        <td><input type="text" id="sexo" name="sexo" size="45" required
            value="${cliente.sexo}" /></td>
    </tr>
    <tr>
        <td><label for="telefone">Telefone</label></td>
        <td><input type="text" id="telefone" name="telefone" size="45" required
            value="${cliente.telefone}" /></td>
    </tr>
    <tr>
		<td><label for="data_nascimento">Data de nascimento</label></td>
		<td><input type="date" id="data_nascimento" name="data_nascimento" required value="${cliente.dataNascimento}" /></td>
	</tr>
    <tr>
		<td><label for="papel">Papel</label></td>
		<td><input type="text" id="papel" name="papel" required value="${cliente.papel}" /></td>
	</tr>
    
   	
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>