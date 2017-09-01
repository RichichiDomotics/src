<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<script type="text/javascript" src="resources/js/manejoTablas.js"></script>
<script>
  $(document).ready(function() {
    var oTable1 = $('#tablaResultadoAutoriza').dataTable();

  });


</script>
<style>
  .dataTable th[class*=sorting_] {
    color: #fff;
  }
</style>
<h1>Listado Prospectos</h1>

<div class="component">
  <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoAutoriza">
    <thead>
    <tr role="row">
      <th align="center">PROSPECTO</th>
      <th align="center">EJECUTIVO</th>
      <th align="center" >FECHA DE INGRESO</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${prospecto}" var="c">
    <tr>
    <td align="right" >${c.razonSocial}</td>
        <td align="center">
          <c:forEach items="${ejecutivos}" var="e">
            <c:if test="${e.id==c.idEjecutivo}">
              ${e.nombreEjecutivo}
            </c:if>
          </c:forEach>
        </td>
        <td align="right" ><fmt:parseDate value="${c.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
      </tr>
    </c:forEach>
    </tbody>

  </table>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>