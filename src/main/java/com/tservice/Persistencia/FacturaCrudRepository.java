/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Persistencia;

import com.tservice.Model.Factura;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author anfer_000
 */
public interface FacturaCrudRepository extends CrudRepository<Factura, Integer>{
    @Query("select count(*) from Factura")
    public int CantFacturas();
}
