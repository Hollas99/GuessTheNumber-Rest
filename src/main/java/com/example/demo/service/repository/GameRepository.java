package com.example.demo.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Game;



@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
	
	Optional<Game> findGameById(Long id);
}