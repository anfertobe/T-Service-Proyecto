/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.UsuariActivo;

import com.tservice.Model.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author andres
 */
public class WrapperPublicanteActivo implements UsuarioActivo{
    
    Publicante publicante;
    
    public WrapperPublicanteActivo(Publicante publicante){
     
        this.publicante = publicante;
        
    }

    @Override
    public int getIdentificacion() {
        return publicante.getIdentificacion();
    }

    @Override
    public String getNombre() {
        return publicante.getNombre();
    }

    @Override
    public String getCorreo() {
        return publicante.getCorreo();
    }

    @Override
    public Object getObjectUsuario() {
        return publicante;
    }

    
    
    
}
