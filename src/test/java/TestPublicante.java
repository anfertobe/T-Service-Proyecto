/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Publicante;
import com.tservice.Persistencia.PublicanteCrudRepository;
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
public class TestPublicante {
    @Autowired
    PublicanteCrudRepository pur;
    
    @Autowired
    PersistenceFacede lp;
    
    
    @Test
    public void testAgregarPublicante(){
        
        int num;
        
        Publicante pu = new Publicante(23, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("a");
        
        pur.save(pu);
    
        
        assertTrue(lp.addPublicante(pu).trim().equals("OK"));
    }
  
}
