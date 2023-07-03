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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }

        .container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: flex-start;
            max-width: 600px;
            margin: 0 auto;
        }

        .header {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        .greeting {
            margin-bottom: 20px;
        }

        .links {
            margin-bottom: 20px;
        }

        .links a {
            display: block;
            margin-bottom: 10px;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            width: 150px;
            text-align: center;
        }

        table {
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

        .logout-btn {
            position: fixed;
            top: 20px;
            right: 20px;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
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
    <div class="header">
        <h1>Área do Cliente</h1>
        <div class="greeting">
            <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        </div>
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
    <a href="${pageContext.request.contextPath}/logout.jsp" class="logout-btn">Sair</a>
</div>
</body>
</html>
