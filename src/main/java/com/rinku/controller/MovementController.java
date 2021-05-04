package com.rinku.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rinku.exception.ResourceNotFoundException;
import com.rinku.model.Movement;
import com.rinku.model.MovementId;
import com.rinku.repository.MovementRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MovementController {
    @Autowired
    private MovementRepository movementRepository;
    
    @GetMapping("/movement/all")
    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    @GetMapping("/movement/{idf}/{idn}")
    public ResponseEntity<Movement> getMovementById(
    	 @PathVariable(value = "idf") String fecha,
       	 @PathVariable(value = "idn") long numero)
        throws ResourceNotFoundException {
    	MovementId movementId = new  MovementId();
    	movementId.setFecha(fecha);
    	movementId.setNumero(numero);
    	
        Movement movement = movementRepository.findById(movementId)		
          .orElseThrow(() -> new ResourceNotFoundException("Movement not found for this id :: " + movementId));
        return ResponseEntity.ok().body(movement);
    }
    
    @PostMapping("/register/movement")
    public Movement createMovement(@Valid @RequestBody Movement movement) {
    	
        return movementRepository.save(movement);
    }

    @PutMapping("/update/movement/{idf}/{idn}")
    public ResponseEntity<Movement> updateMovement(
         @PathVariable(value = "idf") String fecha,
    	 @PathVariable(value = "idn") long numero,
         @Valid @RequestBody Movement movementDetails) throws ResourceNotFoundException {
    	MovementId movementId = new  MovementId();
    	movementId.setFecha(fecha);
    	movementId.setNumero(numero);
    	
        Movement movement = movementRepository.findById(movementId)
        .orElseThrow(() -> new ResourceNotFoundException("Movement not found for this id :: " + movementId));

       
        //movement.setNumero(movementDetails.getNumero());
        //movement.setFecha(movementDetails.getFecha());
        //movement.setMovementId(movementId);
        movement.setMovementId(movementDetails.getMovementId());
        movement.setEntregas(movementDetails.getEntregas());
        movement.setCubrio(movementDetails.isCubrio());
        movement.setRol(movementDetails.getRol());
        
        final Movement updatedMovement = movementRepository.save(movement);
        return ResponseEntity.ok(updatedMovement);
    }

    @DeleteMapping("/delete/movement/{idf}/{idn}")
    public Map<String, Boolean> deleteMovement(
    	 @PathVariable(value = "idf") String fecha,
       	 @PathVariable(value = "idn") long numero)
         throws ResourceNotFoundException {
    	MovementId movementId = new  MovementId();
    	movementId.setFecha(fecha);
    	movementId.setNumero(numero);
    	
        Movement movement = movementRepository.findById(movementId)
       .orElseThrow(() -> new ResourceNotFoundException("Movement not found for this id :: " + movementId));

        movementRepository.delete(movement);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
