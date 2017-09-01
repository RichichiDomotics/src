<%@ include file="/WEB-INF/views/include.jsp" %>
<c:if test="${conteoProspectos==0}">
    0
</c:if>
<c:if test="${conteoProspectos>0}">
    <h3>Prospectos coincidentes</h3>
    <table width="150px" aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoProspectoJefe">
        <thead>
        <tr role="row">
            <th>Prospectos</th>
            <th>Ejecutivo</th>
            <th>Fecha de Ingreso</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${prospectos}" var="c">
        <tr>
            <td>${c.razonSocial}</td>
            <td>
                <c:forEach items="${ejecutivos}" var="e">
                    <c:if test="${e.id==c.idEjecutivo}">
                        ${e.nombreEjecutivo}
                    </c:if>
                </c:forEach>
            </td>
            <td><fmt:parseDate value="${c.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</c:if>
