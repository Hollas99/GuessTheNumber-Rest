package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Game;
import com.example.demo.model.Round;
import com.example.demo.service.GameService;
import com.example.demo.service.repository.GameRepository;


@RestController
@RequestMapping
public class GameController {

	private final GameService gameService;
	
	@Autowired
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@PostMapping(path="begin")
    @ResponseStatus(code =HttpStatus.CREATED)
	public Long startGame() {
		Long id = gameService.addNewGame();
		return id; //Returns the game ID
	}
	
	@PostMapping(path="guess")
	public Round guessNum(
			@RequestParam(required=true) Long gameId, 
			@RequestParam(required=true) String guess) {
		Round result = gameService.checkGuess(gameId,guess);
		return result;
	}
	@GetMapping(path="game")
	public List<Game> getGames(){
		return gameService.getGames();
	}
	@GetMapping(path="game/{gameId}")
	public Optional<Game> getGame(@PathVariable("gameId") Long gameId){
		return gameService.getGame(gameId);
	}
	@GetMapping(path="rounds/{gameId}")
	public List<Round> getRounds(@PathVariable("gameId") Long gameId){
		return gameService.getRounds(gameId);
	}
}
	
