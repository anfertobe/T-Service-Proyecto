/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Facade;


import com.tservice.Logica.PersistenceFacede;
import com.tservice.Model.Categoria;
import com.tservice.Model.Interes;
import com.tservice.Model.Postulante;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author anfer_000
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
          // TODO code application logic here
            ApplicationContext ap = new ClassPathXmlApplicationContext("/applicationContext.xml");
            PersistenceFacede l = (PersistenceFacede) ap.getBean(PersistenceFacede.class);
                        
    }
    
}
