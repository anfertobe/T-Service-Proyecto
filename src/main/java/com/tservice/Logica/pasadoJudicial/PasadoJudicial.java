/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Logica.pasadoJudicial;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author LuisAndres
 */
public class PasadoJudicial {
 
    private String descripcion;
    
    private Date fechaAntecedente;
    
    private ArrayList<Antecedente> antecedentes;
    
    public PasadoJudicial(String descripcion,Date fechaAntecedente,ArrayList<Antecedente> antecedentes){
        this.descripcion=descripcion;
        this.fechaAntecedente=fechaAntecedente;
        this.antecedentes=antecedentes;
    }
    
    public PasadoJudicial(String descripcion,Date fechaAntecedente){
        this.descripcion=descripcion;
        this.fechaAntecedente=fechaAntecedente;
    }
    
    
    public ArrayList<Antecedente> getAntecedentes(){
        return antecedentes;
    }
    
    public void setAntecedentes(ArrayList<Antecedente> antecedentes){
        this.antecedentes=antecedentes;
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
}
