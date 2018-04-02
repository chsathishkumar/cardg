package com.cardg.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.cardg.model.AccessRights;
import com.cardg.model.Game;
import com.cardg.model.Role;
import com.cardg.model.User;
import com.cardg.repository.AccessRightRepository;
import com.cardg.repository.GameRepository;
import com.cardg.repository.RoleRepository;
import com.cardg.repository.UserRepository;

@Service
public class GameService {

	public User createGame(String user1, String user2, UserRepository userRepository, GameRepository gameRepository) {
		// TODO Auto-generated method stub
				//User user3 = userRepository.findByUserEmail(user1);
				//User user4 = userRepository.findByUserEmail(user1);
				
				Game oGame = new Game();
				oGame.setUser_name(user1);
				oGame.setStatus("New");
				
				gameRepository.save(oGame);
				Game oGame1 = new Game();
				oGame1.setUser_name(user2);
				oGame1.setStatus("New");

				gameRepository.save(oGame1);
				
				return null;
	}
	
}
