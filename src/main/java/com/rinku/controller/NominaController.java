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
    
    @GetMapping("/nomina/{idf}/{ide}")
    public long procedureNominaByEmp(
    		@PathVariable(value = "idf") String fecha,
    		@PathVariable(value = "ide") long numero) throws SQLException {

    	Query query = em.createNativeQuery("call nomina_by_emp(:employee, :fecha)");
    	query.setParameter("employee", numero);
    	query.setParameter("fecha", fecha);
    	
    	return ((Number) query.getSingleResult()).longValue();
    }

}
