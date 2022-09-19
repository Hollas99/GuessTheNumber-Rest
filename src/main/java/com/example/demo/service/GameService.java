package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Game;
import com.example.demo.model.Round;
import com.example.demo.service.repository.GameRepository;
import com.example.demo.service.repository.RoundRepository;

@Service
public class GameService {
	@Autowired
	private  GameRepository gameRepository;
	@Autowired
	private  RoundRepository roundRepository;

	public List<Game> getGames(){ //Returns all game
		List<Game> gameList = gameRepository.findAll(); //Temp list of all games 
		for (Game g : gameList) { //Iterate through each game 
			if (!g.getStatus()) //If the game is still in progress
				g.setAnswer(null); //Hide the answer
		}
		return gameRepository.findAll();
	}
	public Optional<Game> getGame(Long id){ //Returns specific game based on id
		Game tempGame = gameRepository.findGameById(id)
				.orElseThrow(()->  new IllegalStateException("Game with ID: "+ id+" does not exist"));
		if (!tempGame.getStatus()) //If the game is still in progress
			tempGame.setAnswer(null); //Hide the answer
		return gameRepository.findGameById(id);
	}
	
	public List<Round> getRounds(Long gameId){ //Returns all rounds for game id
		Optional<Game> game = gameRepository.findById(gameId);
		return roundRepository.findByGame(game);
	}
	
	
	public Long addNewGame() {
		String randomNum= generateNumber(); //Generate the random number answer
		//Create a new game
		Game game = new Game();
		game.setAnswer(randomNum);
		game.setStatus(false);
		gameRepository.save(game);
		return game.getId();  //Return the game id
	}
	public Round checkGuess(Long gameId, String guess) {
		Game game = gameRepository.findGameById(gameId)
				.orElseThrow(()->  new IllegalStateException("Game with ID: "+ gameId+" does not exist"));
		List<Round> roundList= roundRepository.findByGame(game); //Get list of all rounds
		Round round;
		try {
			round = roundList.get(-1); //Try and get the last (most recent) round in the list 
		}catch (IndexOutOfBoundsException e){ //If not it mean this is the first round
			round = new Round();
			round.setGame(game);
		}
		round.setGuess(guess);
		round.setTime(LocalDateTime.now());
		round.setGuessResult(checkPos(guess, game.getAnswer()));

		if (guess.contentEquals(game.getAnswer()))
			game.setStatus(true); //Game complete
		
		roundRepository.save(round);
		return round;
	}
	public String generateNumber() {
		
		int min = 0123;
		int max = 9876;
		Random rand = new Random();
		while (true) { //Generates a number until its 4 different digits
			String n = String.valueOf(rand.nextInt(min,max)); //Generate random number between min and max
			Set<Character> cSet = new HashSet<Character>(); 
			for (int i=0; i<n.length();i++) { //Add each digit to a set
				cSet.add(n.charAt(i));
			}
			if (cSet.size()==4) //If the set isn't 4 in size, it means there was a duplicate number
				return (n);
		}
	}
	public String checkPos(String guess, String answer) {
		int exactMatch = 0;
		int partialMatch = 0;
		for (int i=0; i<guess.length();i++){//Iterate through each digit
			if (answer.indexOf(guess.charAt(i))!=-1) { //If the first character is in the answer
				if (guess.charAt(i)== answer.charAt(i)) //Check if the number is also in the same position
					exactMatch++; //If so, it's an exact match
				else
					partialMatch++; //Otherwise its a partial match
			}
		}
		return ("e:"+exactMatch+"p:"+partialMatch);
	}
}
