/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Persistencia;

import com.tservice.Model.Categoria;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author anfer_000
 */
public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer>{
    
}
