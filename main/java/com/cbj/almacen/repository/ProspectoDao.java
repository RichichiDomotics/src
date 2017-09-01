package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Prospecto;

import java.util.List;

/**
 * Created by RICHARD on 01/09/2014.
 */
public interface ProspectoDao {

    public boolean saveProspecto(Prospecto prospecto);

    public boolean updateProspecto(Prospecto prospecto);
    public List<Prospecto> getProspectos(String idCliente,String nombre,int idEjecutivo);
    public List<Prospecto> getProspectosJefe(String idCliente,String nombre);
    public List<Prospecto> getProspectosByActive(String activo,int idEjecutivo);
    public List<Prospecto> getProspectosByActiveJefe(String activo, int idJefe);
    public Prospecto getProspecto(int idCliente,String nombre);
    public List<Prospecto> getAll(int idEjecutivo);
    public List<Prospecto> getAllJefe(int idJefe);
    public List<Prospecto> getAllJefeSinAsignar(int idJefe);
    public List<Prospecto> getAllActive(int idEjecutivo);
    public List<Prospecto> getAllActiveJefe(int idJefe);
    public List<Prospecto> getAllFail(int idEtapa);
    public int getAllEstatus(String activo,int idEjecutivo);
    public int getAllEtapa(String activo,int idEjecutivo);
    public int getByEztatusIdJefe(String activo,int idEjecutivo);
    public List<Prospecto> getProspectoFiltro(String nombre, String estatus, int idEjecutivo);
    public List<Prospecto> getProspectoFiltroJefe(String nombre, String estatus, int idJefe);
    public List<Prospecto> getProspectoFiltroDesasignadoJefe(String nombre, int idJefe);
    public List<Object> reporteEjecutivoEstatus(int idJefe);
    public List<Object> reporteEjecutivoEtapas(int idJefe);
    public List<Object> reporteEjecutivoEstatusFE(String cveEjecutivo,int idJefe);
    public List<Object> reporteEjecutivoEtapasFE(String cveEjecutivo,int idJefe);
    public List<Object> reportePorEstatus(int idJefe);
    public List<Object> reportePorEtapas(int idJefe);

    public List<Prospecto> getByRazonSocial(String nombre,String rfc,String calle,String colonia,String ciudad,String email,String telefono,String divisionNegocio,String producto);
    public List<Object> reporteHistorial(String fechaIni, String fechaFin, String idProspecto, String claveJefe);

    public List<Prospecto> getAllGerentes();
}
