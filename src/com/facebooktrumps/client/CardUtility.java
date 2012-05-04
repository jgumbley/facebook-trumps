package com.facebooktrumps.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Random;

public class CardUtility {
	
	public static void shuffle(ArrayList list) {
	    
		for (int i = 0; i < list.size(); i++)
	            swap(list, i, Random.nextInt(list.size()));
	    }

	    private static void swap(ArrayList list, int i, int j) {
	        Object tmp = list.get(i);
	        list.set(i, list.get(j));
	        list.set(j, tmp);
	    }

}
