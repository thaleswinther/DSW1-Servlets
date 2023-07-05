<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: flex-start;
                min-height: 100vh;
            }
            
            h1 {
                margin-top: 0;
            }
            
            #erro {
                background-color: #ffcccc;
                padding: 10px;
                margin-bottom: 20px;
            }
            
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            
            th, td {
                padding: 5px;
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
            
            select {
                padding: 5px;
            }
            
            input[type="submit"] {
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <h1>Autenticação de Usuário</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

            <form method="post" action="index.jsp">
                <table>
                    <tr>
                        <th>Login: </th>
                        <td><input type="text" name="login"
                                   value="${param.login}"/></td>
                    </tr>
                    <tr>
                        <th>Senha: </th>
                        <td><input type="password" name="senha" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <div style="text-align: right;">
                                <input type="submit" name="bOK" value="Entrar" />
                            </div>
                        </td>
                    </tr>
                </table>
            </form>



        <div align="center" style="margin-top: 50px;">
            <form id="formLista" method="post" action="index.jsp">
                <label for="cidade">Filtrar locadora por cidade:</label>
                <select name="cidade" id="cidade">
                    <option value="">Todas as cidades</option>
                    <c:forEach var="locadora" items="${sessionScope.listaLocadoras}">
                        <option value="${locadora.cidade}">${locadora.cidade}</option>
                    </c:forEach>
                </select>
                <input type="submit" name="bFiltrar" value="Filtrar" />
            </form>
        </div>
        
        <div align="center" style="margin-top: 10px;">
            <table border="1">
                <caption>Lista de Locadoras por Cidade</caption>
                <tr>
                    <th>Nome</th>
                    <th>CNPJ</th>
                    <th>Cidade</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty sessionScope.listaLocadorasFiltradas}">
                        <c:forEach var="locadoraFiltrada" items="${sessionScope.listaLocadorasFiltradas}">
                            <tr>
                                <td>${locadoraFiltrada.nome}</td>
                                <td>${locadoraFiltrada.CNPJ}</td>
                                <td>${locadoraFiltrada.cidade}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="locadora" items="${sessionScope.listaLocadoras}">
                            <tr>
                                <td>${locadora.nome}</td>
                                <td>${locadora.CNPJ}</td>
                                <td>${locadora.cidade}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </body>
</html>
