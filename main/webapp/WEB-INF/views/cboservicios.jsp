<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <div class="form-group">
   	 <label>ELIJA LA MANIOBRA ESPECIAL</label>
    	<select id="TIPOSERVICIO" name="TIPOSERVICIO" required class="form-control">
    		<option value="" ><fmt:message key="entradas.select"/></option>
    		<c:forEach items="${servicios}" var="servicio">
            	<option value="${servicio.clave}"><c:out value="${servicio.descripcion}"/></option>
            </c:forEach>
    	</select>
 </div>
