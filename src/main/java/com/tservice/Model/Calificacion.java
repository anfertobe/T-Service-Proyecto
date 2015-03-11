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
 * Calificacion generated by hbm2java
 */
@Entity
@Table(name="Calificacion"
    ,catalog="coswg2"
)
public class Calificacion  implements java.io.Serializable {


     private Integer id;
     private int rango;
     private String comentario;
     private int valor;

    public Calificacion() {
    }

	
    public Calificacion(int rango, String comentario, int valor) {
        this.rango = rango;
        this.comentario = comentario;
        this.valor = valor;
    }
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="Rango", nullable=false)
    public int getRango() {
        return this.rango;
    }
    
    public void setRango(int rango) {
        this.rango = rango;
    }

    
    @Column(name="Comentario", nullable=false, length=45)
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
    @Column(name="Valor", nullable=false)
    public int getValor() {
        return this.valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }

}


