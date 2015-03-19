/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Logica.pasadoJudicial;

import java.util.Date;

/**
 *
 * @author LuisAndres
 */
public class Antecedente {
    private String descripcion;
    
    private Date fechaAntecedente;
    
    private int codigo;
    
    public Antecedente(String descripcion,Date fechaAntecedente,int codigo){
        this.descripcion=descripcion;
        this.fechaAntecedente=fechaAntecedente;
        this.codigo=codigo;
    
    
    }
    
    
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    
    
    public Date getFechaAntecedente(){
        return fechaAntecedente;
    }
    
    public void setFechaAntecedente(Date fechaAntecedente){
        this.fechaAntecedente=fechaAntecedente;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public void setCodigo(int codigo){
        this.codigo=codigo;
    }
    
    
}
