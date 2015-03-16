/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Logica;

import com.tservice.Componentes.Mocks;
import com.tservice.Logica.correo.Gmail;
import com.tservice.Model.*;
import com.tservice.Persistencia.*;
import java.util.*;
import java.util.List;
import java.util.Set;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author anfer_000
 */
@Service
public class PersistenceFacede {
    
    
    @Autowired
    HojaDeVidaCrudRepository hvc;
    @Autowired
    PostulanteCrudRepository postCru;
    @Autowired
    ExperienciaLaboralCrudRepository expeCrud;
    @Autowired
    InteresCrudRepository interCrud;
    @Autowired
    Gmail gmail;
    @Autowired
    PublicanteCrudRepository publicru;
    @Autowired
    OfertaCrudRepository oferCru;
    
    public void pruebaPersistenciaEntidades() throws MessagingException
    {
        //Creacion de Hoja De Vide
        
        boolean asd = gmail.sender("","","");
        
        HojaDeVida hv = new HojaDeVida("HojaDeVidaPrueba", "FechaActualizacionPrueba", "FotoPrueba");
        hvc.save(hv);
        
        Postulante post = new Postulante(14, hv, 23456789, "NombrePrueba", new Date(1,1,1), "CorreoPrueba", "DireccionPrueba", "123456789", "PaisPrueba", "regionPrueba", "CiudadPrueba");      
                 
        ExperienciaLaboral expe = new ExperienciaLaboral("EntidaPrueba", new Date(1,1,1), "CometarioPrueba");
        expe.setComentario(this.toString().substring(0,45));
 
        
        
        post.getExperienciaLaborals().add(expe);
        
        //Set<ExperienciaLaboral> experi = post.getExperienciaLaborals();
    
        //experi.add(expe);
        
        //post.setExperienciaLaborals(experi);
        
        //PARA  EVITAR EL PROBLEMA DE LA EXPERIENCIA LABORAL DUPLICADA, DEBERÍAN HACER PERSISTENTE AL
        //POSTULANTE SÓLO UNA VEZ (SI COMENTAN ESTE PRIMER 'SAVE' Y DEJAN SÓLO EL DEL FINAL LES DEBERÍA CORREGIR EL 
        //PROBLEMA DE LOS DUPLICADOS
        
        //postCru.save(post);
        
        //

        //System.out.println("Experiencia****** "+ postCru.findOne(post.getIdentificacion()).getExperienciaLaborals().size()); 
        
        Interes interes = new Interes("ExperienciaPrueba");
        
        Set<Interes> inter = post.getIntereses();
        inter.add(interes);
        post.setIntereses(inter);
        postCru.save(post);
        //interCrud.save(interes);

        //expeCrud.save(expe);
        
        
    }
    
    
       
    /*
    *@obj: agregar oferta a publicante
    *@param: publicante , oferta
    *@pre: El publicante ya existe , el publicante debe tener una licencia vigente
    *@return: si agrego oferta o no
    */
    public boolean addOferta(Publicante pu,Oferta of)
    {
        return false;
    }  
    
    
    
    /*
    *@obj: agregar postulante 
    *se validaran terceros para poder agregarlo
    *@param: postulante
    *@pre: El postulante no existe
    *@return: si agrego postulante o no
    */
    public boolean addPostulante(Postulante po)
    {
        return false;
    }  
    
  
      /*
    *@obj: agregar publicante 
    *se validaran terceros para poder agregarlo
    *@param: publicante
    *@pre: El publicante no existe
    *@return: si agrego publicante o no
    */
    public boolean addPublicante(Publicante po)
    {
        return false;
    }  
    
  
       
    /*
    *@obj: aplicar a oferta postulante
    *@param: postulante , oferta
    *@pre: El postulante ya existe , la oferta ya existe
    *@return: si aplico oferta o no
    */
    public boolean aplicarOferta(Postulante po,Oferta of)
    {
        return false;
    }  

    /*
    *@obj: agregar empleado oferta
    *@param: postulante , oferta
    *@pre: El postulante ya existe , la oferta ya existe
    *@return: si aplico oferta o no
    */
    public boolean addEmpleadoOferta(Postulante po,Oferta of)
    {       
        Boolean transaccion;
        Gmail correo = new Gmail();
        try{
            of.setPostulante(po);
            oferCru.save(of);
            
            correo.sender("asdfgh", "sdfghj", po.getCorreo());
            Publicante publi = of.getPublicante();
            
            correo.sender("dffdsg", "dfsfas", publi.getCorreo());
            
            transaccion = true;
        }catch(Exception e){
            transaccion = false;
        }
       return transaccion;
    }  
    
    private Object TraerUltimoelemtoLista(List listaObjetos)
    {
        Object objeto = listaObjetos.get(listaObjetos.size()-1);
        
        return objeto;
    }
    
    
    public void RealizarPAgo(Publicante publi, Licencias licencia)
    {
        Mocks pago = new Mocks();
        String referenciaPAgo = pago.PagoElectronico();
        Date fecha = new Date();
        Factura factu = new Factura(licencia, publi, referenciaPAgo, (int)licencia.getValor(), fecha);
 
        publi.getFacturas().add(factu);
        
        publicru.save(publi);
    }

}
