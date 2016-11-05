package navigationTabs;

import javafx.scene.control.Menu;

public interface ToolsTab {
    
    public Menu getBackgroundColorMenu();
    
    public Menu getPenSizeSubMenu();
    
    public Menu getPenColorSubMenu();
    
    public void addColorOption(String Key);

}
