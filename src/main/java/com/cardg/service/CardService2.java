package com.cardg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardg.dto.ShuffledCards;
import com.cardg.repository.CardRepository;



@Service
public class CardService2 {
	//private Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
    private HttpSession httpSession;

	@Autowired
	private CardRepository userRepository;
    
    public final String CURRENT_USER_KEY = "CURRENT_USER";

    

	public String findFullNamebyCuid(String cuid) {
		// TODO Auto-generated method stub
		return userRepository.findFullNamebyCuid(cuid);
	}

	public ShuffledCards getShuffledCards() {
		// TODO Auto-generated method stub
		
		 //String suits = "shdc";
	       // String faces = "a23456789tjqk";
	        String[] players={"player1","player2"};	
	        ShuffledCards oShuffledCards = new ShuffledCards();
	        Map<String,List<String>> cardAndPlayerMap = new HashMap<String, List<String>>();
	        //List<String> allCard = new ArrayList<String>();
	        
	        List<String> player1Cards = new ArrayList<String>();
	        List<String> player2Cards = new ArrayList<String>();
	        
	        
	        
	        String cardName = "";
	        
	        /*for (int suit=0; suit < suits.length(); suit++) {
	            for (int face=0; face < faces.length(); face++) {
	                //... Get the image from the images subdirectory.
	                String imagePath = "cards/images/" + faces.charAt(face) +
	                        suits.charAt(suit) + ".gif";
	                
	                cardName = String.valueOf(faces.charAt(face)) +String.valueOf(suits.charAt(suit)) + ".gif";
	                System.out.println("------Here is yoru card :::"+cardName);
	                allCard.add(cardName);
	            }
	        }
	        */
	        List<String> allCard = new ArrayList<String>();
	        allCard = getCardBundles();
	        	
        		System.out.println(allCard);
		        for(int i=0; i<allCard.size(); i++)
		        {
		        	if(i%2 ==0){
		        		player1Cards.add(allCard.get(i));
		        		System.out.println("Player1 :::"+allCard.get(i));
		        		
		        	}else
		        	{
		        		player2Cards.add(allCard.get(i));
		        		System.out.println("Player2 :::"+allCard.get(i));
		        		
		        	}
		        	
		        	cardAndPlayerMap.put("Player1",player1Cards);
		        	cardAndPlayerMap.put("Player2",player2Cards);
		        	oShuffledCards.setCardAndPlayerMap(cardAndPlayerMap);
		        	
		        }
		        
		        System.out.println(oShuffledCards);
	        
		return oShuffledCards;
	} 
	
	public List<String> getCardBundles()
	{
		List<String> allCard = new ArrayList<String>();
		String[] SUITS = {
	            "c", "d", "h", "s"
	        };

	        String[] RANKS = {
	            "2", "3", "4", "5", "6", "7", "8", "9", "t",
	            "j", "q", "k", "a"
	        };
	        
	        String[] cardBundles = {"1"};

	        // initialize deck
	        int n = SUITS.length * RANKS.length;
	        String[] deck = new String[n];
	        for (int i = 0; i < RANKS.length; i++) {
	            for (int j = 0; j < SUITS.length; j++) {
	                deck[SUITS.length*i + j] = RANKS[i] +SUITS[j]+".gif";
	            }
	        }
	        // shuffle
	        for (int i = 0; i < n; i++) {
	            int r = i + (int) (Math.random() * (n-i));
	            String temp = deck[r];
	            deck[r] = deck[i];
	            deck[i] = temp;
	        }

	        // print shuffled deck
	        for(int bundle=0; bundle<cardBundles.length;bundle++){
		        for (int i = 0; i < n; i++) {
		            //System.out.println(deck[i]);
		            allCard.add(deck[i]);
		        }
	        }
	        return allCard;
	}

	public List<String> groupACards(ArrayList listAcards) {
		// TODO Auto-generated method stub
		String[] SUITS = {
	            "c", "d", "h", "s"
	        };
		List<String> allCard = new ArrayList<String>();
		String cardName = "";
		for (int j = 0; j < SUITS.length; j++) {
		for(Object card:listAcards){
			cardName = card.toString();
			System.out.println(card.toString());
			if(SUITS[j].equalsIgnoreCase(String.valueOf(cardName.charAt(1)))){
				allCard.add(cardName);
			}
			
		}
		}
		return allCard;
	}
	
	public List<String> groupBCards(ArrayList listBcards) {
		// TODO Auto-generated method stub
		String[] SUITS = {
	            "c", "d", "h", "s"
	        };
		List<String> allCard = new ArrayList<String>();
		String cardName = "";
		for (int j = 0; j < SUITS.length; j++) {
		for(Object card:listBcards){
			cardName = card.toString();
			System.out.println(card.toString());
			if(SUITS[j].equalsIgnoreCase(String.valueOf(cardName.charAt(1)))){
				allCard.add(cardName);
			}
			
		}
		}
		return allCard;
	}

	

}
