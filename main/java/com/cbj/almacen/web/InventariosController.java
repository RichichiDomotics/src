package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.Inventarioview;
import com.cbj.almacen.domain.Ubicaciones;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.*;


/**
 * Handles requests for the application home page.
 */
@Controller
public class InventariosController {

    private static final Logger logger = LoggerFactory
	    .getLogger(InventariosController.class);

    @Autowired
    private InventarioManager inventarioManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private CatUbicacionesManager catUbicacionesManager;
    @Autowired
    private UbicacionesManager ubicacionesManager;
    @Autowired
    private DetalleRdManager detalleRdManager;
    private List<Object> comparator;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/ae_formInventarios", method = RequestMethod.GET)
    public String consultaClientes(Locale locale, Model model) {
	logger.info("Inventarios/formInventarios", locale);

	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);
	model.addAttribute("now", formattedDate);
	model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
	return "formInventarios";
    }
    
    @RequestMapping(value = "/inventariosReporte", method = RequestMethod.POST)
    public String inventariosReporte(Locale locale, Model model,
    @RequestParam(value = "consecutivo") String consecutivo,
    @RequestParam(value = "camara") String camara,
    @RequestParam(value = "idCliente") String idCliente,
    @RequestParam(value = "claveProducto") String claveProducto,
    @RequestParam(value = "tunel") String tunel )
    {
        List<Inventarioview> inventarios = this.inventarioManager.getInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel);
        model.addAttribute("reporte", this.inventarioManager.getInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel));
        model.addAttribute("consecutivo", consecutivo);
        model.addAttribute("camara", camara);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("claveProducto", claveProducto);
        model.addAttribute("tunel", tunel);
	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);
	model.addAttribute("now", formattedDate);
	return "inventariosReporte";

    }

    @RequestMapping(value = "/ae_formInventariosCamara", method = RequestMethod.GET)
    public String ae_formInventariosCamara(Locale locale, Model model)
    {
        final List<?> camaras = this.inventarioManager.getSaldoXCamara();
        final List<?> tunel = this.inventarioManager.getSaldoXTunel();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("camaras", camaras);
        model.addAttribute("tunel", tunel);
        return "saldosXCamara";

    }

    @RequestMapping(value = "/ae_formInventariosCliente", method = RequestMethod.GET)
    public String ae_formInventariosCliente(Locale locale, Model model)
    {
        final List<?> clientes = this.inventarioManager.getSaldoXCliente();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", clientes);
        return "saldosXCliente";

    }

    @RequestMapping(value = "/ae_formInventariosTodaCamaras", method = RequestMethod.GET)
    public String ae_formInventariosTodaCamaras(Locale locale, Model model)
    {
        final List<?> clientes = this.inventarioManager.getTodasCamaras();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", clientes);
        return "todasCamaras";

    }

    @RequestMapping(value = "/alm_regUbicaciones", method = RequestMethod.GET)
    public String registraUbicacion(Locale locale, Model model) {
        logger.info("Menu Consultas/alm_regUbicaciones", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("camaras", this.catUbicacionesManager.getCamaras());
        return "registraUbicacion";
    }

    @RequestMapping(value = "/cargaPuerta", method = RequestMethod.POST)
    public String cargaPuerta(Locale locale, Model model,String camara) {
        logger.info("cargaPuerta", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("puertas", this.catUbicacionesManager.getPuertas(camara));
        return "cargaPuerta";
    }

    @RequestMapping(value = "/cargaPasillo", method = RequestMethod.POST)
    public String cargaPasillo(Locale locale, Model model,String Puerta, String Camara) {
        logger.info("cargaPasillo "+Puerta+ " "+Camara, locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("pasillos", this.catUbicacionesManager.getPasillos(Puerta,Camara));
        return "cargaPasillo";
    }

    @RequestMapping(value = "/cargaFila", method = RequestMethod.POST)
    public String cargaFila(Locale locale, Model model,String pasillo,String puerta,String camara) {
        logger.info("cargaFila", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("filas", this.catUbicacionesManager.getFilas(pasillo,puerta,camara));
        return "cargaFila";
    }

    @RequestMapping(value = "/cargaPosicion", method = RequestMethod.POST)
     public String cargaPosicion(Locale locale, Model model,String pasillo,String puerta,String camara, String fila) {
        logger.info("cargaPosicion", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("posiciones", this.catUbicacionesManager.getPosiciones(pasillo,puerta,camara,fila));
        return "cargaPosicion";
    }

    @RequestMapping(value = "/cargaNivel", method = RequestMethod.POST)
    public String cargaNivel(Locale locale, Model model,String pasillo,String puerta,String camara, String fila) {
        logger.info("cargaNivel", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("niveles", this.catUbicacionesManager.getNiveles(pasillo,puerta,camara,fila));
        return "cargaNivel";
    }

    @RequestMapping(value = "/insPosicion", method = RequestMethod.POST)
    public String insPosicion(@Valid Ubicaciones ubicaciones,Locale locale, Model model) {
        logger.info("Inserta posicion", locale);

        Ubicaciones ubicacionrec = new Ubicaciones();
        boolean upbandera = false;
        ubicacionrec = this.ubicacionesManager.getRevisionUbicacion(ubicaciones.getCamara(),ubicaciones.getPuerta(),ubicaciones.getPasillo(),ubicaciones.getFilaCalle(),ubicaciones.getPosicion(),ubicaciones.getNivel(),ubicaciones.getTipoTarima(),ubicaciones.getConsecutivo());
        if(ubicacionrec.getIdUbica()==null){
            //INSERTA NUEVO REGISTRO
            ubicacionrec = this.ubicacionesManager.insertaRegistroUbicacion(ubicaciones);
        }else{
            //ACTUALIZA REGISTRO
            upbandera = this.ubicacionesManager.updateUbicacion(ubicaciones);
        }

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("registro", ubicacionrec);
        return "tablaRegUbicacion";
    }


    @RequestMapping(value = "/updatePosicion", method = RequestMethod.POST)
    public String updatePosicion(@Valid Ubicaciones ubicaciones,String tipoTarima, String consecutivos,Locale locale, Model model) {
        logger.info("Inserta posicion", locale);

        Ubicaciones ubicacionrec = new Ubicaciones();
        boolean upbandera = false;
        ubicacionrec = this.ubicacionesManager.getUbicacionIdUbica(ubicaciones.getIdUbica());
        ubicacionrec.setTipoTarima(tipoTarima);
        ubicacionrec.setConsecutivo(consecutivos);
        //ACTUALIZA REGISTRO
        upbandera = this.ubicacionesManager.updateUbicacion(ubicacionrec);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("registro", ubicacionrec);
        return "updatePosicion";
    }

    @RequestMapping(value = "/alm_getUbicaciones", method = RequestMethod.GET)
    public String formUbicaciones(Locale locale, Model model) {
        logger.info("Menu Consultas/alm_getUbicaciones", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "formUbicaciones";
    }

    @RequestMapping(value = "/tablaRecUbicacion", method = RequestMethod.POST)
     public String tablaRecUbicacion(Locale locale, Model model, String camara, String consecutivo) {
        logger.info("Recupera posicion", locale);
        List<Object[]> ubicacionrec = null;
        if(camara.equals("")){
            camara = "%";
        }

        if(consecutivo.equals("")){
            consecutivo = "%";
        }

        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getUbicacion(camara,consecutivo);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        return "tablaRecUbicacion";
    }

    @RequestMapping(value = "/tablaRecUbicacionVacias", method = RequestMethod.POST)
    public String tablaRecUbicacionVacias(Locale locale, Model model, String camara) {
        logger.info("Recupera posiciones vacias", locale);
        List<Object[]> ubicacionrec = null;
        if(camara.equals("")){
            camara = "%";
        }

        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getUbicacionVacias(camara);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        return "tablaRecUbicacionVacias";
    }

    @RequestMapping(value = "/borraUbicacion", method = RequestMethod.POST)
    public String borraUbicacion(Locale locale, Model model, Integer idUbica) {
        logger.info("Borra Ubicacion", locale);

        boolean ubicacionrec = (boolean) this.ubicacionesManager.borraUbicacion(idUbica);
        return "borraUbicacion";
    }

    @RequestMapping(value = "/formaAcutalizaUbicacion", method = RequestMethod.POST)
    public String formaAcutalizaUbicacion(Locale locale, Model model, Integer idUbica) {
        logger.info("Forma Acutaliza Ubicacion", locale);

        Ubicaciones ubicacion = this.ubicacionesManager.getUbicacionIdUbica(idUbica);
        model.addAttribute("ubicacion", ubicacion);
        return "formaActualizaUbicacion";
    }

    @RequestMapping(value = "/tablaRecConsolidados", method = RequestMethod.POST)
    public String tablaRecConsolidados(Locale locale, Model model, String camara) {
        logger.info("Recupera posiciones vacias", locale);
        List<Object[]> ubicacionrec = null;
        List<Object[]> ubicacionrecVacio = null;
        if(camara.equals("")){
            camara = "%";
        }
        logger.info("camara", camara);
        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getConsolidados(camara);

        ubicacionrecVacio = (List<Object[]>) this.ubicacionesManager.getConsolidadosVacio(camara);

        //ubicacionrec.addAll(ubicacionrecVacio);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        model.addAttribute("ubicacionesvacias", ubicacionrecVacio);
        return "tablaRecConsolidados";
    }
//*********************************************************************************
/*   @RequestMapping(value = "/alm_getUbicaciones", method = RequestMethod.GET)
    public String formUbicaciones(Locale locale, Model model) {
        logger.info("Menu Consultas/alm_getUbicaciones", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "formUbicaciones";
    }

    @RequestMapping(value = "/tablaRecCatUbicacion", method = RequestMethod.POST)
    public String tablaRecCatUbicacion(Locale locale, Model model, String camara, String consecutivo) {
        logger.info("Recupera posicion", locale);
        List<Object[]> ubicacionrec = null;
        if(camara.equals("")){
            camara = "%";
        }

        if(consecutivo.equals("")){
            consecutivo = "%";
        }

        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getUbicacion(camara,consecutivo);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        return "tablaRecCatUbicacion";
    }*/
}