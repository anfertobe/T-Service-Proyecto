
package com.tservice.restcontrollers;

import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Oferta;
import com.tservice.Model.Postulante;
import com.tservice.Model.Publicante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping("/tservice")
public class RestControllerTservice {
    
    @Autowired
    PersistenceFacede persistenci;
    
    @RequestMapping(value="/Postulantes",method = RequestMethod.GET)        
    public List<Postulante> consultarPostulantes()  throws ResourceNotFoundException { 
          return persistenci.traerPostulantes();
    }
    
    @RequestMapping(value="Postulantes/{idPostulante}",method = RequestMethod.GET)
    public Postulante consultarPostulante(@PathVariable("idPostulante") int idPosultante) throws ResourceNotFoundException {
        return persistenci.consultarPostulante(idPosultante);
    }
    
    @RequestMapping(value="/Postulantes/",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarPostulante(@PathVariable Postulante postulante)  throws ResourceNotFoundException{ 
     
       String sRpta = persistenci.addPostulante(postulante);
       
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<?> agregarPublicante(@PathVariable Publicante publicante)  throws ResourceNotFoundException{ 
     
       String sRpta = persistenci.addPublicante(publicante);
       
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    
    @RequestMapping(value="/Ofertas/",method = RequestMethod.PUT)        
    public ResponseEntity<?> agregarOferta(@PathVariable Publicante publicante,@PathVariable Oferta oferta)  throws ResourceNotFoundException{ 
     
       String sRpta = persistenci.addOferta(publicante,oferta);
       
            
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    
    @RequestMapping(value="/Ofertas/aplicarOferta",method = RequestMethod.PUT)        
      public ResponseEntity<?> aplicarOferta(@PathVariable Postulante postulante,@PathVariable Oferta oferta)  throws ResourceNotFoundException{ 
     
       String sRpta = persistenci.aplicarOferta(postulante,oferta);
       
       if (sRpta.trim().equals("OK")){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
  
    @RequestMapping(value="/Ofertas/agregarEmpleadoOferta",method = RequestMethod.PUT)        
      public ResponseEntity<?> agregarEmpleadoOferta(@PathVariable Postulante postulante,@PathVariable Oferta oferta)  throws ResourceNotFoundException{ 
     
       if (persistenci.addEmpleadoOferta(postulante,oferta)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
      
}
