package com.cardg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cardg.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	@Query(value = "SELECT * FROM Game ga where ga.game_name = :gameName", nativeQuery = true)
	public Game findByGameName(@Param("gameName") String gameName);

}
