package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Clientes;

import java.util.List;

/**
 * Created by RICHARD on 10/06/2014.
 */
public interface ClienteDao {

    public Clientes findCliente(int id);

    public Clientes insertCliente(Clientes cliente);
    public boolean updateCliente(Clientes cliente);
    public List<Clientes> getClientes(String idCliente,String nombre);
    public Clientes getClienteByIdNom(String idCliente,String nombre);
    public List<Clientes> getClientesAll();
    public List<Clientes> getAllIdEjecutivo(String idEjecutivo);
}
