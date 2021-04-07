package com.rinku.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, String> procedureNominaByEmp(
    		@PathVariable(value = "idf") String fecha,
    		@PathVariable(value = "ide") long numero) throws SQLException {
    	
    	HashMap<String, String> map = new HashMap<>();

    	Query query = em.createNativeQuery("call sueldo_mensual_by_emp(:employee, :fecha, '')");
    	query.setParameter("employee", numero);
    	query.setParameter("fecha", fecha);
    	
    	map.put("sueldoMensual", ((String) query.getSingleResult()).strip());
    	
    	return map;
    }
    
    /*@GetMapping("/nomina/{idf}/{ide}")
    public String procedureNominaByEmp(
    		@PathVariable(value = "idf") String fecha,
    		@PathVariable(value = "ide") long numero) throws SQLException {

    	Query query = em.createNativeQuery("call nomina_by_emp(:employee, :fecha, '')");
    	query.setParameter("employee", numero);
    	query.setParameter("fecha", fecha);
    	
    	return ((String) query.getSingleResult()).strip();
    }*/

}
