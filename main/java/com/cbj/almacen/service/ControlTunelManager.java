package com.cbj.almacen.service;

import com.cbj.almacen.domain.ControlTunel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RICHARD on 22/07/2014.
 */
public interface ControlTunelManager extends Serializable {

    public boolean setControlTunel(ControlTunel controlTunel);
    public ControlTunel getControlTunelById(Integer id);
    public boolean updateControlTunel(ControlTunel controlTunel);
    public List<ControlTunel> getControlTunelAll();
}
