package com.cbj.almacen.domain;

import com.cbj.almacen.repository.ClienteDao;
import com.cbj.almacen.repository.impl.ClienteDaoImpl;
import com.cbj.almacen.service.ClienteManager;
import com.cbj.almacen.service.impl.ClienteManagerImpl;
import com.cbj.almacen.web.ClientesController;
import org.hibernate.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by RICHARD on 17/05/2014.
 */
@Entity
@Table(name = "inventarios")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Inventario.getByClienteConsecutivo",
                query="SELECT c FROM Inventario c where c.idCliente = :idCliente and c.consecutivo = :consecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Inventario.getByConsecutivo",
        query="SELECT c FROM Inventario c where c.consecutivo = :consecutivo"),
        @org.hibernate.annotations.NamedQuery(name = "Inventario.getArrastreSaldosInventarioAgrupado",
		query = "SELECT c.renglon, SUM(c.cantidadInventario), SUM(c.cantidadInventario*c.pesou)  FROM Inventario c where c.consecutivo = :consecutivo group by c.renglon order by c.renglon"),
        @org.hibernate.annotations.NamedQuery(name = "Inventario.getSaldoXCamara",
                query = "SELECT c.camara, sum(c.cantidadInventario),sum(c.cantidadInventario*pesou), sum((c.cantidadInventario*c.pesou)/1000) from Inventario c WHERE c.cantidadInventario >0 AND c.camara IN ('0','1','2','3','4','5','6','7','8','9','10','11') GROUP BY c.camara"),
        @org.hibernate.annotations.NamedQuery(name = "Inventario.getSaldoXTunel",
                query = "SELECT c.camara, sum(c.cantidadInventario),sum(c.cantidadInventario*c.pesou), sum((c.cantidadInventario*c.pesou)/1000) from Inventario c WHERE c.cantidadInventario >0 AND c.camara IN ('12') GROUP BY c.camara"),
        @org.hibernate.annotations.NamedQuery(name = "Inventario.getSaldoXCliente",
                query = "SELECT i.idCliente, c.NOM_CLIENTE, sum(i.cantidadInventario),sum((i.cantidadInventario*i.pesou)/1000) from Inventario i, Clientes c WHERE i.cantidadInventario >0 and i.idCliente = c.ID_CLIENTE GROUP BY i.idCliente ORDER BY i.idCliente + 0"),
        @org.hibernate.annotations.NamedQuery(name = "Inventario.getTodasCamaras",
        query = "SELECT i.camara,c.ID_CLIENTE, c.NOM_CLIENTE,i.consecutivo,i.renglon,i.fechaCaptura, i.cantidadInventario,i.pesou,i.cantidadInventario*i.pesou,i.descripcion,i.embalaje,i.marca from Inventario i, Clientes c WHERE i.cantidadInventario >0 and i.idCliente = c.ID_CLIENTE GROUP BY i.camara, i.consecutivo")
})

public class Inventario implements Serializable {

    private static final Logger logger = LoggerFactory
            .getLogger(Inventario.class);

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idInventario")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idInventario;

    private Integer consecutivo;

    private String idCliente;
    private String fechaSalida;
    private Double renglon;
    private String claveProducto;
    private Double pesou;
    private Double cantidadInventario;
    private String cantidadSalida;
    private String hora;
    private String embalaje;
    private String marca;
    private String lote;
    private Double bultos;
    private Double valorTotal;
    private String tunel;
    private String camara;
    private String fechaCaptura;
    private String diasTunel;
    private String horaCaptura;
    private String descripcion;
    private String servicio;
    private String aplicado;
    private String maniobras;
    private String caducidad;
    private String pesoBruto;
    private String lote2;
    private String lote3;
    private String lote4;
    private String lote5;
    private String lote6;
    private String lote7;
    private String lote8;
    private String tarimas;
    private String tAnden;
    private String calle;
    private String no;
    private String altura;
    private Double disp;

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getRenglon() {
        return renglon;
    }

    public void setRenglon(Double renglon) {
        this.renglon = renglon;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    public Double getPesou() {
        return pesou;
    }

    public void setPesou(Double pesou) {
        this.pesou = pesou;
    }

    public Double getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(Double cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public String getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(String cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEmbalaje() {
        return embalaje;
    }

    public void setEmbalaje(String embalaje) {
        this.embalaje = embalaje;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getBultos() {
        return bultos;
    }

    public void setBultos(Double bultos) {
        this.bultos = bultos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTunel() {
        return tunel;
    }

    public void setTunel(String tunel) {
        this.tunel = tunel;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public String getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(String fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public String getDiasTunel() {
        return diasTunel;
    }

    public void setDiasTunel(String diasTunel) {
        this.diasTunel = diasTunel;
    }

    public String getHoraCaptura() {
        return horaCaptura;
    }

    public void setHoraCaptura(String horaCaptura) {
        this.horaCaptura = horaCaptura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getAplicado() {
        return aplicado;
    }

    public void setAplicado(String aplicado) {
        this.aplicado = aplicado;
    }

    public String getManiobras() {
        return maniobras;
    }

    public void setManiobras(String maniobras) {
        this.maniobras = maniobras;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(String pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getLote2() {
        return lote2;
    }

    public void setLote2(String lote2) {
        this.lote2 = lote2;
    }

    public String getLote3() {
        return lote3;
    }

    public void setLote3(String lote3) {
        this.lote3 = lote3;
    }

    public String getLote4() {
        return lote4;
    }

    public void setLote4(String lote4) {
        this.lote4 = lote4;
    }

    public String getLote5() {
        return lote5;
    }

    public void setLote5(String lote5) {
        this.lote5 = lote5;
    }

    public String getLote6() {
        return lote6;
    }

    public void setLote6(String lote6) {
        this.lote6 = lote6;
    }

    public String getLote7() {
        return lote7;
    }

    public void setLote7(String lote7) {
        this.lote7 = lote7;
    }

    public String getLote8() {
        return lote8;
    }

    public void setLote8(String lote8) {
        this.lote8 = lote8;
    }

    public String getTarimas() {
        return tarimas;
    }

    public void setTarimas(String tarimas) {
        this.tarimas = tarimas;
    }

    public String gettAnden() {
        return tAnden;
    }

    public void settAnden(String tAnden) {
        this.tAnden = tAnden;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Double getDisp() {
        return disp;
    }

    public void setDisp(Double disp) {
        this.disp = disp;
    }

    @Override
    public String toString() {
        return "Inventario [consecutivo=" + getConsecutivo()+"]";
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

}
