
package com.tservice.restcontrollers;

import com.tservice.Logica.PersistenceFacede;
import com.tservice.Logica.pagos.InformacionPago;
import com.tservice.Model.*;
import com.tservice.Persistencia.*;
import com.tservice.exceptions.tserviceExceptions;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andres Torres y Luis Gomez
 */
@RestController
@RequestMapping("/tservice")
public class RestControllerTservice {
    
    @Autowired
    PersistenceFacede persistenci;
    @Autowired 
    PublicanteCrudRepository publicru;
    @Autowired
    PostulanteCrudRepository poscru;
    @Autowired
    OfertaCrudRepository oferCru;
    @Autowired
    HojaDeVidaCrudRepository hojadevidacrud;
    @Autowired
    LicenciasCrudRepository licenciaCrud;
    @Autowired
    CalificacionCrudRepository calificacionCrud;
    
    
    
    @RequestMapping(value="/Calificacion",method = RequestMethod.GET)        
    public List<Calificacion> consultarCalificacion()  throws ResourceNotFoundException { 
          return persistenci.traerCalificaciones();
    }
    
    @RequestMapping(value="/Postulantes",method = RequestMethod.GET)        
    public List<Postulante> consultarPostulantes()  throws ResourceNotFoundException { 
          return persistenci.traerPostulantes();
    }
    
    @RequestMapping(value="/Categorias",method = RequestMethod.GET)        
    public List<Categoria> consultarCategorias() { 
          return persistenci.traerCategorias();
    }
    
    @RequestMapping(value="/Interes",method = RequestMethod.GET)        
    public List<Interes> consultarIntereses()  throws ResourceNotFoundException { 
          return persistenci.traerIntereses();
    }
    
    @RequestMapping(value="/Interes",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarInteres(@RequestBody Postulante postulante){ 
        
        try {
            //System.out.println("Este es el postulante: "+interes.getIdentificacion().getNombre());
            
            persistenci.uddPostulante(postulante);
        } catch (tserviceExceptions ex) {
                        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="/Calificacion/{IdOferta}",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarCalificacion(@RequestBody Calificacion calificacion ,@PathVariable("IdOferta") int idOferta){ 
        
        Oferta oferta = oferCru.findOne(idOferta);
       
       if(oferta == null)
           return new ResponseEntity<>("La oferta No Existe", HttpStatus.INTERNAL_SERVER_ERROR);
         try {
            persistenci.addCalificacion(oferta,calificacion);
        } catch (tserviceExceptions ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    
   
    @RequestMapping(value="Categorias/{idCategoria}",method = RequestMethod.GET)
    public Categoria consultarCategoria(@PathVariable("idCategoria") int idCategoria) throws ResourceNotFoundException {
        return persistenci.consultarCategoria(idCategoria);
    }
    
    @RequestMapping(value="Postulantes/{idPostulante}",method = RequestMethod.GET)
    public Postulante consultarPostulante(@PathVariable("idPostulante") int idPosultante) throws ResourceNotFoundException {
        return persistenci.consultarPostulante(idPosultante);
    }
    
    @RequestMapping(value="/Postulantes",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarPostulante(@RequestBody Postulante postulante){ 
        
        try {
            hojadevidacrud.save(postulante.getHojaDeVida());
            persistenci.addPostulante(postulante);
        } catch (tserviceExceptions ex) {
                        hojadevidacrud.delete(postulante.getHojaDeVida());
                       return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
       
    @RequestMapping(value="/Categorias",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarCategoria(@RequestBody Categoria categoria){ 
        
        try {
            persistenci.addCategoria(categoria);
        } catch (tserviceExceptions ex) {
                     return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
        @RequestMapping(value="/Publicantes",method = RequestMethod.GET)        
    public List<Publicante> consultarPublicantes()  throws ResourceNotFoundException { 
          return persistenci.traerPublicantes();
    }    
    
    @RequestMapping(value="Publicantes/{idPublicante}",method = RequestMethod.GET)
    public Publicante consultarPublicante(@PathVariable("idPublicante") int idPublicante) throws ResourceNotFoundException {
        return persistenci.consultarPublicante(idPublicante);
    }
    
    @RequestMapping(value="/Publicantes",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarPublicante(@RequestBody Publicante publicante)  throws ResourceNotFoundException{ 
     
        try {
            persistenci.addPublicante(publicante);
        } catch (tserviceExceptions ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  
    
    @RequestMapping(value="/Ofertas",method = RequestMethod.GET)        
    public List<Oferta> consultarOfertas()  throws ResourceNotFoundException { 
          return persistenci.traerOfertas();
    }
    
    @RequestMapping(value="Ofertas/{idOferta}",method = RequestMethod.GET)
    public Oferta consultarOferta(@PathVariable("idOferta") int idOferta) throws ResourceNotFoundException {
        return persistenci.consultarOferta(idOferta);
    }
    
    @RequestMapping(value="/Ofertas/{id}",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarOferta(@RequestBody Oferta oferta, @PathVariable("id") int id){ 
       Publicante publi = publicru.findOne(id);
       
       if(publi == null)
           return new ResponseEntity<>("El Publicante No Existe", HttpStatus.INTERNAL_SERVER_ERROR);
       
        try {
            persistenci.addOferta(publi,oferta);
        } catch (tserviceExceptions ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="/Ofertas/aplicarOferta/{idpostulante}/{idoferta}",method = RequestMethod.PUT)        
      public ResponseEntity<?> aplicarOferta(@PathVariable("idpostulante") int idpostulante, @PathVariable("idoferta") int idoferta){ 
     
          Oferta ofer = oferCru.findOne(idoferta);
          
          if(ofer == null)
              return new ResponseEntity<>("La Oferta no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
          Postulante post = poscru.findOne(idpostulante);
          
          if(post == null)
              return new ResponseEntity<>("El postulante no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
          
        try {
            persistenci.aplicarOferta(post,ofer);
        } catch (tserviceExceptions ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
       
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
  
    @RequestMapping(value="/Ofertas/agregarEmpleadoOferta/{idpostulante}/{idoferta}",method = RequestMethod.PUT)        
      public ResponseEntity<?> agregarEmpleadoOferta(@PathVariable("idpostulante") int idpostulante, @PathVariable("idoferta") int idoferta){ 
     
        Oferta ofer = oferCru.findOne(idoferta);
          
        if(ofer == null)
            return new ResponseEntity<>("La Oferta no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
        Postulante post = poscru.findOne(idpostulante);
          
        if(post == null)
            return new ResponseEntity<>("El postulante no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
        try {
            persistenci.addEmpleadoOferta(post,ofer);
        }catch (tserviceExceptions ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>("Ok", HttpStatus.ACCEPTED);
       }
      
        @RequestMapping(value="/licencias",method = RequestMethod.GET)
        public List<Licencias> consultarLicencias() throws ResourceNotFoundException {
            return persistenci.traerLicencias();
        }
        
        @RequestMapping(value="/pagarlicencias/{idLicencia}/usuario/{usuario}",method = RequestMethod.POST)
        public ResponseEntity<?> pagarLicencia(@PathVariable("usuario") int usuario, @PathVariable("idLicencia") String idLicencia, @RequestBody InformacionPago pago) {
            Publicante publi = publicru.findOne(usuario);
            
            persistenci.login(publi);
            
            Licencias licencia = licenciaCrud.findOne(Integer.parseInt(idLicencia));
            try{
                persistenci.realizarPago(licencia, pago);
            }catch(Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            return new ResponseEntity<>("Ok", HttpStatus.ACCEPTED); 
        }
}