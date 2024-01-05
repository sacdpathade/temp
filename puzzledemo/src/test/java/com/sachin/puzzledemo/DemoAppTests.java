package com.sachin.puzzledemo;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DemoAppTests {
	DemoApp app = new DemoApp();

	@Before
	public void initialize() {
		app = new DemoApp();
	}

	@Test
	public void testSuccessSingleJourney() {
		//AddProd, Purchase, ViewProduct, AddProd, Purchase
		List<EventType> eventTypeList = Arrays.asList(EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE);
		List<Journey> journeyList = app.findSuccessfulJourneys(eventTypeList);
		assertTrue("Failed", journeyList.size() == 1);
	}

	@Test
	public void testSuccessSingleJourneyFailure() {
		//AddProd, Purchase, ViewProduct, AddProd, Purchase
		List<EventType> eventTypeList = Arrays.asList(EventType.ADD_TO_CART,  EventType.PURCHASE, EventType.VIEW_PRODUCT);
		List<Journey> journeyList = app.findSuccessfulJourneys(eventTypeList);
		assertTrue("Failed", journeyList.size() == 0);
	}

	@Test
	public void testSuccessSingleJourneyWithrRepeatTasks() {
		//AddProd, Purchase, ViewProduct, AddProd, Purchase
		List<EventType> eventTypeList = Arrays.asList(EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE, EventType.VIEW_PRODUCT, EventType.PURCHASE);
		
		//List<EventType> eventTypeList = Arrays.asList(EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE, EventType.VIEW_PRODUCT);
		List<Journey> journeyList = app.findSuccessfulJourneys(eventTypeList);
		System.out.println(journeyList);
		assertTrue("Failed", journeyList.size() == 1);
	}

	// @Test
	// public void testSuccessSingleJourneyWithrRepeatPurchaseTask() {
	// 	//AddProd, Purchase, ViewProduct, AddProd, Purchase
	// 	List<EventType> eventTypeList = Arrays.asList(EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE, EventType.VIEW_PRODUCT, EventType.PURCHASE);
	// 	List<Journey> journeyList = app.findSuccessfulJourneys(eventTypeList);
	// 	System.out.println(journeyList);
	// 	assertTrue("Failed", journeyList.size() == 1);
	// }
}
