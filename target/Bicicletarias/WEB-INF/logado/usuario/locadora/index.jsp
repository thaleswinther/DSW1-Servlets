<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locação de Bicicletas</title>
    </head>
    <body>
        <h1>Página da Locadora</h1>
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        <div align="center">
            <table border="1">
                <caption>Lista de Locações</caption>
                <tr>
                    <th>Nome do Cliente</th>
                    <th>CPF do Cliente</th>
                    <th>Nome do Locadora</th>
                    <th>CNPJ da Locadora</th>
                    <th>Data e hora da Locação</th>
                </tr>
                <c:forEach var="locacao" items="${sessionScope.listaLocacoes}">
                    <c:if test="${locacao.locadora.email eq sessionScope.usuarioLogado.email}">
                        <tr>
                            <td>${locacao.cliente.nome}</td>
                            <td>${locacao.cliente.CPF}</td>
                            <td>${locacao.locadora.nome}</td>
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



