<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% System.out.println("PASSEI POR: WEB-INF/locacao/campos.jsp"); %> 

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${locadora!= null}">
                            Cadastro
            </c:when>
        </c:choose>
 </caption>
    <c:if test="${locadora != null}">
		<input type="hidden" name="id" value="${locacao.cliente.id}" />
	</c:if>

    <tr>
		<td><label for="locadora">Locadora</label></td>
		<td><select name="locadora_selecionada">
				<c:forEach items="${locadoras}" var="locadora">
					<option value="${locadora.key}"
						${locacao.locadora.nome==locadora.value ? 'selected' : '' }>
						${locadora.value}</option>
				</c:forEach>
		</select></td>
	</tr> 

    
 	
    <tr>
        <td><label for="data">Data</label></td>
        <td><input type="date" id="data" name="data" required value="${locacao.dataHora.toLocalDate()}" /></td>
    </tr>

    <tr>
        <td><label for="hora">Hora</label></td>
        <td>
            <select id="hora" name="hora" required>
                <option value="">Selecione a hora</option>
                <c:forEach begin="0" end="23" var="hour">
                    <c:set var="formattedHour" value="${hour < 10 ? '0' + hour : hour}" />
                    <c:set var="displayText" value="${formattedHour}:00" />
                    <option value="${formattedHour}" ${formattedHour == locacao.dataHora.getHour() ? 'selected' : ''}>
                        <fmt:formatNumber type="number" pattern="00" value="${formattedHour}" />:00
                    </option>
                </c:forEach>
            </select>
        </td>
    </tr>
    
    
   	
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>