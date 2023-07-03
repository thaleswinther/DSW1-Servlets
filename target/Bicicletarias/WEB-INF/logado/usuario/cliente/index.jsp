<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página do Cliente</title>
    </head>
    <body>
        <h1>Página do Cliente</h1>
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        <a href="locadoras">Cadastrar Locação</a>
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
                    <tr>
                        <td>${locacao.cliente.nome}</td>
                        <td>${locacao.cliente.CPF}</td>
                        <td>${locacao.locadora.CNPJ}</td>
                        <td>${locacao.dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))}</td>
                    </tr>
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



