package com.cardg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cardg.dto.ShuffledCards;
import com.cardg.service.CardService;
import com.cardg.service.GameService;
import com.cardg.repository.GameRepository;
import com.cardg.repository.UserRepository;
import com.cardg.service.UserService;
import com.cardg.model.User;

@RestController
public class CardController {

	@Autowired
	CardService cardService;
	
	@Autowired
	GameService gameService;
	
	UserService userService = new UserService();
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GameRepository gameRepository;
	
	@RequestMapping(value = "/getProfile", method = RequestMethod.GET)
	public User getProfile(@RequestParam("userEmail") String userEmail, User user) {
		return userService.getProfile(userEmail, user, userRepository);
	}
	
	@RequestMapping(value = "/createGame", method = RequestMethod.POST)
	public User createGame(@RequestParam("user1") String user1, @RequestParam("user2") String user2) {
		return gameService.createGame(user1,user2, userRepository,gameRepository);
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public User updateProfile(@RequestParam("userEmail") String userEmail, @RequestParam("userName") String userName,
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("addressOne") String addressOne,
			@RequestParam("addressTwo") String addressTwo, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("zipcode") String zipcode, User user) {
		return userService.udpateProfile(userEmail, userName, phoneNumber, addressOne, addressTwo, city, state, country,
				zipcode, user, userRepository);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User signup(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail,
			@RequestParam("userPassword") String userPassword, @RequestParam("userLocation") String userLocation,
			@RequestParam("userType") String userType, User user) {
		return userService.saveUser(userEmail, userName, userPassword, userLocation, userType, user, userRepository);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userPassword", required = false) String userPassword) {
		return userService.userExists(userEmail, userPassword, userRepository);
	}

	@RequestMapping(value = "/getShuffledCards", method = RequestMethod.GET)
	public ShuffledCards getShuffledCards(ServletRequest servletRequest, HttpSession httpSession) {
		// authService = new SPSPAuthorizationFilter();

		ShuffledCards oShuffledCards = cardService.getShuffledCards();

		return oShuffledCards;

	}
	
	@RequestMapping(value = "/groupACards", method = RequestMethod.POST)
	public List<String> groupACards(@RequestParam("listAcards") ArrayList listAcards, ServletRequest servletRequest, HttpSession httpSession) {
		//authService = new SPSPAuthorizationFilter();
			
			List<String> groupACard = cardService.groupACards(listAcards);
		
		
		return groupACard;
		
	}
	
	@RequestMapping(value = "/groupBCards", method = RequestMethod.POST)
	public List<String> groupBCards(@RequestParam("listBcards") ArrayList listBcards, ServletRequest servletRequest, HttpSession httpSession) {
		//authService = new SPSPAuthorizationFilter();
			
			List<String> groupBCard = cardService.groupBCards(listBcards);
		
		
		return groupBCard;
		
	}

}
