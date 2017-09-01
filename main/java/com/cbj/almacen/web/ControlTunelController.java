package com.cbj.almacen.web;

import com.cbj.almacen.domain.ControlTunel;
import com.cbj.almacen.domain.Mensajes;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RICHARD on 22/07/2014.
 */
@Controller
public class ControlTunelController {

    private static final Logger logger = LoggerFactory
            .getLogger(VehiculoController.class);

    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ControlTunelManager controlTunelManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private AvisosHistorialManager avisosHistorialManager;
    @Autowired
    private MensajesManager mensajesManager;

    private ArrayList<String> periodo = new ArrayList<String>();

    @RequestMapping(value = "/ae_controlTunel", method = RequestMethod.GET)
    public String controlTunel(Model model) {
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        getPeriodoValues();
        model.addAttribute("periodo", periodo);
        model.addAttribute("listaTunel", this.controlTunelManager.getControlTunelAll());
        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

//        model.addAttribute("totalAvisos", totalAvisosObj.get(0));
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "controlTunel";
        }

    @RequestMapping(value = "/insControlTunel", method = RequestMethod.POST)
    public String insControlTunel(@Valid ControlTunel controlTunel,
                                  BindingResult result) {
        logger.info("ENTRO A insControlTunel (y)");
        if (result.hasErrors()) {
            logger.error("Error al validar bean ControlTunel "+result.getAllErrors().get(0));
            return "error";
        }
        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        controlTunel.setFecha(fecha+" "+hora);
        controlTunel.setNombreCliente(clienteManager.getByIdCliente(Integer.parseInt(controlTunel.getIdCliente())).getNombreCliente());
        boolean resp = controlTunelManager.setControlTunel(controlTunel);
        if (resp) {
            logger.info("VA A ALMACENAR");
            return "redirect:exito";
        }else {
            logger.error("ERROR AL  ALMACENAR");
            return "error";}
    }


    private void getPeriodoValues() {
        /*periodo.add("01");
        periodo.add("02");
        periodo.add("03");
        periodo.add("04");
        periodo.add("05");
        periodo.add("06");
        periodo.add("07");
        periodo.add("08");
        periodo.add("09");
        periodo.add("10");
        periodo.add("11");
        periodo.add("12");
        periodo.add("13");
        periodo.add("14");
        periodo.add("15");
        periodo.add("16");
        periodo.add("17");
        periodo.add("18");
        periodo.add("19");
        periodo.add("20");
        periodo.add("21");
        periodo.add("22");
        periodo.add("23");
        periodo.add("24");*/
        periodo.add("12");
        periodo.add("24");
        periodo.add("48");
    }



}
