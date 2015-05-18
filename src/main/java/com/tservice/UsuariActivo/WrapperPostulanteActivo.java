/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.UsuariActivo;

import com.tservice.Model.Postulante;
import org.springframework.stereotype.Service;

/**
 *
 * @author andres
 */
public class WrapperPostulanteActivo implements UsuarioActivo{

    private Postulante postulante;
    
    public WrapperPostulanteActivo(Postulante postulante){
        this.postulante = postulante;
    }
    
    @Override
    public int getIdentificacion() {
        return postulante.getIdentificacion();
    }

    @Override
    public String getNombre() {
        return postulante.getNombre();
    }

    @Override
    public String getCorreo() {
        return postulante.getCorreo();
    }

    @Override
    public Object getObjectUsuario() {
        return postulante;
    }
    
}
