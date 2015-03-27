
package com.tservice.restcontrollers;

import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.*;
import com.tservice.Persistencia.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//
/**
 *
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
    
    @RequestMapping(value="/Postulantes",method = RequestMethod.GET)        
    public List<Postulante> consultarPostulantes()  throws ResourceNotFoundException { 
          return persistenci.traerPostulantes();
    }
    
    @RequestMapping(value="Postulantes/{idPostulante}",method = RequestMethod.GET)
    public Postulante consultarPostulante(@PathVariable("idPostulante") int idPosultante) throws ResourceNotFoundException {
        return persistenci.consultarPostulante(idPosultante);
    }
    
    @RequestMapping(value="/Postulantes/",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarPostulante(@RequestBody Postulante postulante){ 
       String sRpta = persistenci.addPostulante(postulante);
       
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(sRpta,HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(sRpta, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
       
        @RequestMapping(value="/Publicantes",method = RequestMethod.GET)        
    public List<Publicante> consultarPublicantes()  throws ResourceNotFoundException { 
          return persistenci.traerPublicantes();
    }    
    
    @RequestMapping(value="Publicantes/{idPublicante}",method = RequestMethod.GET)
    public Publicante consultarPublicante(@PathVariable("idPublicante") int idPublicante) throws ResourceNotFoundException {
        return persistenci.consultarPublicante(idPublicante);
    }
    
    @RequestMapping(value="/Publicantes/",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarPublicante(@RequestBody Publicante publicante)  throws ResourceNotFoundException{ 
     
       String sRpta = persistenci.addPublicante(publicante);
       
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(sRpta, HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(sRpta, HttpStatus.INTERNAL_SERVER_ERROR);
       }
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
       
       String sRpta = persistenci.addOferta(publi,oferta);
       
            
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(sRpta, HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(sRpta, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    
    @RequestMapping(value="/Ofertas/aplicarOferta/{idpostulante}/{idoferta}",method = RequestMethod.PUT)        
      public ResponseEntity<?> aplicarOferta(@PathVariable("idpostulante") int idpostulante, @PathVariable("idoferta") int idoferta){ 
     
          Oferta ofer = oferCru.findOne(idoferta);
          
          if(ofer == null)
              return new ResponseEntity<>("La Oferta no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
          Postulante post = poscru.findOne(idpostulante);
          
          if(post == null)
              return new ResponseEntity<>("El postulante no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
          
       String sRpta = persistenci.aplicarOferta(post,ofer);
       
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(sRpta, HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(sRpta, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
  
    @RequestMapping(value="/Ofertas/agregarEmpleadoOferta/{idpostulante}/{idoferta}",method = RequestMethod.PUT)        
      public ResponseEntity<?> agregarEmpleadoOferta(@PathVariable("idpostulante") int idpostulante, @PathVariable("idoferta") int idoferta){ 
     
        Oferta ofer = oferCru.findOne(idoferta);
          
        if(ofer == null)
            return new ResponseEntity<>("La Oferta no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
        Postulante post = poscru.findOne(idpostulante);
          
        if(post == null)
            return new ResponseEntity<>("El postulante no existe",HttpStatus.INTERNAL_SERVER_ERROR);
          
        Boolean resultado = persistenci.addEmpleadoOferta(post,ofer);
        
       if (resultado){
            return new ResponseEntity<>("Ok", HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>("Fallo el agregar un empleado a una oferta",HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
      
}
