package com.rinku.controller;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NominaController {

    @PersistenceContext
    private EntityManager em;
    
    @GetMapping("/nomina/{emp}")
    public Integer procedureNominaByEmp(@PathVariable(value = "emp") int emp) throws SQLException {

    	Query query = em.createNativeQuery("call nomina_by_emp(:id)");
    	query.setParameter("id", emp);
    	
    	return ((Number) query.getSingleResult()).intValue();
    }

}
