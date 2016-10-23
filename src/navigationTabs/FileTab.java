package navigationTabs;
import javafx.scene.control.MenuItem;

public class FileTab extends MenuCreator{
    private MenuItem newTurtle;
    public FileTab(){
        super("FileCommand");
    }

    @Override
    protected void addItems () {
        newTurtle = createMenuItem("NewTurtleCommand"); 
        myMenu.getItems().addAll(newTurtle);
        myMenuBar.getMenus().add(myMenu);
    }

    public MenuItem getNewTurtleItem(){
        return this.newTurtle;
    }
}