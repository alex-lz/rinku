package com.rinku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rinku.model.Movement;
import com.rinku.model.MovementId;

@Repository
public interface MovementRepository extends JpaRepository<Movement, MovementId>{

}
