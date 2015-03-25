
package com.tservice.restcontrollers;

import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Postulante;
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
    
    @RequestMapping(value="/Postulante",method = RequestMethod.GET)        
    public Postulante traerPostulante() { 
            
        return persistenci.traerPostulante();
    }
    
}
