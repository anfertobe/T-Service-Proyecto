/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Logica;

import com.tservice.Model.Postulante;
import com.tservice.Model.HojaDeVida;
import com.tservice.Model.Interes;
import com.tservice.Model.ExperienciaLaboral;
import com.tservice.Persistencia.*;
import java.sql.Date;
import java.util.Set;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anfer_000
 */
@Service
public class PersistenceFacede {
    
    
    @Autowired
    HojaDeVidaCrudRepository hvc;
    @Autowired
    PostulanteCrudRepository postCru;
    @Autowired
    ExperienciaLaboralCrudRepository expeCrud;
    @Autowired
    InteresCrudRepository interCrud;
    public void pruebaPersistenciaEntidades()
    {
        //Creacion de Hoja De Vide
        
        HojaDeVida hv = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hvc.save(hv);
        
        Postulante post = new Postulante(734567890, hv, 23456789, "NombrePrueba", new Date(1,1,1), "CorreoPrueba", "DireccionPrueba", "123456789", "PaisPrueba", "regionPrueba", "CiudadPrueba");      
          
        ExperienciaLaboral expe = new ExperienciaLaboral("EntidaPrueba", new Date(1,1,1), "CometarioPrueba");
        
        Set<ExperienciaLaboral> experi = post.getExperienciaLaborals();
        experi.add(expe);
        post.setExperienciaLaborals(experi);
        
        //postCru.SetExpe(experi, post.getIdentificacion());
        postCru.save(post);
        expeCrud.save(expe);
        
        
        



        
        Interes interes = new Interes("ExperienciaPrueba");
        
        Set<Interes> inter = post.getIntereses();
        inter.add(interes);
        
        interCrud.save(interes);       
        
        postCru.save(post);
    }
    
}
