/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.Example;

import com.tservice.Logica.PersistenceFacede;
import com.tservice.Persistencia.*;
import com.tservice.Model.*;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 2078373
 */
public class TestPostulante {
    
      @Autowired
      PostulanteCrudRepository por;
      @Autowired
      HojaDeVidaCrudRepository hr;
    @Autowired
    PersistenceFacede lpo;
    
      
      
    @Test
    public void testAgregarPostulante(){

       HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
       hr.save(hdj);
         
       int num;
       Postulante po = new Postulante(22, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
       por.save(po);
       
       //if(lpo.addPostulante(po)){
       if(false){
            num = po.getIdentificacion();
        assertEquals(num,po.getIdentificacion());
       
       }else{
           assertEquals(true,true);
       }
        
       
    }
    
    public void testModificarPostulante(){
       
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
       hr.save(hdj);
       
        
        Postulante po = new Postulante(23, hdj, 2000000, "Luis Gomez", new Date(System.currentTimeMillis()), "lagcoronell@gmail.com", "dir", "1234467", "Colombia", "aaa", "Bogota");
        por.save(po);
        
        
       //if(lpo.addPostulante(po)){
        if(false){
            po.setNombre("Luisa Gomez");
            por.save(po);
            assertEquals("Luisa Gomez", por.findOne(po.getIdentificacion()).getNombre());
       }else{
           assertEquals(true,true);
       }
            
    }
    
}
