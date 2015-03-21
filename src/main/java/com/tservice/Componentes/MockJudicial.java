/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Componentes;

import com.tservice.Logica.pasadoJudicial.Antecedente;
import com.tservice.Logica.pasadoJudicial.PasadoJudicial;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author LuisAndres
 */
public class MockJudicial implements IMockJudicial {

    @Override
    public PasadoJudicial getAntecedentes(int identificacion) {
    
        PasadoJudicial pasado;
        //Traer valor al azar
        String descripcion;
        Date fecha=new Date();
        
        
        
        //Si el valor es par no agrega pasado judicial se encuentra bien
        if (identificacion % 2 == 0){
            descripcion="La persona identificada con cédula de ciudadanía "+identificacion+"/n";
            descripcion+="se encuentra libre de antecedentes penales/n";
            pasado=new PasadoJudicial(descripcion, fecha);
         
        }else{
        //Si es impar genera pasado judicial
            descripcion="La persona identificada con cédula de ciudadanía "+identificacion+"/n";
            descripcion+="se encuentra reportada con antecedentes penales/n";
            pasado=new PasadoJudicial(descripcion, fecha);
            int valor=(int) (Math.random()*10);

            ArrayList<Antecedente> antecedentes=new ArrayList<Antecedente>();
            
            for(int i=0;i<valor;i++){
               int valorP=(int) (Math.random()*10);
                
                if(valorP<5){
                //Si es menor de 5 robo
                    descripcion="Robo en centro comercial Plaza imperial/n";
                    descripcion+="local : "+valorP+"/n";
                }else{
                //Si es mayor de 5 homicidio
                    descripcion="Homicidio culposo suba/n";
                    descripcion+="direccion : "+valorP+"/n";
                }
                antecedentes.add(new Antecedente(descripcion, fecha, valorP));
            
            }
            
            pasado.setAntecedentes(antecedentes);
        }
        
        
        return pasado;
    }
    
}
