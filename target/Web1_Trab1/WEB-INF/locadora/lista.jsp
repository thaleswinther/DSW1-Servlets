<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Locação de Bicicletas</title>
</head>
<body>

    <% System.out.println("PASSEI POR: WEB-INF/locadora/lista.jsp"); %>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Locadoras</h1>
		<h2>
			<a href="/<%=contextPath%>/admin/">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				href="/<%=contextPath%>/admin/locadoras/cadastro">Adicione Nova Locadora</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Locadoras</caption>
			<tr>
				<th>CNPJ</th>
				<th>Cidade</th>
                <th>Email</th>
                <th>Nome</th>
                <th>Papel</th>
                <th>Ações</th>
			</tr>
			<c:forEach var="locadora" items="${requestScope.listaLocadoras}">
				<tr>
					<td>${locadora.CNPJ}</td>
					<td>${locadora.cidade}</td>
					<td>${locadora.email}</td>
                    <td>${locadora.nome}</td>
                    <td>${locadora.papel}</td>
					<td><a href="/<%= contextPath%>/admin/locadoras/edicao?id=${locadora.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/admin/locadoras/remocao?id=${locadora.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
