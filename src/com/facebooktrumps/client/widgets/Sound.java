package com.facebooktrumps.client.widgets;

import com.google.gwt.user.client.ui.HTML;

/**
 * Sound Class adapted from gpokr blog
 * 
 * @author jimgumbley
 *
 */
public class Sound extends HTML {
	
	static boolean _mute = false;
	
	public static void setMute(boolean mute){
		_mute = mute;
	}
	
	public static boolean isMute(){
		return _mute;
	}


	public void playMusic () {
		playSoundR("http://betoni.aivo.com/", "betoni56");
	}
	
	public void playSound ( String soundname ) {
		if (!_mute) playSoundR(soundname, "http://localhost:8888/com.facebooktrumps.ui/snd/");
	}
	
	private void playSoundR( String soundname, String host ) {
		this.setHTML( "<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0' width='1' height='1' id='player' align='absmiddle' VIEWASTEXT>" +
	     		"<param name='wmode' value='transparent' />"+
	     		"<param name='allowScriptAccess' value='sameDomain' />"+
	     		"<param name='flashVars' value='theLink=" + host + soundname +".mp3' />"+
	     		"<param name='movie' value='http://del.icio.us/static/swf/playtagger.swf' />"+
	     		"<param name='quality' value='high' />" +
	     		"<embed src='http://del.icio.us/static/swf/playtagger.swf' flashVars='theLink=http://www.gpokr.com/"+soundname+".mp3' quality='high' wmode='transparent' width='1' height='1' name='player' align='absmiddle' allowScriptAccess='sameDomain' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />"+
	     		"</object>" );
	}

}
