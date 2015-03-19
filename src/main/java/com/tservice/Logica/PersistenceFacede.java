/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Logica;

import com.tservice.Componentes.MockJudicial;
import com.tservice.Componentes.MockPago;
import com.tservice.Logica.correo.Gmail;
import com.tservice.Logica.pasadoJudicial.PasadoJudicial;
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
    *@return: comentario al agregar oferta
    */
    public String addOferta(Publicante pu,Oferta of)
    {
        boolean add = false;
        String comentario = "OK";
        
        
        //Si el publicante existe se valida la vigencia de la licencia 
        if(this.publicru.exists(pu.getIdentificacion())){
            Publicante puBD=this.publicru.findOne(pu.getIdentificacion());
            
            //Traer factura actual
            Factura facturaActual=getFacturaActual((ArrayList<Factura>) puBD.getFacturas());
            
            //Si tiene factura actual
            if(facturaActual!=null){
                
                Date fechaActual=facturaActual.getFecha();
                int vigenciaDias=facturaActual.getLicencias().getVigenciaDias();
                Date fechaVencimiento;
                
                
                //Sumar dias a fecha
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaActual);
                calendar.add(Calendar.DAY_OF_YEAR, vigenciaDias);
                fechaVencimiento=calendar.getTime();

                //Si la fecha de vencimiento es menor o igual a la fecha actual se agrega la oferta
                if(fechaVencimiento.before(new Date()) || fechaVencimiento.equals(new Date()) ){
               
                    //Salvar oferta
                    this.oferCru.save(of);
                    
                    //Asociar oferta
                    Set<Oferta> ofertas = puBD.getOfertas();
                    ofertas.add(of);
                    puBD.setOfertas(ofertas);
                    
                    
                    //Actualizar publicante
                    this.publicru.save(puBD);
                                       
                    add=true;
                }else{
                    //La licencia actual del publicante ya no tiene vigencia
                    comentario="La licencia actual del publicante ya no tiene vigencia";
                }
            
            }else{
                //El publicante no tiene facturas ni licencias
                comentario="El publicante no tiene facturas ni licencias";
            }
            
        }else{
            //El publicante no existe en el sistema
            comentario="El publicante no existe en el sistema";
        }
               
        return comentario;
    }
    
    private Factura getFacturaActual(ArrayList<Factura> facturas){
        
        Factura fac=null;
        
        //Si tiene facturas
        if (facturas.size()>0){
            
            //Punto de referencia primera factura
            Date fechaMayor=facturas.get(0).getFecha();
            fac=facturas.get(0);
            
            //Recorrer facturas
            for(Factura factura:facturas){
                //Si la fecha de la factura es mayor se cambia variable
                if (factura.getFecha().after(fechaMayor)){
                    fechaMayor=factura.getFecha();
                    fac=factura;
                }
             }
        }
        
    
        return fac;
    
    }
    
    
    
    
    /*
    *@obj: agregar postulante 
    *se validaran terceros para poder agregarlo
    *@param: postulante
    *@pre: El postulante no existe
    *@return: Comentario para agregar postulante
    */
    public String addPostulante(Postulante po)
    {
        //Intanciar mock judicial
        MockJudicial judicial = new MockJudicial();
        PasadoJudicial pasadoJudicial=judicial.getAntecedentes(po.getIdentificacion());
        boolean add = true;
        String comentario="OK";
        
        //Si la persona tiene antecedentes se válida que sean menores a 1 para pasar el primer filtro
        if(pasadoJudicial.getAntecedentes()!=null){
            if(pasadoJudicial.getAntecedentes().size()>1){
                add=false;
                comentario="La persona tiene antecedentes penales bastante graves /n";
                comentario+="por lo tanto no se agregara al sistema";
            }
        }
        
        //Si no tiene problemas penales se agrega al sistema
        if(add) postCru.save(po);
        
        return comentario;
    }  
    
  
      /*
    *@obj: agregar publicante 
    *se validaran terceros para poder agregarlo
    *@param: publicante
    *@pre: El publicante no existe
    *@return: si agrego publicante o no
    */
    public String addPublicante(Publicante po)
    {
        //Intanciar mock judicial
        MockJudicial judicial = new MockJudicial();
        PasadoJudicial pasadoJudicial=judicial.getAntecedentes(po.getIdentificacion());
        boolean add = true;
        String comentario="OK";
        
        
        
        //Si la persona tiene antecedentes se válida que sean menores a 1 para pasar el primer filtro
        if(pasadoJudicial.getAntecedentes()!=null){
            if(pasadoJudicial.getAntecedentes().size()>1){
                comentario="La persona tiene antecedentes penales bastante graves /n";
                comentario+="por lo tanto no se agregara al sistema";
                add=false;
            }
        }
        
        //Si no tiene problemas penales se agrega al sistema
        if(add) publicru.save(po);
        
        return comentario;
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
            String texto="";
            
          
            texto = "Se le informa que se ha sido escogido como empleado en la/n";
            texto += "oferta " + of.getDescripcion() +"("+ of.getId() +")/n";
                            
            
            correo.sender(texto, ConstantesCorreo.correoAdmin, po.getCorreo());
            Publicante publi = of.getPublicante();
            
            texto = "Se le informa que se ha asociado el empleado "+ po.getNombre() + "/n";
            texto += "identificado con la cédula de ciudadanía" + po.getIdentificacion()+"/n";
            texto += "a la oferta " + of.getDescripcion() +"("+ of.getId() +")/n";
            
            
            correo.sender(texto, ConstantesCorreo.correoAdmin, publi.getCorreo());
            
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
        MockPago pago = new MockPago();
        String referenciaPAgo = pago.PagoElectronico();
        Date fecha = new Date();
        Factura factu = new Factura(licencia, publi, referenciaPAgo, (int)licencia.getValor(), fecha);
 
        publi.getFacturas().add(factu);
        
        publicru.save(publi);
    }

}
