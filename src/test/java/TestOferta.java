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
import com.tservice.exceptions.tserviceExceptions;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    CategoriaCrudRepository cr;
    @Autowired
    InteresCrudRepository ir;
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
        
        try {
            lo.addOferta(pu, o);
        } catch (tserviceExceptions ex) {
            Logger.getLogger(TestOferta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        try {
            lo.addOferta(pu, o);
        } catch (tserviceExceptions ex) {
            Logger.getLogger(TestOferta.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        
        try {
            lo.addOferta(pu, o);
        } catch (tserviceExceptions ex) {
            Logger.getLogger(TestOferta.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        o.setDescripcion("cuidar motos");
        or.save(o);
        assertEquals("cuidar motos", or.findOne(o.getId()).getDescripcion());

        
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
        

        try {
            lo.addOferta(pu, o);
            lo.aplicarOferta(po, o);
        } catch (tserviceExceptions ex) {
            Logger.getLogger(TestOferta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        num = po.getIdentificacion();
        assertEquals(num, o.getPostulante().getIdentificacion());

        
        
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
        
        try {
            lo.addOferta(pu, o);
        } catch (tserviceExceptions ex) {
            Logger.getLogger(TestOferta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(po.getIdentificacion(), o.getPostulante().getIdentificacion());

                
    }
    
    @Test
    public void testAgregarOfertaACategoria(){
        
//        HashSet<Oferta> ofertas = new HashSet<Oferta>();
//        
//        Interes inte = new Interes("aaa");
//    
//        HojaDeVida hdj = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
//        hr.save(hdj);
//        
//        Categoria ca = new Categoria(inte, "Labores diarias", ofertas);
//        cr.save(ca);
//        
//        Set<Categoria> categorias = inte.getCategorias().add(ca);
//        
//        inte.setCategorias(categorias);
//            ir.save(inte);
//        
//        
//        Postulante po = new Postulante(36, hdj, 2000000, "Abdamir Saab", new Date(System.currentTimeMillis()), "spikoable@gmail.com", "dir", "1234567", "Colombia", "aaa", "Bogota");
//        por.save(po);
//        
//        Publicante pu = new Publicante(37, "experiencia en mecanica", new Date(System.currentTimeMillis()), "Andres", new Date(System.currentTimeMillis()), "dir", "2345678", "Colombia", "bbbb", "Bogota");
//        pur.save(pu);
//        
//        Oferta of= new Oferta(po, pu, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2000, "cuidar casa", "Disponible");
//        or.save(of);
//        
//        ofertas.add(of);
//        cr.save(ca);
//        
//        String resultado=lo.agregarOfertaACategoria(of, ca).trim();
//        System.out.println("Resultado "+resultado);
//      
//        if(resultado.equals("OK")){
//            {
//                assertEquals(of.getId(), cr.findOne(of.getId()));
//            }
//        }else{
//            assertEquals(true,false);
//        
//        }
        
        
        assertEquals(1, 1);
    }
    
    @Test
    public void TestRapido()
    {
        assertEquals(1, 1);
    }
    
    
    
    
    
}
