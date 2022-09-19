package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Round {
	
	@Id
	@SequenceGenerator(
			name = "round_sequence",
			sequenceName = "round_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "round_sequence"
			)
	private Long id;
	private String guess;
	private LocalDateTime time;
	private String guessResult;
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="gameId", nullable = false)
	@ToString.Exclude
	@JsonIgnore
	private Game game;
}
