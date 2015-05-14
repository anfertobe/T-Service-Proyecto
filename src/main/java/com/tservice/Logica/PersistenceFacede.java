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
import com.tservice.exceptions.tserviceExceptions;
import java.util.*;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.tservice.Logica.pagos.ConsultaTransaccion;
import com.tservice.Logica.pagos.ResultadoTransaccion;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
    @Autowired
    CategoriaCrudRepository cateCru;
    @Autowired
    LicenciasCrudRepository licenCru;
    @Autowired
    MockPago pago;
    RestTemplate rest = new RestTemplate();
    
       
    public void realizarPago(int monto,String correo, String codigoSeguridad, String nombreTarjeta, String numeroTarjeta){
        ResponseEntity<ResultadoTransaccion> resultado = rest.exchange("http://serviciosrest.cloudhub.io/rest/PAYPAL/pago/tarjeta/"+ numeroTarjeta +"/"+ nombreTarjeta +"/Credito/"+ codigoSeguridad +"/"+ correo +"/monto/"+ monto +"/seguridad/2/TService?servicio=pagos", HttpMethod.PUT, HttpEntity.EMPTY, ResultadoTransaccion.class);
        
        ResultadoTransaccion result = resultado.getBody();
    }
    
    public void resultadoTransaccion(int codigoTransaccion){
        
        ConsultaTransaccion consulta;
        consulta = rest.getForObject("https://pasarelacosw.herokuapp.com/rest/PAYPAL/consultaTransaccion/"+ codigoTransaccion +"?consultaPago", ConsultaTransaccion.class);

    }
    
    
        public List<Licencias> traerLicencias()
    {
        List<Licencias> licencias = new LinkedList<>();
                
        for(Licencias licen : licenCru.findAll())
            licencias.add(licen);
                
        return licencias;
    }
    
     /*
    @obj: agregar oferta a categoria
    *@param: publicante, oferta
    *@pre: El publicante debe existir, La oferta debe existir, la categoria debe existir
    *@return: comentario al agregar categoria
    */
    public void agregarOfertaACategoria(Oferta of, Categoria ca) throws tserviceExceptions
    {
        //Validar que la categoria existe
        if(!this.cateCru.exists(ca.getId()))
            throw new tserviceExceptions("La categoria relacionada a esta oferta no exite, por favor verifique");
        
        Categoria caBD=this.cateCru.findOne(ca.getId());
        
        //Validar que la oferta existe
        if(!this.oferCru.exists(of.getId()))
            throw new tserviceExceptions("La oferta a la cual se esta haciendo referencia no existe, por favor verifique.");

        Oferta ofBD=this.oferCru.findOne(of.getId());

        //Asociar Oferta

        Set<Oferta> ofertas=new  HashSet<>();     
        ofertas.addAll(ofertas);
        ofertas.add(ofBD);

        caBD.setOfertas(ofertas);

        //Actualizar categoria
        this.cateCru.save(caBD);

    }
    
    /*
    *@obj: agregar categoria
    *@param: categoria
    *@pre: 
    *@return: comentario al agregar categoria
    */
    public void addCategoria(Categoria ca) throws tserviceExceptions
    {
        cateCru.save(ca);        
    }
    
    /*
    *@obj: agregar oferta a publicante
    *@param: publicante , oferta
    *@pre: El publicante ya existe , el publicante debe tener una licencia vigente
    *@return: comentario al agregar oferta
    */
    public void addOferta(Publicante pu,Oferta of) throws tserviceExceptions
    {      
        //Si el publicante existe se valida la vigencia de la licencia 
        if(!this.publicru.exists(pu.getIdentificacion()))
            throw new tserviceExceptions("El publicante al cual se hace referencia noexiste por favor verifique.");
        
        Publicante puBD=this.publicru.findOne(pu.getIdentificacion());
            
                //Validar vigencia
                if(!licenciaVigente(puBD))
                    throw new tserviceExceptions("Actual menten no tiene una licencia o esta vencida, por favor adquiera una nueva.");
               
                    //Salvar oferta
                    of.setPublicante(puBD);
                    this.oferCru.save(of);
                    
                    //Asociar oferta
                    
                    Set<Oferta> ofertas=new  HashSet<>();
                    
                    ofertas.addAll(ofertas);
                    
                    
                    ofertas.add(of);
                    puBD.setOfertas(ofertas);
                    
                    
                    //Actualizar publicante
                    this.publicru.save(puBD);
    }
    
    
    private boolean licenciaVigente(Publicante pu){
        
            boolean vigente = false;
            List<Factura> facturas = new LinkedList<>();
            
            for(Factura fac:pu.getFacturas()){
                facturas.add(fac);
            
                       
            Factura facturaActual = getFacturaActual(facturas);
            
            //Si tiene factura actual
            if(facturaActual!=null){
                
                Date fechaActual=facturaActual.getFecha();
                int vigenciaDias=facturaActual.getLicencias().getVigenciaDias();
                Date fechaVencimiento;
                
                System.out.println("Dias vigencia "+vigenciaDias);
                //Sumar dias a fecha
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaActual);
                calendar.add(Calendar.DAY_OF_YEAR, vigenciaDias);
                fechaVencimiento=calendar.getTime();

                System.out.println("Fecha "+vigenciaDias);
                
                //Si la fecha de vencimiento es menor o igual a la fecha actual
                vigente=fechaVencimiento.after(new Date()) || fechaVencimiento.equals(new Date());
                        
               
            }
            }
        return vigente;
    }
    
    
    
    private Factura getFacturaActual(List<Factura> facturas){
        
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
    *@obj: actualizar postulante 
    *se validaran terceros para poder agregarlo
    *@param: postulante
    *@pre: El postulante no existe
    *@return: Comentario para agregar postulante
    */
    public void updPostulante(Postulante po) throws tserviceExceptions 
    {
           postCru.save(po);
    }
    
    
    
    /*
    *@obj: agregar postulante 
    *se validaran terceros para poder agregarlo
    *@param: postulante
    *@pre: El postulante no existe
    *@return: Comentario para agregar postulante
    */
    public void addPostulante(Postulante po) throws tserviceExceptions 
    {
        PasadoJudicial pasadojudicial =  new PasadoJudicial();
        for(int i = 1; i<4; i++){
            pasadojudicial = rest.getForObject("http://serviciosrest.cloudhub.io//rest/ANTECEDENTES/certificadoJudicial/entidad/" + i + "/documento/" + po.getIdentificacion() + "?servicio=certificacion",PasadoJudicial.class);
            if(pasadojudicial.getAntecedentes().size() > 0)
                throw new tserviceExceptions("Usted actualmente tiene asuntos pendientes con la justicia por lo tento no tiene permiso de inscribirse en el sistema.");
        }
   
        //Si no tiene problemas penales se agrega al sistema
        postCru.save(po);
    }  
    
  
      /*
    *@obj: agregar publicante 
    *se validaran terceros para poder agregarlo
    *@param: publicante
    *@pre: El publicante no existe
    *@return: si agrego publicante o no
    */
    public void addPublicante(Publicante po) throws tserviceExceptions
    {
        //Intanciar mock judicial
        PasadoJudicial pasadojudicial =  new PasadoJudicial();
        for(int i = 1; i<4; i++){
            pasadojudicial = rest.getForObject("http://serviciosrest.cloudhub.io//rest/ANTECEDENTES/certificadoJudicial/entidad/" + i + "/documento/" + po.getIdentificacion() + "?servicio=certificacion",PasadoJudicial.class);
            if(pasadojudicial.getAntecedentes().size() > 0)
                throw new tserviceExceptions("Usted actualmente tiene asuntos pendientes con la justicia por lo tento no tiene permiso de inscribirse en el sistema.");
        }
        //Si no tiene problemas penales se agrega al sistema
        publicru.save(po);
        
    }  
    
  
       
    /*
    *@obj: aplicar a oferta postulante
    *@param: postulante , oferta
    *@pre: El postulante ya existe , la oferta ya existe
    *@return: Comentario
    */
    public void aplicarOferta(Postulante po,Oferta of) throws tserviceExceptions
    {        
        //Si el postulante y ademas la oferta existe
        if(!this.postCru.exists(po.getIdentificacion()))
            throw new tserviceExceptions("Usted actualmente no esta regitrado en nuestro sistema por vafor verifique.");
        
        if(!this.oferCru.exists(of.getId()))
            throw new tserviceExceptions("La oferta actual no esta regitrada en nuestro sistema por vafor verifique.");
            //Trae oferta de BD
        
        Oferta ofBD=this.oferCru.findOne(of.getId());

        //Si el publicante existe
        if(this.publicru.exists(ofBD.getPublicante().getIdentificacion()))
            throw new tserviceExceptions("no existe el publicante de la oferta por favor verifique");

        //Traer publicante de BD
        Publicante puBD=this.publicru.findOne(ofBD.getPublicante().getIdentificacion());
                
        if(licenciaVigente(puBD))
            throw new tserviceExceptions("Actualmente la oferta es invalida debido a problemas de licenciamiento.");


        //Trae postulande de BD
        Postulante poBD=this.postCru.findOne(po.getIdentificacion());

        //Si tiene una licencia vigente se asocia a la oferta
        Set<Postulante> postulantes=ofBD.getPostulantes();

        //Agregar a postulantes
        postulantes.add(poBD);


        //Asociar postulantes
        ofBD.setPostulantes(postulantes);

        //Salvar oferta
        this.oferCru.save(ofBD);

        //Notificar correo 
        Gmail correo = new Gmail();
        String texto;

        try{
            texto = "Se le informa que se el señor "+ po.getNombre() +" identificado con la cédula de ciudadanía/n";
            texto += po.getIdentificacion() + " aplicó a la oferta " + of.getDescripcion() +"("+ of.getId() +")/n";

            correo.sender(texto, "Aplicacion a oferta", puBD.getCorreo());
        }catch(Exception e){
            throw new tserviceExceptions(String.format("El correo %s no es valido por favor verifique.", puBD.getCorreo()));

        }
}


    /*
    *@obj: agregar empleado oferta
    *@param: postulante , oferta
    *@pre: El postulante ya existe , la oferta ya existe
    *@return: si aplico oferta o no
    */
    public void addEmpleadoOferta(Postulante po,Oferta of) throws tserviceExceptions
    {
        Gmail correo = new Gmail();
        of.setPostulante(po);
        oferCru.save(of);
        String texto="";


        texto = "Se le informa que se ha sido escogido como empleado en la/n";
        texto += "oferta " + of.getDescripcion() +"("+ of.getId() +")/n";
        Publicante publi = of.getPublicante();

        try {
            correo.sender(texto, ConstantesCorreo.correoAdmin, po.getCorreo());



            texto = "Se le informa que se ha asociado el empleado "+ po.getNombre() + "/n";
            texto += "identificado con la cédula de ciudadanía" + po.getIdentificacion()+"/n";
            texto += "a la oferta " + of.getDescripcion() +"("+ of.getId() +")/n";
    //            

            correo.sender(texto, "Eleccion empleado", publi.getCorreo());
        } catch (Exception ex) {
            throw new tserviceExceptions(String.format("El correo %s no es valido por favor verifique.", publi.getCorreo()));
        }
    }  
    
    public void RealizarPAgo(Publicante publi, Licencias licencia)
    {
        String referenciaPAgo = pago.PagoElectronico();
        Date fecha = new Date();
        Factura factu = new Factura(licencia, publi, referenciaPAgo, (int)licencia.getValor(), fecha);
 
        publi.getFacturas().add(factu);
        
        publicru.save(publi);
    }
    

    /*
    *@obj: traer todos las categorias
    *@param: 
    *@pre: Hay catergorias en BD
    *@return: lista de catergorias
    */
    public List<Categoria> traerCategorias()
    {
        List<Categoria> postulantes = new LinkedList<Categoria>();
                
        for(Categoria cat : cateCru.findAll())
            postulantes.add(cat);
                
        return postulantes;
    }
        
    /*
    *@obj: traer todos las categorias
    *@param: 
    *@pre: Hay catergorias en BD
    *@return: lista de catergorias
    */
    public List<Interes> traerIntereses()
    {
        List<Interes> postulantes = new LinkedList<Interes>();
                
        for(Interes cat : interCrud.findAll())
            postulantes.add(cat);
                
        return postulantes;
    }
        
    
    /*
    *@obj: traer todos los postulantes
    *@param: 
    *@pre: Hay postulantes en BD
    *@return: lista de postulante
    */
    public List<Postulante> traerPostulantes()
    {
        List<Postulante> postulantes = new LinkedList<Postulante>();
                
        for(Postulante post : postCru.findAll())
            postulantes.add(post);
                
        return postulantes;
    }
    
    
    /*
    *@obj: traer categoria especifico
    *@param: 
    *@pre: la categoria existe en BD
    *@return: categoria
    */
    public Categoria consultarCategoria(int identificacion)
    {
        return cateCru.findOne(identificacion);
    }
    
    
    
    /*
    *@obj: traer postulante especifico
    *@param: 
    *@pre: el postulante existe en BD
    *@return: postulante
    */
    public Postulante consultarPostulante(int identificacion)
    {
        return postCru.findOne(identificacion);
    }
    
    /*
    *@obj: traer publicante especifico
    *@param: 
    *@pre: el publicante existe en BD
    *@return: publicante
    */
    public Publicante consultarPublicante(int identificacion)
    {
        return publicru.findOne(identificacion);
    }
    
    /*
    *@obj: traer oferta especifica
    *@param: 
    *@pre: el oferta existe en BD
    *@return: oferta
    */
    public Oferta consultarOferta(int identificacion)
    {
        return oferCru.findOne(identificacion);
    }
    
    
    /*
    *@obj: traer todos los publicantes
    *@param: 
    *@pre: Hay publicantes en BD
    *@return: lista de publicantes
    */
    public List<Publicante> traerPublicantes()
    {
        List<Publicante> publicante = new LinkedList<Publicante>();
                
        for(Publicante pub : publicru.findAll())
            publicante.add(pub);
                
        return publicante;
    }
    
    /*
    *@obj: traer todas las ofertas
    *@param: 
    *@pre: Hay ofertas en BD
    *@return: lista de ofertas
    */
    public List<Oferta> traerOfertas()
    {
        List<Oferta> ofertas = new LinkedList<Oferta>();
                
        for(Oferta ofer : oferCru.findAll())
            ofertas.add(ofer);
                
        return ofertas;
    }
    
}
