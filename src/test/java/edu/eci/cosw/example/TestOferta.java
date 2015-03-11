/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.Example;

import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Oferta;
import com.tservice.Model.*;
import com.tservice.Model.Publicante;
import com.tservice.Persistencia.*;
import java.util.Date;
import static org.junit.Assert.assertEquals;
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
public class TestOferta {
    @Autowired
    OfertaCrudRepository or;
    @Autowired
    PublicanteCrudRepository pur;
    @Autowired
    PostulanteCrudRepository por;
    @Autowired
    HojaDeVidaCrudRepository hr;
    @Autowired
    PersistenceFacede lo;
    
    
    
    
    
   @Test
    public void testAgregarOferta(){
        
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        Postulante po = new Postulante(22, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(23, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2000, "cuidar perro", "Disponible");
        por.save(po);
        pur.save(pu);
        or.save(o);
        
        if(lo.addOferta(pu, o)){
            assertEquals(po.getIdentificacion(),o.getPostulante().getIdentificacion());
        }else{
            assertEquals(false,true);
        }
        
    }
    
    @Test
    public void testModificarOferta(){
        
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        
        Postulante po = new Postulante(24, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(25, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 40000, "cuidar carro", "Disponible");
        por.save(po);
        pur.save(pu);
        or.save(o);
     
        if(lo.addOferta(pu, o)){
            o.setDescripcion("cuidar motos");
            or.save(o);
            assertEquals("cuidar motos", or.findOne(o.getId()).getDescripcion());
        }else{
                assertEquals(false,true);
        }
        
        
    }
    
    @Test 
    public void testAplicarAUnaOferta(){
        
        int num;
        
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        
        Postulante po = new Postulante(27, hdj, 2000000, "Abda", new Date(System.currentTimeMillis()), "spikoablssse@gmail.com", "dir", "1235467", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(26, "experiencia en cuidar perros", new Date(System.currentTimeMillis()), "Luis", new Date(System.currentTimeMillis()), "dir", "2345778", "Colombia", "bbbb", "Bogota");      
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 40000, "cuidar carro", "Disponible");
        por.save(po);
        pur.save(pu);
        or.save(o);
        
        if(lo.addOferta(pu, o)){
            if(lo.aplicarOferta(po, o)){
                num = po.getIdentificacion();
                assertEquals(num, o.getPostulante().getIdentificacion());
            }else{
                assertEquals(true,false);
            }
        }else{
            assertEquals(true,false);
        }
        
        
    }
    
    @Test
    public void testSeleccionarEmpleadoAUnaOferta(){
        
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
               
        Postulante po = new Postulante(27, hdj, 2000000, "Abda", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1235467", "Colombia", "aaa", "Bogota");
        Postulante po2 = new Postulante(28, hdj, 2000000, "Alejandro", new Date(System.currentTimeMillis()), "spikoablssse@gmail.com", "dir", "1235667", "Colombia", "bbb", "Bogota");
        Publicante pu = new Publicante(31, "experiencia en cuidar perros", new Date(System.currentTimeMillis()), "Luis", new Date(System.currentTimeMillis()), "dir", "2345778", "Colombia", "bbbb", "Bogota");            
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 40000, "cuidar carro", "Disponible");
        por.save(po);
        pur.save(pu);
        por.save(po2);
        or.save(o);
        if(lo.addOferta(pu, o)){
            if(lo.addEmpleadoOferta(po, o)){
                assertEquals(po.getIdentificacion(), o.getPostulante().getIdentificacion());
            }
        }else{
            assertEquals(true,false);
        
        }
                
    }
    
    
    
    
    
}
