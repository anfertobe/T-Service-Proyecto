/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.tservice.Logica.PersistenceFacede;
import com.tservice.Persistencia.*;
import com.tservice.Model.*;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author 2078373
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
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
         
       Postulante po = new Postulante(22, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
       po.setCorreo("a");
       
      assertTrue(lpo.addPostulante(po).trim().equals("OK"));
       
    }
    
    @Test
    public void testModificarPostulante(){
       
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
       hr.save(hdj);
       
        
        Postulante po = new Postulante(29, hdj, 2000000, "Luis Gomez", new Date(System.currentTimeMillis()), "lagcoronell@gmail.com", "dir", "1234467", "Colombia", "aaa", "Bogota");
        po.setCorreo("a");
        
        
       if(lpo.addPostulante(po).trim().equals("OK")){
        
            po.setNombre("Luisa Gomez");
            por.save(po);
            assertEquals("Luisa Gomez", por.findOne(po.getIdentificacion()).getNombre());
       }else{
           assertEquals(true,false);
       }
            
    }
    
}
