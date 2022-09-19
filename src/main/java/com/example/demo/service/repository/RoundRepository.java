package com.example.demo.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Game;
import com.example.demo.model.Round;



@Repository
public interface RoundRepository extends JpaRepository<Round, Long>{
	
	List<Round> findByGame(Game game);

	List<Round> findByGame(Optional<Game> game);
}
