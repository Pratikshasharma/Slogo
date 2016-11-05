package navigationTabs;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author pratikshasharma
 *Purpose: Create Menu tab in the Scene
 */

public abstract class MenuTemplate implements MenuLayout {
    protected ResourceBundle myResources; 
    protected HBox mySettingOptions = new HBox();
    protected BorderPane optionsRoot = new BorderPane();
    protected Menu myMenu;
    protected MenuBar myMenuBar = new MenuBar();
    public static final String RESOURCE_PACKAGE = "resources";
    public static final String BUTTON_LABEL_FILE = "Button";


    public MenuTemplate(String property) {
        myResources= ResourceBundle.getBundle(RESOURCE_PACKAGE + File.separator + BUTTON_LABEL_FILE);
        String label = myResources.getString(property);
        myMenu = new Menu (label);
        addItems();
    }

    /**
     * 
     * @param property
     * @return MenuItem
     * creates a Menu item based on the String property and returns it
     */
    protected MenuItem createMenuItem(String property){
        MenuItem item;
        if(myResources.containsKey(property)){
            item = new MenuItem(myResources.getString(property));
        } else {
            item = new MenuItem(property);
        }
        return item;
    }

    /**
     * Adds menu item in each menu tab
     */
    protected abstract void addItems();

    /**
     * returns Menu item created
     */
    public Menu getMyMenu(){
        return myMenu;
    }
}

