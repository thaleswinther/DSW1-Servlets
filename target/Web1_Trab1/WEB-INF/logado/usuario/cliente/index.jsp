<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<% System.out.println("PASSEI POR: WEB-INF/logado/usuario/cliente/index.jsp"); %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locação de Bicicletas</title>
    </head>
    <body>
        <h1>Página do Cliente</h1>
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
		<a href="${pageContext.request.contextPath}/usuario/locacoes/cadastro">Cadastrar uma locação</a>
        <div align="center">
            <table border="1">
                <caption>Lista de Locações</caption>
                <tr>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>CNPJ</th>
                    <th>Data e hora</th>
                </tr>
                <c:forEach var="locacao" items="${sessionScope.listaLocacoes}">
                    <c:if test="${locacao.cliente.email eq sessionScope.usuarioLogado.email}">
                        <tr>
                            <td>${locacao.cliente.nome}</td>
                            <td>${locacao.cliente.CPF}</td>
                            <td>${locacao.locadora.CNPJ}</td>
                            <td>${locacao.dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
            </li>
        </ul>
    </body>
</html>



