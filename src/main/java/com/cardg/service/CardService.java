package com.cardg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardg.dto.ShuffledCards;
import com.cardg.repository.UserRepository;

@Service
public class CardService {
	// private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private UserRepository userRepository;

	public final String CURRENT_USER_KEY = "CURRENT_USER";

	public ShuffledCards getShuffledCards() {
		// TODO Auto-generated method stub

		// String suits = "shdc";
		// String faces = "a23456789tjqk";
		String[] players = { "player1", "player2" };
		ShuffledCards oShuffledCards = new ShuffledCards();
		Map<String, List<String>> cardAndPlayerMap = new HashMap<String, List<String>>();
		// List<String> allCard = new ArrayList<String>();

		List<String> player1Cards = new ArrayList<String>();
		List<String> player2Cards = new ArrayList<String>();

		List<String> playerCards = new ArrayList<String>();

		String cardName = "";

		List<String> allCard = new ArrayList<String>();
		List<String> allCardTemp = new ArrayList<String>();
		
		String[] cardBundles = { "1","2"};
		for(int i=0; i<cardBundles.length; i++){
			allCardTemp = getCardBundles();
			for (String moreCards : allCardTemp){
				allCard.add(moreCards);
			}
		}
		

		System.out.println(allCard);
		for (int i = 0; i < allCard.size(); i++) {
			playerCards.add(allCard.get(i));
			cardAndPlayerMap.put("Player", playerCards);
			oShuffledCards.setCardAndPlayerMap(cardAndPlayerMap);
		}

		return oShuffledCards;
	}

	public List<String> getCardBundles() {
		List<String> allCard = new ArrayList<String>();
		String[] SUITS = { "c", "d", "h", "s" };

		String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k", "a" };

		String[] cardBundles = { "1","2","3"};

		// initialize deck
		int n = SUITS.length * RANKS.length;
		String[] deck = new String[n];
		for (int bundle = 0; bundle < cardBundles.length; bundle++) {
		for (int i = 0; i < RANKS.length; i++) {
			for (int j = 0; j < SUITS.length; j++) {
				deck[SUITS.length * i + j] = RANKS[i] + SUITS[j] + ".gif";
			}
		}
		}
		// shuffle
		for (int i = 0; i < n; i++) {
			int r = i + (int) (Math.random() * (n - i));
			String temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}

		// print shuffled deck
		
			for (int i = 0; i < n; i++) {
				// System.out.println(deck[i]);
				allCard.add(deck[i]);
			}
		
		return allCard;
	}

	public List<String> groupACards(ArrayList listAcards) {
		// TODO Auto-generated method stub
		String[] SUITS = { "s", "h", "d", "c" };
		List<String> allCard = new ArrayList<String>();
		List<String> allCardtemp = new ArrayList<String>();
		String cardName = "";
		String symbol = "";
		for (int j = 0; j < SUITS.length; j++) {
			allCard = new ArrayList<String>();
			for (Object card : listAcards) {				
				cardName = card.toString();
				symbol = String.valueOf(cardName.charAt(1));
				/*if(!String.valueOf(cardName.charAt(0)).equalsIgnoreCase("1"))
					symbol = String.valueOf(cardName.charAt(1));
				else
					symbol = String.valueOf(cardName.charAt(2));*/
				if (SUITS[j].equalsIgnoreCase(symbol)) {
					if(cardName.startsWith("t")){
						cardName=cardName.replace("t", "9z");
					}else if(cardName.startsWith("a")){
						cardName=cardName.replace("a", "1a");
					}else if(cardName.startsWith("q")){
						cardName=cardName.replace("q", "jz");
					}
					allCard.add(cardName);
				}				
			}
			Collections.sort(allCard);
			for(String cardTemp :allCard){				
				if(cardTemp.startsWith("9z")){
					cardTemp=cardTemp.replace("9z", "t");
				}else if(cardTemp.startsWith("1a")){
					cardTemp=cardTemp.replace("1a", "a");
				}else if(cardTemp.startsWith("jz")){
					cardTemp=cardTemp.replace("jz", "q");
				}
				allCardtemp.add(cardTemp);
			}
		}
		
		return allCardtemp;
	}

	public List<String> groupBCards(ArrayList listBcards) {
		// TODO Auto-generated method stub
		String[] SUITS = { "s", "h", "d", "c" };
		List<String> allCard = new ArrayList<String>();
		List<String> allCardtemp = new ArrayList<String>();
		String cardName = "";
		String symbol = "";
		for (int j = 0; j < SUITS.length; j++) {
			allCard = new ArrayList<String>();
			for (Object card : listBcards) {
				
				cardName = card.toString();
				symbol = String.valueOf(cardName.charAt(1));
				/*if(!String.valueOf(cardName.charAt(0)).equalsIgnoreCase("1"))
					symbol = String.valueOf(cardName.charAt(1));
				else
					symbol = String.valueOf(cardName.charAt(2));*/
				if (SUITS[j].equalsIgnoreCase(symbol)) {
					if(cardName.startsWith("t")){
						cardName=cardName.replace("t", "9z");
					}else if(cardName.startsWith("a")){
						cardName=cardName.replace("a", "1a");
					}else if(cardName.startsWith("q")){
						cardName=cardName.replace("q", "jz");
					}
					allCard.add(cardName);
				}				
			}
			Collections.sort(allCard);
			for(String cardTemp :allCard){
				if(cardTemp.startsWith("9z")){
					cardTemp=cardTemp.replace("9z", "t");
				}else if(cardTemp.startsWith("1a")){
					cardTemp=cardTemp.replace("1a", "a");
				}else if(cardTemp.startsWith("jz")){
					cardTemp=cardTemp.replace("jz", "q");
				}
				allCardtemp.add(cardTemp);
			}
		}
		
		return allCardtemp;
	}

}
