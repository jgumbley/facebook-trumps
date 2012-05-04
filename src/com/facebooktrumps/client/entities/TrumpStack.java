package com.facebooktrumps.client.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * An arraylist but sort of with a different name.
 * @author jimgumbley
 * @gwt.typeArgs <com.facebooktrumps.client.model.entities.Trump>
 */
public class TrumpStack extends ArrayList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2758263688259219728L;
	private int currentTrump = 0;
	
	public Trump getNextTrump() {
		currentTrump++;
		if (currentTrump >= size()) currentTrump = 0;
		return getThisTrump();
	}
	
	public Trump getThisTrump() {
		return (Trump) this.get(currentTrump);
	}
	
	public void clearLastHalf(){
		removeRange(this.size() /2 , this.size());
	}
	
	public void clearFirstHalf(){
		removeRange(0 ,this.size() /2);
	}
	
	public TrumpStack  copy() {
	    
	    TrumpStack copyOfThis = new TrumpStack();
	    Iterator i = this.iterator();
	    while (i.hasNext()) {
		copyOfThis.add(i.next());
	    }

	    return copyOfThis;
	}
}
