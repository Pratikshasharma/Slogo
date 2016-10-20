package navigationTabs;
import javafx.scene.control.MenuItem;

public class FileTab extends MenuCreator{

    public FileTab(){
        super("FileCommand");
        addItems();
    }

    @Override
    protected void addItems () {
        MenuItem newTurtle = createMenuItem("NewTurtleCommand");
        newTurtle.setOnAction(e -> getTurtle());  
        myMenu.getItems().addAll(newTurtle);
        myMenuBar.getMenus().add(myMenu);
    }

    private void getTurtle(){
        System.out.println(" Clicked New Turtle :" );
    }
}
