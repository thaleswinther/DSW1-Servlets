<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<% System.out.println("PASSEI POR: WEB-INF/logado/usuario/cliente/index.jsp"); %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Área do Cliente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: flex-start;
            min-height: 100vh;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            text-align: left;
        }

        h1 {
            margin-top: 0;
        }

        .greeting {
            margin-bottom: 20px;
        }

        .links {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-bottom: 20px;
        }

        .links a {
            display: inline-block;
            margin-bottom: 10px;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
        }

        .btn-logout {
            display: inline-block;
            padding: 10px 20px;
            background-color: #ff0000;
            color: #fff;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
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
    </style>
</head>
<body>
<div class="container">
    <h1>Área do Cliente</h1>
    <div class="greeting">
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        <div class="links">
            <a href="${pageContext.request.contextPath}/usuario/locacoes/cadastro">Cadastrar uma locação</a>
        </div>
    </div>
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
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/logout.jsp" class="btn-logout">Sair</a>
        </li>
    </ul>
</div>
</body>
</html>
