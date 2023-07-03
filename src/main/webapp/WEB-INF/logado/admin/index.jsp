<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Menu do Sistema</title>
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

        p {
            text-align: center;
        }

        .link-box {
            margin-bottom: 10px;
        }

        .link-box a {
            display: block;
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            text-align: center;
            background-color: #007bff;
            padding: 10px;
            border-radius: 5px;
            width: 200px;
            margin: 0 auto;
            transition: background-color 0.3s ease;
        }

        .link-box a:hover {
            background-color: #0056b3;
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        li {
            margin-top: 10px;
        }

        .logout-link {
            display: block;
            color: #000;
            text-decoration: none;
            text-align: center;
            background-color: #007bff;
            padding: 10px;
            border-radius: 5px;
            width: 200px;
            margin: 0 auto;
            transition: background-color 0.3s ease;
        }

        .logout-link:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Página do Administrador</h1>
<p>Olá ${sessionScope.usuarioLogado.nome}</p>
<div class="link-box">
    <a href="clientes">Gerenciar Clientes</a>
</div>
<div class="link-box">
    <a href="locadoras">Gerenciar Locadoras</a>
</div>
<ul>
    <li>
        <a class="logout-link" href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
    </li>
</ul>
</body>
</html>
