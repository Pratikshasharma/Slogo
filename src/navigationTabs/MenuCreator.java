package navigationTabs;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public abstract class MenuCreator {

	protected ResourceBundle myResources;  
	protected HBox mySettingOptions = new HBox();
	protected BorderPane optionsRoot = new BorderPane();
	protected Menu myMenu;
	protected MenuBar myMenuBar = new MenuBar();
	protected BorderPane myCanvas;
	public static final String RESOURCE_PACKAGE = "resources";
	private static final String BUTTON_LABEL_FILE = "Button";

	public MenuCreator(String property, BorderPane canvas) {
		myCanvas = canvas;
		myResources= ResourceBundle.getBundle(RESOURCE_PACKAGE + File.separator + BUTTON_LABEL_FILE);
		String label = myResources.getString(property);
		myMenu = new Menu (label);
	}
	
	public MenuCreator(String property){
		myResources= ResourceBundle.getBundle(RESOURCE_PACKAGE + File.separator + BUTTON_LABEL_FILE);
		String label = myResources.getString(property);
		myMenu = new Menu (label);
	}

	protected MenuItem createMenuItem(String property){
		MenuItem item;
		if(myResources.containsKey(property)){
			item = new MenuItem(myResources.getString(property));
		} else {
			item = new MenuItem(property);
		}
		return item;
	}

	protected abstract void addItems();

	public Menu getMyMenu(){
		return myMenu;
	}
}

