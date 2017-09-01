package com.cbj.almacen.service;

import com.cbj.almacen.domain.User;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by RICHARD on 11/05/2014.
 */
public interface SecurityUserManager extends Serializable {

    public User getLoginSession(JSONObject entradaJson);

    public User getUsuFirmado(String usuario, String password);

}
