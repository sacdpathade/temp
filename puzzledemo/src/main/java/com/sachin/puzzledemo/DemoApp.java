package com.sachin.puzzledemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Journey {
    List<EventType> eventTypeList;

	private static Set<EventType> successfulEventTypes = new HashSet<>();

	static {
		successfulEventTypes.add(EventType.ADD_TO_CART);
		successfulEventTypes.add(EventType.VIEW_PRODUCT);
		successfulEventTypes.add(EventType.PURCHASE);
	}

	Journey() {
		eventTypeList = new ArrayList<>();
	}

	private static boolean isSuccessfulEvent(EventType eventType) {
		return eventType != null && successfulEventTypes.contains(eventType);
	}

	public boolean addToJourney(EventType eventType) {
		if(isSuccessfulEvent(eventType)) {
			eventTypeList.add(eventType);
			return true;
		}
		return false;
	}

	public boolean isSuccessful() {
		if(!eventTypeList.isEmpty()) {

			if(eventTypeList.get(eventTypeList.size() - 1) != EventType.PURCHASE) {
				int lastPurchaseIndex = eventTypeList.lastIndexOf(EventType.PURCHASE);
				if(lastPurchaseIndex < 0) {
					return false;
				}
				eventTypeList = eventTypeList.subList(0, lastPurchaseIndex + 1);
			}

			for(EventType eventType : successfulEventTypes) {
				if(!eventTypeList.contains(eventType)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}



public class DemoApp {

	public List<Journey> findSuccessfulJourneys(List<EventType> userEventTypes) {
		List<Journey> successfulJourneys = new ArrayList<>();

		Journey journey = new Journey();
		for(EventType eventType : userEventTypes) {
			boolean isAdded = journey.addToJourney(eventType);
			if(!isAdded) {
				if(journey.isSuccessful()) {
					successfulJourneys.add(journey);
				}
				journey = new Journey();
			}
		}

		if(journey.isSuccessful()) {
			successfulJourneys.add(journey);
		}

		return successfulJourneys;

	}

	public static void main(String[] args) {
		// /AddProd, Purchase, ViewProduct, AddProd, Purchase




	}
}
