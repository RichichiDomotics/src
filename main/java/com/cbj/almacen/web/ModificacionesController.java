package com.cbj.almacen.web;

/**
 * Created by Richard on 21/07/2015.
 */

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.Modificaciones;
import com.cbj.almacen.service.CatalogoManager;
import com.cbj.almacen.service.ModificacionesManager;
import com.cbj.almacen.service.impl.TipoCatalogo;
import com.thoughtworks.xstream.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
@Controller
public class ModificacionesController {
    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);
    @Autowired
    private ModificacionesManager modificacionesManager;

    @Autowired
    private CatalogoManager catalogoManager;

    @RequestMapping(value = "/solicitudModificacion", method = RequestMethod.GET)
    public String solicitudModificacion(Locale locale,Model model){
        logger.info("Formulario para capturar solicitudes de modificaciones");
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        final String formattedDate = formatter.format(date);
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        int folio= this.modificacionesManager.getFolio();
        if(folio==0){
            model.addAttribute("folio",1);
        }else{
            model.addAttribute("folio",folio+1);
        }
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("now", formattedDate);
        model.addAttribute("time", hora);
        return "formModificacion";
    }

    @RequestMapping(value = "/insertarModificacion", method = RequestMethod.POST)
    public String insertarModificacion(Locale locale, Model model,@RequestParam(value = "folio")int folio,
                                       @RequestParam(value = "area")String area,@RequestParam(value = "fecha")String fecha,
                                       @RequestParam(value = "hora")String hora,@RequestParam(value = "idCliente")Integer idCliente,
                                       @RequestParam(value = "cambio")String cambio,@RequestParam(value = "causa")String causa,
                                       @RequestParam(value = "consecutivo")String consecutivo,@RequestParam(value = "salida")String salida,
                                       @RequestParam(value = "nombre")String nombre){
        logger.info("Se inserta la modificacion");
        Modificaciones modificaciones = new Modificaciones();
        Date date = null;
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date=formatFecha.parse(fecha);
        } catch (ParseException e) {
            logger.error("error al convertir la fecha"+e);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String newFecha=formatter.format(date);
        modificaciones.setFolio(folio);
        modificaciones.setNombre_solicitante(nombre);
        modificaciones.setArea_solicitante(area);
        modificaciones.setFecha_captura(newFecha);
        modificaciones.setHora_captura(hora);
        modificaciones.setIdCliente(idCliente);
        modificaciones.setCambio_solicitado(cambio);
        modificaciones.setCausa(causa);
        modificaciones.setRd_afectado(consecutivo);
        modificaciones.setSalida_afectada(salida);
        modificaciones.setEstatus(0);
        boolean respuesta = this.modificacionesManager.insertModificacion(modificaciones);
        return "formModificacion";
    }

    @RequestMapping(value = "/autorizarModificaciones", method = RequestMethod.GET)
    public String autorizarModificaciones(Locale locale, Model model){
        logger.info("Empieza el listado de modificaciones");
        List<Modificaciones> modificaciones = this.modificacionesManager.getAll();
        model.addAttribute("modificaciones",modificaciones);
        return "tablaModificaciones";
    }
}
