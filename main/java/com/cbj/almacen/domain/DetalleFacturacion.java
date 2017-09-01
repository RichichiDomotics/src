package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by colvera on 11/05/2014.
 */
@Entity
@Table(name = "detalle_facturacion")
@NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getAll",
                query="SELECT c FROM DetalleFacturacion c"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getDetFacturaByIdCliente",
                query="SELECT c.idCliente, c.nofactura, c.fechafactura ,SUM(c.kilos), SUM(c.total) FROM DetalleFacturacion c where c.idCliente = :idCliente AND c.nofactura is not null group by c.nofactura ORDER BY c.fechafactura DESC"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getIdDetalleFactura",
                query="SELECT c.rd,c.fechainiper,c.fechafinper,c.camara,c.kilos,c.tiposervicio, c.congelacion, c.total,c.nofactura,c.fechafactura FROM DetalleFacturacion c where c.nofactura = :nofactura"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getVentasEjecutivo",
                query="select sum(c.subtotal) as ventas, cl.NOM_EJECUTIVO from DetalleFacturacion c, Clientes cl where c.idCliente=cl.ID_CLIENTE and (c.fechafactura >= :fechainicio and c.fechafactura <= :fechafin) and (not(c.nofactura=''))GROUP BY cl.NOM_EJECUTIVO ORDER BY cl.NOM_EJECUTIVO"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getVentasNetasEjecutivo",
                query="SELECT SUM(c.subtotal) AS saldo, c.idCliente, cl.NOM_CLIENTE,c.nofactura, cl.NOM_EJECUTIVO FROM DetalleFacturacion c, Clientes cl WHERE c.idCliente = cl.ID_CLIENTE AND (c.fechafactura >= :fechainicio AND c.fechafactura <= :fechafin) AND (c.nofactura IS NOT NULL) AND (c.subtotal IS NOT NULL) GROUP BY c.idCliente, cl.NOM_CLIENTE, c.nofactura, cl.NOM_EJECUTIVO ORDER BY cl.NOM_EJECUTIVO, c.idCliente"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getVentasTotal",
                query="select sum(c.subtotal) as ventastotal from DetalleFacturacion c where  (c.fechafactura >= :fechainicio and c.fechafactura <= :fechafin) and (not(c.nofactura=''))"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getVentasNetasEjecutivoTotal",
                query="SELECT SUM(c.subtotal) AS saldo FROM DetalleFacturacion c WHERE (c.fechafactura >= :fechainicio AND c.fechafactura <= :fechafin) AND (c.nofactura IS NOT NULL) AND (c.subtotal IS NOT NULL)"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getVentasEjecutivoId",
                query="select sum(c.subtotal) as ventas, cl.NOM_EJECUTIVO from DetalleFacturacion c, Clientes cl where c.idCliente=cl.ID_CLIENTE and (c.fechafactura >= :fechainicio and c.fechafactura <= :fechafin) and (not(c.nofactura='')) and (cl.ID_EJECUTIVO= :idEjecutivo) GROUP BY cl.NOM_EJECUTIVO ORDER BY cl.NOM_EJECUTIVO"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getVentasNetasEjecutivoId",
                query="SELECT SUM(c.subtotal) AS saldo, c.idCliente, cl.NOM_CLIENTE,c.nofactura, cl.NOM_EJECUTIVO FROM DetalleFacturacion c, Clientes cl WHERE c.idCliente = cl.ID_CLIENTE AND (c.fechafactura >= :fechainicio AND c.fechafactura <= :fechafin) AND (c.nofactura IS NOT NULL) AND (c.subtotal IS NOT NULL) AND (cl.ID_EJECUTIVO= :idEjecutivo)GROUP BY c.idCliente, cl.NOM_CLIENTE, c.nofactura, cl.NOM_EJECUTIVO ORDER BY cl.NOM_EJECUTIVO, c.idCliente"),
        @org.hibernate.annotations.NamedQuery(name="DetalleFacturacion.getVentasNetasEjecutivoTotalId",
                query="SELECT SUM(c.subtotal) AS saldo FROM DetalleFacturacion c ,Clientes cl WHERE c.idCliente = cl.ID_CLIENTE AND (c.fechafactura >= :fechainicio AND c.fechafactura <= :fechafin) AND (c.nofactura IS NOT NULL) AND (c.subtotal IS NOT NULL) and (cl.ID_EJECUTIVO= :idEjecutivo)")

})
public class DetalleFacturacion implements Serializable {
    //private static final long serialVersionUID = 1L;

    //@Id
    //@Column(name = "idServicio")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private Integer idServicio;
    private Integer IDCLIENTE;
    private String RD;
    private String FECHAINIPER;
    private String FECHAFINPER;
    private Integer CAMARA;
    private double KILOS;
    private String TIPOSERVICIO;
    private double CONGELACION;
    private double SUBTOTAL;
    private double TOTAL;
    private String NOFACTURA;
    private String FECHAFACTURA;


    
    @Override
	public String toString() {
		return "DetalleFacturacion [IDCLIENTE=" + IDCLIENTE
				+ ", RD=" + RD + ", FECHAINIPER="
				+ FECHAINIPER + ", FECHAFINPER="
                + FECHAFINPER + ", CAMARA="
                + CAMARA + ", KILOS="
                + KILOS + ", TIPOSERVICIO="
                + TIPOSERVICIO + ", CONGELACION="
                + CONGELACION + ", SUBTOTAL="
                + SUBTOTAL + ", TOTAL="
                + TOTAL + ", NOFACTURA="
                + NOFACTURA + ", FECHAFACTURA="
				+ FECHAFACTURA+ "]";
	}

    public Integer getIdCliente() {
        return IDCLIENTE;
    }

    public void setIdCliente(Integer IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public String getRd() {
        return RD;
    }

    public void setRd(String RD) {
        this.RD = RD;
    }

    public String getFechainiper() {
        return FECHAINIPER;
    }

    public void setFechainiper(String FECHAINIPER) {
        this.FECHAINIPER = FECHAINIPER;
    }

    public String getFechafinper() {
        return FECHAFINPER;
    }

    public void setFechafinper(String FECHAFINPER) {
        this.FECHAFINPER = FECHAFINPER;
    }

    public Integer getCamara() {
        return CAMARA;
    }

    public void setCamara(Integer CAMARA) {
        this.CAMARA = CAMARA;
    }

    public double getKilos() {
        return KILOS;
    }

    public void setKilos(double KILOS) {
        this.KILOS = KILOS;
    }

    public String getTiposervicio() {
        return TIPOSERVICIO;
    }

    public void setTiposervicio(String TIPOSERVICIO) {
        this.TIPOSERVICIO = TIPOSERVICIO;
    }

    public double getCongelacion() {
        return CONGELACION;
    }

    public void setCongelacion(double CONGELACION) {
        this.CONGELACION = CONGELACION;
    }

    public double getSubtotal() {
        return SUBTOTAL;
    }

    public void setSubtotal(double SUBTOTAL) {
        this.SUBTOTAL = SUBTOTAL;
    }

    public double getTotal() {
        return TOTAL;
    }

    public void setTotal(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getNofactura() {
        return NOFACTURA;
    }

    public void setNofactura(String NOFACTURA) {
        this.NOFACTURA = NOFACTURA;
    }

    public String getFechafactura() {
        return FECHAFACTURA;
    }

    public void setFechafactura(String FECHAFACTURA) {
        this.FECHAFACTURA = FECHAFACTURA;
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
