package com.cbj.almacen.web;

import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.TraspasosCamara;
import com.cbj.almacen.service.InventarioManager;
import com.cbj.almacen.service.TraspasosCamaraManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by RICHARD on 09/09/2014.
 */
@Controller
public class TraspasoController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    @Autowired
    private TraspasosCamaraManager traspasosCamaraManager;
    @Autowired
    private InventarioManager inventarioManager;

    @RequestMapping(value = "/ae_traspasos", method = RequestMethod.GET)
    public String ae_traspasos(Locale locale, Model model) {
        logger.info("ae_traspasoss", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.SHORT, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "formTraspasos";
    }

    @RequestMapping(value = "/ae_inserttraspasos", method = RequestMethod.POST)
    public String ae_traspasos(@Valid TraspasosCamara traspasosCamara, BindingResult result, Locale locale, Model model) {
        logger.info("ae_inserttraspasos");
        if (result.hasErrors()) {
            model.addAttribute("message","Validar Datos de captura");
            return "formTraspasos";
        }
        List<Inventario> inventarios = inventarioManager.getInventarioByConsecutivo(Integer.parseInt(traspasosCamara.getConsecutivo()));
        if(inventarios.size()<1){
            model.addAttribute("message","RD no existente");
            return "formTraspasos";
        }

        traspasosCamaraManager.insertTraspasoCamara(traspasosCamara);

        for(Inventario inventario:inventarios){
            inventario.setCamara(traspasosCamara.getCamaraFinal());
            inventarioManager.updateInventario(inventario);
        }
        model.addAttribute("message","Registro Actualizado con éxito");
        return "formTraspasos";
    }
}
