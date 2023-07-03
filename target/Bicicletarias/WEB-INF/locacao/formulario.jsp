<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Alocação Bicicleta</title>
</head>

<body>
    <% System.out.println("PASSEI POR: WEB-INF/locacao/formulario.jsp"); %> 
	<div align="center">
		<h1>Insira os dados para cadastrar uma nova locação</h1>
	</div>
	<div align="center">
	    <%@include file="campos.jsp"%>
	</div>
	<c:if test="${!empty requestScope.mensagens}">
		<ul class="erro">
			<c:forEach items="${requestScope.mensagens}" var="mensagem">
				<li>${mensagem}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>