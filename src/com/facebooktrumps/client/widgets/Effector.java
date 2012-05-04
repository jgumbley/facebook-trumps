package com.facebooktrumps.client.widgets;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class Effector {
	
	public static void appear(Widget w) {
		w.setVisible(false);
		applyEffect("Appear", w.getElement());
	}
	
	private native static void applyEffect(String effect, Element element) /*-{
	   var ne = $wnd._nativeExtensions;
	   $wnd._nativeExtensions = false;
	   $wnd.Effect[effect](element);
	   $wnd._nativeExtensions = ne;
	  }-*/;
	
	public static void pulsate(Widget w) {
		applyEffect("Pulsate", w.getElement());
	}
	
	public static void grow(Widget w) {
		w.setVisible(false);
		applyEffect("Grow", w.getElement());
	}

}
