<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% System.out.println("PASSEI POR: WEB-INF/locadora/campos.jsp"); %>

<!DOCTYPE html>
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

        table {
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            padding: 10px;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="submit"],
        input[type="radio"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        caption {
            font-weight: bold;
            margin-bottom: 10px;
        }

        .radio-group {
            display: flex;
            align-items: center;
        }

        .radio-group label {
            margin-right: 10px;
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<table border="1">
    <caption>
        <c:choose>
            <c:when test="${locadora != null}">
                Edição
            </c:when>
            <c:otherwise>
                Cadastro
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${locadora != null}">
        <input type="hidden" name="id" value="${locadora.id}" />
    </c:if>
    <tr>
        <td><label for="CNPJ">CNPJ</label></td>
        <td><input type="text" id="CNPJ" name="CNPJ" size="45" required value="${locadora.CNPJ}" /></td>
    </tr>
    <tr>
        <td><label for="cidade">Cidade</label></td>
        <td><input type="text" id="cidade" name="cidade" size="45" required value="${locadora.cidade}" /></td>
    </tr>
    <tr>
        <td><label for="email">Email</label></td>
        <td><input type="email" id="email" name="email" size="45" required value="${locadora.email}" /></td>
    </tr>
    <tr>
        <td><label for="senha">Senha</label></td>
        <td><input type="password" id="senha" name="senha" size="45" required value="${locadora.senha}" /></td>
    </tr>
    <tr>
        <td><label for="nome">Nome</label></td>
        <td><input type="text" id="nome" name="nome" size="45" required value="${locadora.nome}" /></td>
    </tr>
    <tr>
        <td><label for="papel">Papel</label></td>
        <td>
            <div class="radio-group">
                <input type="radio" id="locadora" name="papel" value="Locadora" ${locadora.papel == 'Locadora' ? 'checked' : ''} required>
                <label for="locadora">Locadora</label>
                <input type="radio" id="admin" name="papel" value="Admin" ${locadora.papel == 'Admin' ? 'checked' : ''} required>
                <label for="admin">Admin</label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
    </tr>
</table>
</body>
</html>
