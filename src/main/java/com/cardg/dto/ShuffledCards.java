package com.cardg.dto;

import java.util.List;
import java.util.Map;

public class ShuffledCards {

	private Map<String,List<String>> cardAndPlayerMap;
	public Map<String, List<String>> getCardAndPlayerMap() {
		return cardAndPlayerMap;
	}
	public void setCardAndPlayerMap(Map<String, List<String>> cardAndPlayerMap) {
		this.cardAndPlayerMap = cardAndPlayerMap;
	}
}
