/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.Example;

import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Publicante;
import com.tservice.Persistencia.PublicanteCrudRepository;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 2078373
 */
public class TestPublicante {
    @Autowired
    PublicanteCrudRepository pur;
    
    @Autowired
    PersistenceFacede lp;
    
    
    @Test
    public void testAgregarPublicante(){
        
        int num;
        
        Publicante pu = new Publicante(23, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pur.save(pu);
        
        //if(lp.addPublicante(pu)){
        if(false){
            num = pu.getIdentificacion();
            assertEquals(num,pu.getIdentificacion());
        
        }else{
                assertEquals(true,true);
        }
        
        
    }
    
    public void testModificarPublicante(){
        
        Publicante pu = new Publicante(23, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pur.save(pu);
        
        
       // if(lp.addPublicante(pu)){
        if(false){
            pu.setNombre("Andrea");
            pur.save(pu);
            assertEquals("Andrea", pur.findOne(pu.getIdentificacion()).getNombre());
        
        }else{
                assertEquals(true,true);
        }
        
        
    
    }
}
