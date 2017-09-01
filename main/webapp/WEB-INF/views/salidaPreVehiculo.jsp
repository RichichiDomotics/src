<%--
  Created by IntelliJ IDEA.
  User: jolvera
  Date: 03/09/2014
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
        <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Tipo Vehiculo</th>
        <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Placas</th>
        <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Cliente</th>
        <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Nombre Operador</th>
        <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Empresa</th>
        <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled"></th>
    </tr>
    </thead>
    <c:set var="contador" value="0" />
    <c:forEach items="${vehiculos}" var="v">
        <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <td class="center  sorting_1">${(v[1]).descripcion}</td>
            <td class="center  sorting_1">${(v[0]).placasVehiculo}</td>
            <td class="center  sorting_1">${(v[0]).idCliente} ${(v[2]).nombreCliente}</td>
            <td class="center  sorting_1">${(v[0]).nombreOperador}</td>
            <td class="center  sorting_1">${(v[0]).nombreCiaTransporte}</td>
            <td><a href="ae_SalidaAutoriza?idVehiculo=${v[0].idIngresoVehiculo}"><b>DAR SALIDA</b></a></td>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>
