<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<style>
  .titulo-nada{
    left: 200px;
  }
  .tab-content {
    width: 1225px;
  }
  .tabbable {
    width: 900px;
  }
</style>
<script>
  $(document).ready(function() {
    var oTable1 = $('#tablaResultado').dataTable();
    var oTable2 = $('#tablaResultadoProspectoJefe').dataTable();
    var oTable3 = $('#tablaResultadoAutoriza').dataTable();
    var oTable4 = $('#tablaResultadoSA').dataTable();
    var oTable5 = $('#tablaResultadoAC').dataTable();
  });

</script>
<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <strong>Listado Prospectos de ${ejecutivo}</strong>
  </div>
</div>

<!--table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable"-->


<div class="row-fluid">
  <div class="span6">
    <div class="tabbable">
      <ul class="nav nav-tabs" id="myTab" style="font-size: 13px">

        <li class="active">
          <a data-toggle="tab" href="#Seguimientos">
            <span class="icon-edit"></span>  Seguimientos
          </a>
        </li>

        <li class="">
          <a data-toggle="tab" href="#Autorizados">
            <i class="icon-briefcase"></i>
            Autorizados
          </a>
        </li>

        <li class="">
          <a data-toggle="tab" href="#Rechazados">
            <i class="icon-barcode"></i>
            Rechazados
          </a>
        </li>

        <li class="">
          <a data-toggle="tab" href="#Sinasignar">
            <i class="icon-check-empty"></i>
            No Autorizados
          </a>
        </li>
        <li class="">
          <a data-toggle="tab" href="#Aceptados">
            <i class="icon-check"></i>
            Aceptados
          </a>
        </li>
        <!--li class="">
            <a data-toggle="tab" href="#datosFacturacion">
                <i class="icon-download-alt"></i>
                Facturaci&oacute;n
            </a>
        </li-->


      </ul>
      <div class="tab-content">
        <div id="Seguimientos" class="tab-pane active">
          <section class="grupodedatos">

              <div class="component">
                <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultado">
                  <thead style="font-size: 12px">
                  <tr role="row">
                    <th align="center">PROSPECTO</th>
                    <th align="center" >RFC</th>
                    <th align="center" >DIRECCION</th>
                    <th align="center" >CONTACTO</th>
                    <th align="center" >EMAIL</th>
                    <th align="center" >TELEFONO</th>
                    <th align="center">ETAPA</th>
                    <th align="center">FECHA DE INGRESO</th>
                  </tr>
                  </thead>
                  <tbody aria-relevant="all" aria-live="polite" role="alert">
                  <c:forEach items="${seguimiento}" var="c">
                    <tr>
                      <td>${c.razonSocial}</td>
                      <td>${c.rfc}</td>
                      <td>${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
                      <td>${c.contacto1}</td>
                      <td>${c.email}</td>
                      <td>${c.telefono}</td>
                      <td>
                        <c:if test="${c.idEtapa==1}">
                          <span class="label label-purple arrowed-in-right">Presentaci&oacute;n</span>
                        </c:if>
                        <c:if test="${c.idEtapa==2}">
                          <span class="label label-grey arrowed-in-right arrowed-in">Presentaci&oacute;n de cuotas</span>
                        </c:if>
                        <c:if test="${c.idEtapa==4}">
                          <span class="label label-pink arrowed-right">Env&iacute;o de cotizaci&oacute;n</span>
                        </c:if>
                        <c:if test="${c.idEtapa==3}">
                          <span class="label label-yellow arrowed-in">Negociaci&oacute;n</span>
                        </c:if>
                        <c:if test="${c.idEtapa==5}">
                          <span class="label label-success arrowed-right">Aceptaci&oacute;n</span>
                        </c:if>
                        <c:if test="${c.idEtapa==6}">
                          <span class="label label-inverse arrowed-right">Negaci&oacute;n</span>
                        </c:if>
                      </td>
                      <td><fmt:parseDate value="${c.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
                    </tr>

                  </c:forEach>
                  </tbody>
                </table>
              </div>

          </section>
        </div>
        <div id="Autorizados" class="tab-pane">
          <div class="component">
            <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoAutoriza">
              <thead style="font-size: 12px">
              <tr role="row">
                <th align="center">PROSPECTO</th>
                <th align="center" >RFC</th>
                <th align="center" >DIRECCION</th>
                <th align="center" >CONTACTO</th>
                <th align="center" >EMAIL</th>
                <th align="center" >TELEFONO</th>
                <th align="center">FECHA DE INGRESO</th>
              </tr>
              </thead>
              <tbody aria-relevant="all" aria-live="polite" role="alert">
              <c:forEach items="${prospecto}" var="c">
                <tr>
                  <td align="center">${c.razonSocial}</td>
                  <td align="right" >${c.rfc}</td>
                  <td align="right" >${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
                  <td align="right" >${c.contacto1}</td>
                  <td align="right" >${c.email}</td>
                  <td align="right" >${c.telefono}</td>
                  <td align="right" ><fmt:parseDate value="${c.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
                </tr>
              </c:forEach>
              </tbody>

            </table>
          </div>
        </div>
        <div id="Rechazados" class="tab-pane">
          <div class="component">
            <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoProspectoJefe">
              <thead style="font-size: 12px">
              <tr role="row">
                <th align="center">PROSPECTO</th>
                <th align="center" >RFC</th>
                <th align="center" >DIRECCION</th>
                <th align="center" >CONTACTO</th>
                <th align="center" >EMAIL</th>
                <th align="center" >TELEFONO</th>
                <th align="center">FECHA DE INGRESO</th>
              </tr>
              </thead>
              <tbody aria-relevant="all" aria-live="polite" role="alert">
              <c:forEach items="${rechazados}" var="c">
              <tr>
                <td align="center">${c.razonSocial}</td>
                <td align="right" >${c.rfc}</td>
                <td align="right" >${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
                <td align="right" >${c.contacto1}</td>
                <td align="right" >${c.email}</td>
                <td align="right" >${c.telefono}</td>
                <td align="right" ><fmt:parseDate value="${c.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
              </tr>
              </tbody>
              </c:forEach>

            </table>
        </div>
      </div>
        <div id="Sinasignar" class="tab-pane">
          <div class="component">
            <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoSA">
              <thead style="font-size: 12px">
              <tr role="row">
                <th align="center">PROSPECTO</th>
                <th align="center" >RFC</th>
                <th align="center" >DIRECCION</th>
                <th align="center" >CONTACTO</th>
                <th align="center" >EMAIL</th>
                <th align="center" >TELEFONO</th>
                <th align="center">FECHA DE INGRESO</th>
              </tr>
              </thead>
              <tbody aria-relevant="all" aria-live="polite" role="alert">
              <c:forEach items="${sinasignar}" var="c">
                <tr>
                  <td align="center">${c.razonSocial}</td>
                  <td align="right" >${c.rfc}</td>
                  <td align="right" >${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
                  <td align="right" >${c.contacto1}</td>
                  <td align="right" >${c.email}</td>
                  <td align="right" >${c.telefono}</td>
                  <td align="right" ><fmt:parseDate value="${c.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
                </tr>
              </c:forEach>
              </tbody>

            </table>
          </div>
        </div>
        <div id="Aceptados" class="tab-pane">
          <div class="component">
            <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoAC">
              <thead style="font-size: 12px">
              <tr role="row">
                <th align="center">PROSPECTO</th>
                <th align="center" >RFC</th>
                <th align="center" >DIRECCION</th>
                <th align="center" >CONTACTO</th>
                <th align="center" >EMAIL</th>
                <th align="center" >TELEFONO</th>
                <th align="center">FECHA DE INGRESO</th>
              </tr>
              </thead>
              <tbody aria-relevant="all" aria-live="polite" role="alert">
              <c:forEach items="${aceptados}" var="c">
                <tr>
                  <td align="center">${c.razonSocial}</td>
                  <td align="right" >${c.rfc}</td>
                  <td align="right" >${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
                  <td align="right" >${c.contacto1}</td>
                  <td align="right" >${c.email}</td>
                  <td align="right" >${c.telefono}</td>
                  <td align="right" ><fmt:parseDate value="${c.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
                </tr>
              </c:forEach>
              </tbody>

            </table>
          </div>
        </div>
     </div>
    </div>
  </div>
</div>
