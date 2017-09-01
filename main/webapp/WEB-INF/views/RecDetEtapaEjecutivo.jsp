<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script>
$(document).ready(function(){
    var placeholderEtapasEjecutivo = $('#piechart-placeholderEjecutivo').css({'width':'95%' , 'min-height':'200px'});


    <c:set var="contadorEtapaEjecutivo" value="0"/>
    var dataEtapaEjecutivo = [
        /*<c:forEach items="${repEjecutivoEtapasEjecutivo}" var="e">
        { label: "<c:out value="${e[1]}"/> <c:out value="${e[2]}"/>", data: '<c:out value="${e[2]}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
        <c:set var="contadorEtapaEjecutivo" value="${contadorEtapaEjecutivo+1}"/>
        </c:forEach>*/
        { label: "PRESENTACION", data: '<c:out value="${presentacion}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
        { label: "PRESENTACION DE CUOTAS", data: '<c:out value="${presentacioncuotas}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
        { label: "ENVIO DE COTIZACION", data: '<c:out value="${cotizacion}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
        { label: "NEGOCIACION", data: '<c:out value="${negociacion}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
        { label: "ACEPTACION", data: '<c:out value="${aceptacion}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
        { label: "NEGACION", data: '<c:out value="${negacion}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
    ]


    function drawPieChart(placeholder, data, position) {
        $.plot(placeholder, data, {
            series: {
                pie: {
                    show: true,
                    tilt:0.8,
                    highlight: {
                        opacity: 0.25
                    },
                    stroke: {
                        color: '#fff',
                        width: 2
                    },
                    startAngle: 2,
                    innerRadius: 0.5
                }
            },
            legend: {
                show: true,
                position: position || "ne",
                labelBoxBorderColor: null,
                margin:[-30,15]
            }
            ,
            grid: {
                hoverable: true,
                clickable: true
            }
        })
    }

    drawPieChart(placeholderEtapasEjecutivo, dataEtapaEjecutivo);


    placeholderEtapasEjecutivo.data('chart', dataEtapaEjecutivo);
    placeholderEtapasEjecutivo.data('draw', drawPieChart);

    var $tooltip = $("<div class='tooltip top in hide'><div class='tooltip-inner'></div></div>").appendTo('body');
    var previousPoint = null;

    placeholderEtapasEjecutivo.on('plothover', function (event, pos, item) {
        if(item) {
            if (previousPoint != item.seriesIndex) {
                previousPoint = item.seriesIndex;
                var tip = item.series['label'] + " : " + Math.round(item.series['percent']).toFixed(2)+'%';
                $tooltip.show().children(0).text(tip);
            }
            $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
        } else {
            $tooltip.hide();
            previousPoint = null;
        }

    });

});

</script>
<div class="contenidoGeneral">
    <!-- INICIO REPORTE GENERAL POR ETAPAS Ejecutivo -->
    <!--a href="javascript:visualizaEtapaEjecutivo();" class="btn btn-block btn-info veEtapasEjecutivo"><i class="icon-eye-open"></i> Ver Reporte Etapas por Ejecutivo</a-->
    <div class="contenidoReporteEtapasEjecutivo">
        <div class="contenidoTablaEtapaEjecutivo">
            <!------------------------------------------------------------------->
            <div class="span4">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>${nombreejecutivo}</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                <tr role="row">
                                    <th align="right" >Etapas</th>
                                    <th align="right" >Total</th>
                                </tr>
                                </thead>
                                <c:set var="TotalTotalEt" value="${0}"/>
                                <tbody aria-relevant="all" aria-live="polite" role="alert">
                               <!-- <c:forEach items="${repEjecutivoEtapasEjecutivo}" var="e">
                                <tr class="odd">
                                    <td align="right" >${e[1]}</td>
                                    <td align="right" >${e[2]}</td>
                                    <c:set var="TotalTotalEt" value="${TotalTotalEt+(e[2])}"/>
                                </tr>
                                </c:forEach>-->
                               <tr class="odd">
                                   <td align="right">PRESENTACION</td>
                                   <td align="right">${presentacion}</td>
                               </tr>
                               <tr class="odd">
                                   <td align="right">PRESENTACION DE CUOTAS</td>
                                   <td align="right">${presentacioncuotas}</td>
                               </tr>
                               <tr class="odd">
                                   <td align="right">ENVIO DE COTIZACION</td>
                                   <td align="right">${cotizacion}</td>
                               </tr>
                               <tr class="odd">
                                   <td align="right">NEGOCIACION</td>
                                   <td align="right">${negociacion}</td>
                               </tr>
                               <tr class="odd">
                                   <td align="right">ACEPTACION</td>
                                   <td align="right">${aceptacion}</td>
                               </tr>
                               <tr class="odd">
                                   <td align="right">NEGACION</td>
                                   <td align="right">${negacion}</td>
                               </tr>
                                <tr>
                                    <td align="right">Total: </td>
                                    <td align="right" >${presentacion+presentacioncuotas+cotizacion+negociacion+aceptacion+negacion}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-------------------------------------------->
        <!--********************************************-->
        <div class="graficas4">
            <div class="row-fluid contgrafi">
                <div class="span4">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4>Gr&aacute;fica Etapas</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="row-fluid">
                                    <div id="piechart-placeholderEjecutivo"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--*******************************************-->
    </div>
    <!-- FIN REPORTE GENERAL POR ETAPAS -->
</div>
