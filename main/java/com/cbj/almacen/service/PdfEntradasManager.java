package com.cbj.almacen.service;

import java.io.Serializable;

/**
 * Created by RICHARD on 22/06/2014.
 */
public interface PdfEntradasManager extends Serializable {

    public void generaPdfEntradas(int consecutivo);
}
