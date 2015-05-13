/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Logica.factura;


import java.io.*;
import java.util.stream.Stream;

import com.aspose.cells.*;
/**
 *
 * @author andres
 */
public class FacturaFisica {
    
    
    public void factura() throws FileNotFoundException, IOException, Exception{
        
        InputStream input = new FileInputStream("src\\main\\webapp\\doc\\hojaPrueba.xls");
        Workbook libro = new Workbook(input);
        Worksheet worksheet = libro.getWorksheets().get(0);
        
        OutputStream output = new FileOutputStream("src\\main\\webapp\\doc\\hojaPrueba2.pdf");
        libro.save(output,SaveFormat.PDF);

    }
}
