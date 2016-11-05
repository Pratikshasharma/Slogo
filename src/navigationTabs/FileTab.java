package navigationTabs;
import javafx.scene.control.MenuItem;
/**
 * 
 * @author pratikshasharma
 *Dependencies: MenuTemplate Super Class
 *Assumptions: Assumes createMenuItem() method exists in MenuTemplate super class
 *
 */

public class FileTab extends MenuTemplate{
    private MenuItem newTurtle;
    public FileTab(){
        super("FileCommand");
    }

    public void addItems () {
        newTurtle = createMenuItem("NewTurtleCommand"); 
        myMenu.getItems().addAll(newTurtle);
        myMenuBar.getMenus().add(myMenu);
    }

    public MenuItem getNewTurtleItem(){
        return this.newTurtle;
    }

}