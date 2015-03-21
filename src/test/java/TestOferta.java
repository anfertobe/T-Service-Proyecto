/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Oferta;
import com.tservice.Model.*;
import com.tservice.Model.Publicante;
import com.tservice.Persistencia.*;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
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
    FacturaCrudRepository fr;
    @Autowired
    LicenciasCrudRepository lr;
    @Autowired
    PersistenceFacede lo;
    
     
     
   @Test
    public void testAgregarOferta(){
        
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        Postulante po = new Postulante(22, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(23, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("aa");
        po.setCorreo("aa");
        
        ArrayList<Factura> facs = new ArrayList<Factura>();
        
        Licencias lic=new Licencias(1,"30 Días",30);
            
        lr.save(lic);
        
        Factura fac=new Factura(lic,pu,"ref1",1000,new Date());
                   
        facs.add(fac);
        
        pu.setFacturas(facs);
        
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2000, "cuidar perro", "Disponible");
        
        por.save(po);
        
        pur.save(pu);
        
        or.save(o);
        
        String resultado=lo.addOferta(pu, o).trim();
        
        System.out.println("Resultado "+resultado);
        
        assertTrue(resultado.equals("OK"));
    }
    
    @Test
    public void testAgregarSinLicenciaOferta(){
        
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        Postulante po = new Postulante(22, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(23, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("aa");
        po.setCorreo("aa");
        
               Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2000, "cuidar perro", "Disponible");
        
        por.save(po);
        
        pur.save(pu);
        
        or.save(o);
        
        String resultado=lo.addOferta(pu, o).trim();
        
        System.out.println("Resultado "+resultado);
        
        assertTrue(!resultado.equals("OK"));
    }
   
    
    
    @Test
    public void testModificarOferta(){
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        Postulante po = new Postulante(26, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(27, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("aa");
        po.setCorreo("aa");
        
        ArrayList<Factura> facs = new ArrayList<Factura>();
        
        Licencias lic=new Licencias(2,"30 Días",30);
            
        lr.save(lic);
        
        Factura fac=new Factura(lic,pu,"ref1",1000,new Date());
                   
        facs.add(fac);
        
        pu.setFacturas(facs);
        
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2000, "cuidar perro", "Disponible");
        
        por.save(po);
        
        pur.save(pu);
        
        or.save(o);
        
        String resultado=lo.addOferta(pu, o).trim();
        
        System.out.println("Resultado "+resultado);
      
        if(resultado.equals("OK")){
            o.setDescripcion("cuidar motos");
            or.save(o);
            assertEquals("cuidar motos", or.findOne(o.getId()).getDescripcion());
        }else{
            assertTrue(false);
        }
        
    }
    
    @Test 
    public void testAplicarAUnaOferta(){
        
        int num;
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        Postulante po = new Postulante(28, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(29, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("aa");
        po.setCorreo("aa");
        
        ArrayList<Factura> facs = new ArrayList<Factura>();
        
        Licencias lic=new Licencias(3,"30 Días",30);
            
        lr.save(lic);
        
        Factura fac=new Factura(lic,pu,"ref1",1000,new Date());
                   
        facs.add(fac);
        
        pu.setFacturas(facs);
        
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2000, "cuidar perro", "Disponible");
        
        por.save(po);
        
        pur.save(pu);
        
        or.save(o);
        
        String resultado=lo.addOferta(pu, o).trim();
        
        System.out.println("Resultado "+resultado);
      
        if(resultado.equals("OK")){
            if(lo.aplicarOferta(po, o).trim().equals("OK")){
                num = po.getIdentificacion();
                assertEquals(num, o.getPostulante().getIdentificacion());
            }else{
                assertEquals(false,true);
            }
        }else{
            assertEquals(false,true);
        }
        
        
    }
    
    @Test
    public void testSeleccionarEmpleadoAUnaOferta(){
        
        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hr.save(hdj);
        
        Postulante po = new Postulante(30, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
        Publicante pu = new Publicante(31, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");      
        pu.setCorreo("aa");
        po.setCorreo("aa");
        
        ArrayList<Factura> facs = new ArrayList<Factura>();
        
        Licencias lic=new Licencias(4,"30 Días",30);
            
        lr.save(lic);
        
        Factura fac=new Factura(lic,pu,"ref1",1000,new Date());
                   
        facs.add(fac);
        
        pu.setFacturas(facs);
        
        Oferta o= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2000, "cuidar perro", "Disponible");
        
        por.save(po);
        
        pur.save(pu);
        
        or.save(o);
        
        String resultado=lo.addOferta(pu, o).trim();
        
        System.out.println("Resultado "+resultado);
      
        if(resultado.equals("OK")){
        
            if(lo.addEmpleadoOferta(po, o)){
                assertEquals(po.getIdentificacion(), o.getPostulante().getIdentificacion());
            }
        }else{
            assertEquals(true,false);
        
        }
                
    }
    
    @Test
    public void TestRapido()
    {
        assertEquals(1, 1);
    }
    
    
    
    
    
}
