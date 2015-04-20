/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Publicante;
import com.tservice.Persistencia.PublicanteCrudRepository;
import com.tservice.exceptions.tserviceExceptions;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TestPublicante {
    @Autowired
    PublicanteCrudRepository pur;
    
    @Autowired
    PersistenceFacede lp;
    
    
    @Test
    public void testAgregarPublicante(){
        
        Publicante pu = new Publicante(22, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("a");
        
        try {
            lp.addPublicante(pu);
        } catch (tserviceExceptions ex) {
            Logger.getLogger(TestPublicante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testModificarPublicante(){
        
        Publicante pu = new Publicante(24, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("a");
        
        try {
            lp.addPublicante(pu);
        } catch (tserviceExceptions ex) {
            Logger.getLogger(TestPublicante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pu.setNombre("Andrea");
        pur.save(pu);
        assertEquals("Andrea", pur.findOne(pu.getIdentificacion()).getNombre());

        
        
    
    }
}
