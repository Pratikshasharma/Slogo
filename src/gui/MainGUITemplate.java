package gui;

import commandreference.Turtleable;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import navigationTabs.FileTab;
/**
 * @author pratikshasharma
 */
public interface MainGUITemplate {

    public void updateActiveTurtleInfo(int id, Turtleable turtle);
    
    public FileTab getMyFileTab();
    
    public Menu getLanguageMenu();
    
    public Parent createRoot();
    
    public void updateTurtleLocation(Turtleable turtle);
    
    public void setOnRunButton(EventHandler<? super MouseEvent> handler);
    
    public void clearConsole();
    
    public void setConsole(String text);
    
    public String getCommand();
    
    public History getHistory();
    
    public MenuItem getMyNewWindow();
    
    public Menu getPenSizeMenu();
    
    public Menu getPenColorMenu();
    
    public void addColorOption(String key);
    
    public void setOnSaveButtonClicked(EventHandler<? super MouseEvent> handler);
    
    public void setBackgroundColor(String backgroundRGB);
    
    public Menu getBackgroundMenu();

    public void setOnLoadButtonClicked(EventHandler<? super MouseEvent> handler);
    
    public void clearTurtleLines(Turtleable turtle);
    
    public void setOnTogglePenClicked(EventHandler<? super MouseEvent> handler);
    
}
