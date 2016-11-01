package gui;

import java.util.prefs.Preferences;

public class UserDefaults {
	
	Preferences myPrefs;
	private static final String BACKGROUND_COLOR = "backgroundcolor";
	private static final String PEN_COLOR = "pencolor";
	private static final String PEN_WIDTH = "penwidth";
	
	public UserDefaults(String path){
		myPrefs = Preferences.userRoot().node(path);
	}
	
	public void setBackground(String value){
		myPrefs.put(BACKGROUND_COLOR, value);
	}
	
	public String getBackground(String def){
		return myPrefs.get(BACKGROUND_COLOR, def);
	}
	
	public void setPenColor(String value){
		myPrefs.put(PEN_COLOR, value);
	}
	
	public String getPenColor(String def){
		return myPrefs.get(PEN_COLOR, def);
	}
	
	public void setPenWidth(int value){
		myPrefs.putInt(PEN_WIDTH, value);
	}
	
	public int getPenWidth(int def){
		return myPrefs.getInt(PEN_COLOR, def);
	}
}
