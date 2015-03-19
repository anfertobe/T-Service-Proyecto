/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Componentes;

import com.tservice.Persistencia.FacturaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anfer_000
 */
@Service
public class MockPago implements IMockPagos {

    @Autowired
    FacturaCrudRepository FactuCrud;
    
    @Override
    public String PagoElectronico() {
        
        int cantidadFactura = FactuCrud.CantFacturas() + 1;
        
       return String.valueOf(cantidadFactura);
    }
    
    
    
}
