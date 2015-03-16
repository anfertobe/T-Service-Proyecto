package com.tservice.Model;
// Generated 9/03/2015 11:00:56 PM by Hibernate Tools 4.3.1


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Oferta generated by hbm2java
 */
@Entity
@Table(name="Oferta"
    ,catalog="coswg2"
)
public class Oferta  implements java.io.Serializable {


     private Integer id;
     private Calificacion calificacion;
     private Postulante postulante;
     private Publicante publicante;
     private Date fechaCreacion;
     private Date fechaFinalizacion;
     private double valor;
     private String descripcion;
     private String estado;
     private Set<Categoria> categorias = new HashSet(0);
     private Set<Postulante> postulantes = new HashSet(0);

    public Oferta() {
    }

	
    public Oferta(Postulante postulante, Publicante publicante, Date fechaCreacion, Date fechaFinalizacion, double valor, String descripcion, String estado) {
        this.postulante = postulante;
        this.publicante = publicante;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.valor = valor;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    public Oferta(Calificacion calificacion, Postulante postulante, Publicante publicante, Date fechaCreacion, Date fechaFinalizacion, double valor, String descripcion, String estado, Set<Categoria> categorias, Set<Postulante> postulantes) {
       this.calificacion = calificacion;
       this.postulante = postulante;
       this.publicante = publicante;
       this.fechaCreacion = fechaCreacion;
       this.fechaFinalizacion = fechaFinalizacion;
       this.valor = valor;
       this.descripcion = descripcion;
       this.estado = estado;
       this.categorias = categorias;
       this.postulantes = postulantes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Calificacion_Id")
    public Calificacion getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Trabajador_Identificacion", nullable=false)
    public Postulante getPostulante() {
        return this.postulante;
    }
    
    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Publicante_Identificacion", nullable=false)
    public Publicante getPublicante() {
        return this.publicante;
    }
    
    public void setPublicante(Publicante publicante) {
        this.publicante = publicante;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FechaCreacion", nullable=false, length=10)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FechaFinalizacion", nullable=false, length=10)
    public Date getFechaFinalizacion() {
        return this.fechaFinalizacion;
    }
    
    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    
    @Column(name="Valor", nullable=false, precision=22, scale=0)
    public double getValor() {
        return this.valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }

    
    @Column(name="Descripcion", nullable=false, length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="Estado", nullable=false, length=45)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="Oferta_has_Categoria", catalog="coswg2", joinColumns = { 
        @JoinColumn(name="Oferta_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="Categoria_id", nullable=false, updatable=false) })
    public Set<Categoria> getCategorias() {
        return this.categorias;
    }
    
    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="Oferta_has_Postulante", catalog="coswg2", joinColumns = { 
        @JoinColumn(name="Oferta_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="Identificacion", nullable=false, updatable=false) })
    public Set<Postulante> getPostulantes() {
        return this.postulantes;
    }
    
    public void setPostulantes(Set<Postulante> postulantes) {
        this.postulantes = postulantes;
    }




}


