package gui;

import java.io.File;
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
public interface IGUIController {

    public Parent init();
    
    public void setActiveTurtle(int id, Turtleable turtle);
    public void clearConsole();
    public void setConsole(String text);
    public FileTab getFileTab();
    public void addToCommandHistory(String command);
    public void addToVariableHistory(String variable);
    public void addToFunctionHistory(String function);
    public String getCommandEntered();
    public File chooseFile();
    
    public void addToScene(Turtleable t);
    public String getLanguage();
    public void setOnRunButton(EventHandler<? super MouseEvent> handler);
    public void updateActiveLabels(int id, Turtleable turtle);
    public MenuItem getNewWindowMenu();
    public void addColorOption(String index);
    public void setBackgroundColor(String rgb);
    public void setOnSaveButtonClicked(EventHandler<? super MouseEvent> handler);
    public void setOnLoadButtonClicked(EventHandler<? super MouseEvent> handler);
    public void clearTurtleLines(Turtleable turtle);
    public Menu getPenColorMenu();
    public Menu getPenSizeMenu();
    public Menu getBackgroundMenu();
    public boolean functionHistoryContains(String func);
    public void setOnTogglePen(EventHandler<? super MouseEvent> handler);
    
    
    
}
