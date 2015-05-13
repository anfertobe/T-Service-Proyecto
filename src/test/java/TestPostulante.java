/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.tservice.Logica.PersistenceFacede;
import com.tservice.Logica.factura.FacturaFisica;
import com.tservice.Persistencia.*;
import com.tservice.Model.*;
import com.tservice.exceptions.tserviceExceptions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
         
       Postulante po = new Postulante(123456789, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota","12345");
       po.setCorreo("a");
       
          try {
              lpo.addPostulante(po);
          } catch (tserviceExceptions ex) {
              Logger.getLogger(TestPostulante.class.getName()).log(Level.SEVERE, null, ex);
          }
       
    }
    
    @Test
    public void testModificarPostulante(){
       
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
       hr.save(hdj);
       
        
        Postulante po = new Postulante(12345678, hdj, 2000000, "Luis Gomez", new Date(System.currentTimeMillis()), "lagcoronell@gmail.com", "dir", "1234467", "Colombia", "aaa", "Bogota","12345");
        po.setCorreo("a");
        
          try {
              lpo.addPostulante(po);
          } catch (tserviceExceptions ex) {
              Logger.getLogger(TestPostulante.class.getName()).log(Level.SEVERE, null, ex);
          }

        po.setNombre("Luisa Gomez");
        por.save(po);
        assertEquals("Luisa Gomez", por.findOne(po.getIdentificacion()).getNombre());

            
    }
    
    @Test
    public void validacionPasadoJudicialSinantecedentes() throws tserviceExceptions
    {
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
         
        Postulante po = new Postulante(12345678, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota","12345");
        po.setCorreo("a");
        
        lpo.addPostulante(po);
        
        assertTrue(por.exists(po.getIdentificacion()));

    }
    
    @Test
    public void validacionPasadoJudicialConAntecedentes()
    {
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
         
        Postulante po = new Postulante(1074417758, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota","12345");
        po.setCorreo("a");
        
          try {
              lpo.addPostulante(po);
          } catch (tserviceExceptions ex) {
              assertTrue(true);
              return;
          }
          fail();
    }
    
    @Test
    public void TestRapido() throws IOException, FileNotFoundException, BiffException, WriteException, Exception
    {
        FacturaFisica facfactura = new FacturaFisica();
        facfactura.factura();
    }
    
}
