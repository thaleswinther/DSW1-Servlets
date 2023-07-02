<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Livraria Virtual</title>
</head>
<body>

    <% System.out.println("PASSEI POR: WEB-INF/cliente/lista.jsp"); %> 
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Clientes</h1>
		<h2>
			<a href="/<%=contextPath%>/admin/">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				href="/<%=contextPath%>/admin/clientes/cadastro">Adicione Novo Cliente</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Clientes</caption>
			<tr>
				<th>CPF</th>
				<th>Telefone</th>
				<th>Sexo</th>
				<th>Data de nascimento</th>
                <th>Email</th>
                <th>Senha</th>
                <th>Nome</th>
                <th>Papel</th>
                <th>Ações</th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listaClientes}">
				<tr>
					<td>${cliente.CPF}</td>
					<td>${cliente.telefone}</td>
					<td>${cliente.sexo}</td>
                    <td>${cliente.dataNascimento}</td>
					<td>${cliente.email}</td>
					<td>${cliente.senha}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.papel}</td>
					<td><a href="/<%= contextPath%>/admin/clientes/edicao?id=${cliente.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/admin/clientes/remocao?id=${cliente.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>