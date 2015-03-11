package com.tservice.Model;
// Generated 9/03/2015 11:00:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * HojaDeVida generated by hbm2java
 */
@Entity
@Table(name="HojaDeVida"
    ,catalog="coswg2"
)
public class HojaDeVida  implements java.io.Serializable {


     private Integer id;
     private String hojaDeVida;
     private String fechaActualizacion;
     private String foto;

    public HojaDeVida() {
    }

	
    public HojaDeVida(String hojaDeVida, String fechaActualizacion, String foto) {
        this.hojaDeVida = hojaDeVida;
        this.fechaActualizacion = fechaActualizacion;
        this.foto = foto;
    }

     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="Hoja_de_Vida", nullable=false, length=45)
    public String getHojaDeVida() {
        return this.hojaDeVida;
    }
    
    public void setHojaDeVida(String hojaDeVida) {
        this.hojaDeVida = hojaDeVida;
    }

    
    @Column(name="Fecha_Actualizacion", nullable=false, length=45)
    public String getFechaActualizacion() {
        return this.fechaActualizacion;
    }
    
    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
    @Column(name="Foto", nullable=false, length=45)
    public String getFoto() {
        return this.foto;
    }
    
    public void setFoto(String foto) {
        this.foto = foto;
    }
}


