<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<script>
$(document).ready(function(){
	$(".exportaRep").click(function(){
		tipoarchivo = "xls";
		consecutivo = $("#consecutivo").val();
		camara = $("#camara").val();
		idCliente = $("#idCliente").val();
		claveProducto = $("#claveProducto").val();
		tunel = $("#tunel").val();
		$.ajax("export", {
 		   "type": "GET", 
 		  /*"beforeSend": function() {
 			   $("#buscainventario").css("font-size","27px");
 			   $("#buscainventario").html('Cargando ...');
 		   },*/
 		  "success": function(result){
 		       /*$("#buscainventario").css("font-size","0.875em");
			   $("#buscainventario").html(result);
			   return false;*/
		   },
 		   "error": function(result) {
 		     alert("Error al recuperar la informacion", result);
 		     //showAlert("Error al recuperar la informaci�n", result);
 		   },
 		   "data": { type: tipoarchivo,consecutivo: consecutivo,camara : camara, idCliente : idCliente, 
 			   		 claveProducto : claveProducto,tunel:tunel},
 		   "async": true
 		});
		
		return false;
	});

	exportaxlspdf = function(tipoarchivo){
		
	};  
	
});	
var conteo=1;
</script>
<fmt:setLocale value="en_US"/>
<c:set var="cantidadRegistros" value="${fn:length(reporte)}"/>
<c:if test="${cantidadRegistros > 0}">
<c:if test="${idCliente ne '' || consecutivo ne ''}">
	<div class="alert alert-block alert-info">
		<strong class="blue">
            <table border="0">
                <tr>
                    <td align="right">
                        <i class="icon-cogs blue"></i>
                    </td>
                    <td>
                        CLIENTE:</td>
                    <td>${reporte[0].idCliente} ${reporte[0].nomCliente}</td>
                    <fmt:parseDate value="${reporte[0].fechaCaptura}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
                </tr>
            </table>
        </strong>
	</div>
</c:if>
<a class="btn btn-primary btn-small" target="_blank" href="export?type=xls&idCliente=${idCliente}&consecutivo=${consecutivo}&camara=${camara}&claveProducto=${claveProducto}&tunel=${tunel}&fechaIngreso=<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />&consecutivoCliente=${reporte[0].consecutivo}">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
<a class="btn btn-primary btn-small" target="_blank" href="export?type=pdf&idCliente=${idCliente}&consecutivo=${consecutivo}&camara=${camara}&claveProducto=${claveProducto}&tunel=${tunel}&fechaIngreso=<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />&consecutivoCliente=${reporte[0].consecutivo}">EXPORTA PDF  <span class="glyphicon glyphicon-th-list"></span></a>
<br>
</c:if>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
          <tr role="row">
              <!-- th width="20%">Id</th>
              <th width="20%">ID Cliente</th-->
              <c:if test="${idCliente eq '' && consecutivo eq ''}">
                <th>Cliente</th>
              </c:if>
              <th>RD</th>
              <th>Fecha Ingreso</th>
              <th>Renglon</th>
              <th>Camara</th>
              <th>Lote</th>
              <th>Caducidad</th>
              <th>Cantidad Pza.</th>
              <th>Peso U Kg.</th>
              <th>Descripci&oacute;n</th>
              <th>Embalaje</th>
              <th>Marca</th>
              <th>Valor Total</th>
              <th>Total por Renglon</th>
              <th>Total por RD</th>
           </tr>
           </thead>
    <c:set var="contador" value="${0}"/> 
    <c:set var="total" value="${0}"/> 
    <c:set var="totalpzas" value="${0}"/>  
    <c:set var="subtotalRD" value="${0}"/>
    <c:set var="totalRenglon" value="${0}"/>
    <c:forEach items="${reporte}" var="det">
    <tbody aria-relevant="all" aria-live="polite" role="alert">
        <tr class="odd">
        <!-- td>${det.idInventario}</td>
        <td class="center  sorting_1">${det.idCliente}</td-->
        <c:if test="${idCliente eq '' && consecutivo eq ''}">
        <td>${det.idCliente} ${det.nomCliente}</td>
        </c:if>
        <td>${det.consecutivo}</td>
        <td><fmt:parseDate value="${det.fechaCaptura}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
            <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /></td>

        <td><fmt:formatNumber value="${det.renglon}" maxFractionDigits="0"/></td>

        <td>
        	<c:if test="${det.camara eq ''}">
        		Camara 12
        	</c:if>	
        	
        	<c:if test="${det.camara ne ''}">
        		${det.camara}
        	</c:if>
        </td>
        <td>${det.lote}</td>
        <td>${det.caducidad}</td>
        <td><fmt:formatNumber pattern="###,###,###.##" value="${det.cantidadInventario}"/></td>
        <td><fmt:formatNumber pattern="###,###,###.##" value="${det.pesou}"/></td>
        <td>${det.descripcion}</td>
        <td>${det.embalaje}</td>
        <td>${det.marca}</td>
        <td><fmt:formatNumber pattern="###,###,###.##" value="${det.valorTotal}"/></td>
        <!-- td></td-->
        <c:set var="contador" value="${contador+1}"/>
        <c:set var="totalRenglon" value="${(det.cantidadInventario*det.pesou)}"/>
        <c:set var="subtotalRD" value="${(subtotalRD) + (totalRenglon)}"/>
        <td><fmt:formatNumber pattern="###,###,###.##" value="${totalRenglon}"/></td>
        <td>
        	<c:if test="${det.consecutivo != reporte[contador].consecutivo}">
        		<fmt:formatNumber pattern="###,###,###.##" value="${subtotalRD}"/>
        		<c:set var="subtotalRD" value="${0}"/>
        	</c:if>
        </td>
        <c:set var="total" value="${total+(det.cantidadInventario*det.pesou)}"/>
        <c:set var="totalpzas" value="${totalpzas+(det.cantidadInventario)}"/>
    </tr>
    <tr>
        <td colspan="14"><b>OBSERVACIONES : </b>${det.observaciones}</td>
    </tr>
    </tbody>
    <script>conteo++;</script>
    </c:forEach>
</table>
<br>
<fmt:setLocale value="es_MX"/>
<div id="totalInventario">Total Kilos :<fmt:formatNumber type="number" pattern="###,###,###.##" value="${total}"/></div>
<br><div id="totalInventario">Total Piezas :<fmt:formatNumber type="number" pattern="###,###,###.##" value="${totalpzas}"/></div>

<script>
  /* $("#RENGLONVISTA").val(conteo);
   $("#RENGLON").val(conteo);
   $("#PRODUCTO").val("");
   $("#LOTE").val("");
   $("#CANTIDAD").val("");
   $("#CADUCIDAD").val("");
   $("#PESOU").val("");
   $("#EMBALAJE").val("");
   $("#MARCA").val("");
   $("#BULTOS").val("");
   $("#PRODUCTO").focus();*/
</script>