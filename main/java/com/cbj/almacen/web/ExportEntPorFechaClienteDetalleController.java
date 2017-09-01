package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.service.CatalogoManager;
import com.cbj.almacen.service.ClienteManager;
import com.cbj.almacen.service.DetalleRdManager;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Created by RICHARD on 07/07/2014.
 */
@Controller
@Scope("prototype")
@RequestMapping("/exportEntPorFechaClienteDetalle")
public class ExportEntPorFechaClienteDetalleController {
    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    private static final String PARAMETER_ITEM_NAME = "itemName";
    private static final String PARAMETER_TYPE = "type";
    private static final String VALUE_TYPE_PDF = "pdf";
    private static final String VALUE_TYPE_XLS = "xls";
    private String rdAnterior = "";
    private String rdActual = "";
    private BigDecimal suma = new BigDecimal(0.0);
    private AggregationSubtotalBuilder<Double> quantitySumPza;
    private AggregationSubtotalBuilder<Double> quantitySumTotKgs;

    private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE =
            new HashMap<String, String>();

    static {
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
    }

    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "fechaInicio") String fechaInicio,
                       @RequestParam(value = "fechaFin") String fechaFin,
                       @RequestParam(value = "idCliente") String idCliente,
                       @RequestParam(value = "nombreCliente") String nombreCliente
    ) throws IOException, ServletException {
        String fileType = request.getParameter(PARAMETER_TYPE);
        logger.info("Exporting {} report", fileType);

        response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
        OutputStream out = response.getOutputStream();
        try {
            JasperReportBuilder jrb = createJasperReport(fechaInicio,fechaFin,idCliente,nombreCliente);

            if (VALUE_TYPE_PDF.equals(fileType)) {
                jrb.toPdf(out);
            } else if (VALUE_TYPE_XLS.equals(fileType)) {
                jrb.toExcelApiXls(out);
            }
        } catch (DRException e) {
            throw new ServletException(e);
        }
        out.close();
    }

    private JasperReportBuilder createJasperReport(String fechaInicio,String fechaFin, String idCliente, String nombreCliente) {
        // Here use DynamicReports API to build a report
        // and fill it with a JRDataSource.
        // I used SearchController session bean
        // to get required search results data.

        try {
            URL url = new URL("http://localhost:8080/almacen/resources/img/logo-arcosa.png");
            StyleBuilder boldStyle = stl.style().setFontSize(5).setBold(false);
            StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                    .setFontSize(5)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                    .setBorder(stl.pen1Point())
                    .setFontSize(8)
                    .setBackgroundColor(Color.LIGHT_GRAY);
            StyleBuilder textStyle = stl.style(boldCenteredStyle)
                    .setBorder(stl.pen1Point())
                    .setFontSize(7)
                    .setBackgroundColor(Color.white);
            StyleBuilder titleStyle = stl.style(boldCenteredStyle)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(10);
            StyleBuilder titleStyleMini = stl.style(boldCenteredStyle)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(5);
            StyleBuilder textStyle2 = stl.style(boldCenteredStyle)
                    .setFontSize(10)
                    .setBackgroundColor(Color.white);

            TextColumnBuilder<Double> quantityPza = col.column("Cantidad(Pzas)", "cantidad", type.doubleType()).setWidth(55).setPattern("###,###,###");
            TextColumnBuilder<Double> quantityTotKgs = col.column("Total Kgs", "pesoTotal", type.doubleType()).setPattern("###,###,###.##");
            quantitySumPza = sbt.sum(quantityPza).setLabel("Total");
            quantitySumTotKgs = sbt.sum(quantityTotKgs).setLabel("Total");

            //StyleBuilder estiloPageHeader = new StyleBuilder();
            return report()//create new report design
                    // .setTitleStyle(estiloPageHeader)
                    .setColumnTitleStyle(columnTitleStyle)
                    .setTextStyle(textStyle)
                    .highlightDetailEvenRows()
                    .columns(//add columns
                            //            title,     field name     data type
                            col.column("Fecha", "horaCaptura", type.stringType()),
                            col.column("RD", "consecutivo", type.integerType()).setWidth(50).setPattern("##########"),
                            col.column("Renglon", "renglon", type.doubleType()).setWidth(50).setPattern("###,###,###"),
                            col.column("Camara", "camara", type.stringType()).setWidth(50).setPattern("###"),
                            col.column("Producto", "descripcion", type.stringType()).setWidth(170),
                            col.column("Caducidad", "caducidad", type.stringType()),
                            col.column("Lote", "lote", type.stringType()),
                            col.column("Marca", "marca", type.stringType()),
                            quantityPza,
                            quantityTotKgs
                    )
                            //col.column("Total por RD", new ExpressionColumn()))
                    .title(cmp.horizontalList()
                            .add(cmp.image(url).setFixedDimension(80, 80),
                                    cmp.verticalList()
                                            .add(
                                                    cmp.text("ALMACENES REFRIGERADOS CONSOLIDADOS S.A. DE C.V PLANTA 3").setStyle(titleStyle).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT),
                                                    cmp.text("Operaciones - Inventario del recibo de Deposito").setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT),
                                                    cmp.text(Utils.getFechaTexto()).setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT)
                                                    //shows report title
                                            )
                            ),//shows report title
                            cmp.horizontalList()
                                    .add(cmp.text("").setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.CENTER))
                    )//shows report title
                    .subtotalsAtSummary(quantitySumPza,quantitySumTotKgs)
                    .pageFooter(cmp.pageXofY()).addSubtotalAtColumnFooter()//shows number of page at page footer
                    .setDataSource(createDataSource(fechaInicio, fechaFin, idCliente))//set datasource
                            //.show();//create and show report
                    .rebuild();
            //.show(false);//create and show report

        } catch (DRException e) {
            logger.error("Error en DR " + e);
            //} catch (MalformedURLException e) {
            //    logger.error("Error en URL " + e);
        } catch (Exception e) {
            logger.error("Error " + e);
        }
        return report();
    }

    private JRDataSource createDataSource(String fechaInicio,String fechaFin,String idCliente) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final SimpleDateFormat sdfQuery = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateInicio = null;
        Date dateFin = null;
        try {
            dateInicio = sdf.parse(fechaInicio);
            dateFin = sdf.parse(fechaFin);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        final List<Object[]> oList = this.detalleRdManager.getKilosByFechasClienteDetalle(dateInicio, dateFin, idCliente);
        final List<DetallesRd> responseList = new ArrayList<DetallesRd>();
        final List<Object[]> res = oList;
        for (Object[] o : res) {
            final DetallesRd detallesRd = new DetallesRd();
            try {
                final Date fechaDate = sdfQuery.parse(String.valueOf(o[0]));
                detallesRd.setHoraCaptura(df.format(fechaDate));
                detallesRd.setConsecutivo((Integer) o[1]);
                detallesRd.setRenglon((Double) o[2]);
                detallesRd.setCamara((String) o[3]);
                detallesRd.setDescripcion((String) o[4]);
                detallesRd.setCaducidad((String) o[5]);
                detallesRd.setLote((String) o[6]);
                detallesRd.setMarca((String) o[7]);
                detallesRd.setCantidad((Double) o[8]);
                detallesRd.setPesou((Double) o[9]);
                detallesRd.setPesoTotal((Double) o[10]);
                responseList.add(detallesRd);
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }
        }
        return new JRBeanCollectionDataSource(responseList);
    }

}
