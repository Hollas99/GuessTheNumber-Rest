package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table
public class Game {
	@Id
	@SequenceGenerator(
			name = "game_sequence",
			sequenceName = "game_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "game_sequence"
			)
	private Long id;
	private Boolean status;
	private String answer;
	@OneToMany(mappedBy="game", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Round> rounds;
	
}
