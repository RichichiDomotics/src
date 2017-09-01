package com.cbj.almacen.repository;

import com.cbj.almacen.domain.ControlTunel;

import java.util.List;

/**
 * Created by RICHARD on 22/07/2014.
 */
public interface ControlTunelDao {

    public boolean insertControlTunel(ControlTunel controlTunel);
    public ControlTunel findControlTunelById(Integer folioAsignado);
    public boolean updateControlTunel(ControlTunel controlTunel);
    public List<ControlTunel> getAll();
}
