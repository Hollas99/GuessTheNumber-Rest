package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Round;
import com.example.demo.service.repository.RoundRepository;


@Service
public class RoundService {
	private final RoundRepository roundRepository;
	
	@Autowired
	public RoundService(RoundRepository roundRepository) {
		this.roundRepository = roundRepository;
	}
	
	public List<Round> getRounds(){
		return roundRepository.findAll();
	}
}
