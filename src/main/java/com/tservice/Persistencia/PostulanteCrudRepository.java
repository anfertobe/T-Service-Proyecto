/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Persistencia;

import com.tservice.Model.*;
import java.util.Set;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author anfer_000
 */
public interface PostulanteCrudRepository extends CrudRepository<Postulante, Integer>{
    
//    @Modifying
//    @Query("update Postulante p set p.experienciaLaborals = :expe where p.Identificacion = :id")
//    int SetExpe(@Param("expe") Set<ExperienciaLaboral> expe, @Param("id") long id);
}
