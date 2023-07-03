<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Área da Locadora</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            margin-top: 0;
        }

        p {
            font-size: 18px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        caption {
            font-weight: bold;
            margin-bottom: 10px;
            text-align: center;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
            text-align: right;
        }

        li {
            display: inline-block;
            margin-right: 10px;
        }

        li:last-child {
            margin-right: 0;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .logout-button {
            background-color: #dc3545;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Área da Locadora</h1>
    <p>Olá ${sessionScope.usuarioLogado.nome}</p>
    <div align="center">
        <table border="1">
            <caption>Lista de Locações</caption>
            <tr>
                <th>Nome do Cliente</th>
                <th>CPF do Cliente</th>
                <th>Nome da Locadora</th>
                <th>CNPJ da Locadora</th>
                <th>Data e Hora da Locação</th>
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
            <a href="${pageContext.request.contextPath}/logout.jsp" class="logout-button">Sair</a>
        </li>
    </ul>
</body>
</html>
