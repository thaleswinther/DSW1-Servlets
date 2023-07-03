<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Locação de Bicicletas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
        }

        table {
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e0e0e0;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        caption {
            font-weight: bold;
            margin-bottom: 10px;
        }

        .center {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    String contextPath = request.getContextPath().replace("/", "");
%>
<div class="center">
    <h1>Gerenciamento de Locadoras</h1>
    <h2>
        <a href="/<%=contextPath%>/admin/">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/<%=contextPath%>/admin/locadoras/cadastro">Adicione Nova Locadora</a>
    </h2>
</div>

<div class="center">
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
                <td>
                    <a href="/<%= contextPath %>/admin/locadoras/edicao?id=${locadora.id}">Edição</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/<%= contextPath %>/admin/locadoras/remocao?id=${locadora.id}"
                       onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                        Remoção
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
