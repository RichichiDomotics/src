package com.cbj.almacen.web;

import com.cbj.almacen.repository.DetalleFacturacionDao;
import com.cbj.almacen.service.DetalleFacturacionManager;
import com.cbj.almacen.service.NotasManager;
import com.cbj.almacen.service.UsuarioManager;
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

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by Richard on 07/07/2015.
 */

@Controller
public class ReporteVentasController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    @Autowired
    private DetalleFacturacionManager detalleFacturacionManager;

    @Autowired
    private NotasManager notasManager;

    @Autowired
    private UsuarioManager usuarioManager;

    @RequestMapping(value = "/ae_reporteVentasEjecutivo", method = RequestMethod.GET)
    public String aes_formReporteEntrada(Model model) {
        logger.info("consultar ventas por ejecutivo");
        return "formGetVentas";
    }
    @RequestMapping(value = "/ae_reporteVentasEjecutivoId", method = RequestMethod.GET)
    public String aes_formReporteEntradaId(Model model) {
        logger.info("consultar ventas por ejecutivo");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        model.addAttribute("idEjecutivo", claveUsuario);
        return "formGetVentasIdEjecutivo";
    }

    @RequestMapping(value = "/getVentas", method = RequestMethod.POST)
    public String getVentas(Locale locale, Model model, String fecini, String fecfin) {
        logger.info("Resultado Ventas"+fecini+" "+ locale);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String dateIniString = fecini;
        String dateFinString = fecfin;

        String fechaInicio = "";
        String fechaFinal = "";
        String fechaNva = "";
        try {
            if(!dateIniString.equals("") && !dateFinString.equals("")){
                Date dateIni = formatter.parse(dateIniString);
                Date dateFin = formatter.parse(dateFinString);

                final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
                fechaInicio = formatFecha.format(dateIni);
                fechaFinal = formatFecha.format(dateFin);
                //fechaNva = formatter.format(dateIn);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Object> ventas = this.detalleFacturacionManager.getVentasEjecutivo(fechaInicio,fechaFinal);
        List<Object> ventasNetas = this.detalleFacturacionManager.getVentasNetasEjecutivo(fechaInicio, fechaFinal);
        List<Object> notasCredito= this.notasManager.getNotasCredito(fechaInicio, fechaFinal);
        List<Object> notasDetalle= this.notasManager.getDetalleNotasCredito(fechaInicio, fechaFinal);
        double total= this.detalleFacturacionManager.getVentasTotal(fechaInicio,fechaFinal);
        double totalnotas= this.notasManager.getNotasCreditoTotal(fechaInicio,fechaFinal);
        double totalnetas = this.detalleFacturacionManager.getVentasNetasEjecutivoTotal(fechaInicio,fechaFinal);
        double totalventas = totalnetas - totalnotas;
        model.addAttribute("ventas",ventas);
        model.addAttribute("ventasNetas",ventasNetas);
        model.addAttribute("notas",notasCredito);
        model.addAttribute("notasDetalle",notasDetalle);
        model.addAttribute("total",total);
        model.addAttribute("totalnotas",totalnotas);
        model.addAttribute("totalnetas",totalnetas);
        model.addAttribute("totalventas",totalventas);
        return "getVentas";
    }

    @RequestMapping(value = "/getVentasIdEjecutivo", method = RequestMethod.POST)
    public String getVentasIdEjcutivo(Locale locale, Model model, String fecini, String fecfin, String idEjecutivo) {
        logger.info("Resultado Ventas"+fecini+" "+ locale);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String dateIniString = fecini;
        String dateFinString = fecfin;

        String fechaInicio = "";
        String fechaFinal = "";
        String fechaNva = "";
        try {
            if(!dateIniString.equals("") && !dateFinString.equals("")){
                Date dateIni = formatter.parse(dateIniString);
                Date dateFin = formatter.parse(dateFinString);

                final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
                fechaInicio = formatFecha.format(dateIni);
                fechaFinal = formatFecha.format(dateFin);
                //fechaNva = formatter.format(dateIn);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Object> ventas = this.detalleFacturacionManager.getVentasEjecutivoId(fechaInicio,fechaFinal,idEjecutivo);
        List<Object> ventasNetas = this.detalleFacturacionManager.getVentasNetasEjecutivoId(fechaInicio, fechaFinal, idEjecutivo);
        double totalnetas = this.detalleFacturacionManager.getVentasNetasEjecutivoTotalID(fechaInicio, fechaFinal, idEjecutivo);
        List<Object> notasCredito= this.notasManager.getNotasCreditoId(fechaInicio, fechaFinal, idEjecutivo);
        List<Object> notasDetalle= this.notasManager.getDetalleNotasCreditoId(fechaInicio, fechaFinal, idEjecutivo);
        double totalnotas= this.notasManager.getNotasCreditoTotalId(fechaInicio, fechaFinal, idEjecutivo);
        double totalventas = totalnetas - totalnotas;
        model.addAttribute("ventasNetas",ventasNetas);
        model.addAttribute("totalnetas",totalnetas);
        model.addAttribute("ventas",ventas);
        model.addAttribute("notas",notasCredito);
        model.addAttribute("notasDetalle",notasDetalle);
        model.addAttribute("totalnotas",totalnotas);
        model.addAttribute("totalventas",totalventas);
        return "getVentasIdEjecutivo";
    }
}
