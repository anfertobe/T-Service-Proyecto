package com.tservice.Model;
// Generated 9/03/2015 11:00:56 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Factura generated by hbm2java
 */
@Entity
@Table(name="Factura")
public class Factura  implements java.io.Serializable {


     private Integer id;
     private Licencias licencias;
     private Publicante publicante;
     private String referenciaPago;
     private Integer valor;
     private Date fecha;

    public Factura() {
    }

    public Factura(Licencias licencias, Publicante publicante, String referenciaPago, Integer valor, Date fecha) {
       this.licencias = licencias;
       this.publicante = publicante;
       this.referenciaPago = referenciaPago;
       this.valor = valor;
       this.fecha = fecha;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="Licencias_idLicencias")
    public Licencias getLicencias() {
        return this.licencias;
    }
    
    public void setLicencias(Licencias licencias) {
        this.licencias = licencias;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="Identificacion")
    public Publicante getPublicante() {
        return this.publicante;
    }
    
    public void setPublicante(Publicante publicante) {
        this.publicante = publicante;
    }

    
    @Column(name="Referencia_Pago", length=45)
    public String getReferenciaPago() {
        return this.referenciaPago;
    }
    
    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    
    @Column(name="Valor")
    public Integer getValor() {
        return this.valor;
    }
    
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="Fecha", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}


