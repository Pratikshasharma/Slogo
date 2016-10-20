package navigationTabs;
import javafx.scene.control.Menu;
public class Tools extends MenuCreator {
    private Menu colorSubMenu;
    private Menu penSizeSubMenu;

    public Tools () {
        super("ToolsCommand");
        addItems();
    }

    @Override
    protected void addItems () {
        colorSubMenu = new Menu(myResources.getString("PenColorCommand"));
        penSizeSubMenu = new Menu(myResources.getString("PenSizeCommand"));
        addColorOptions();
        addPenSizeOptions();
        myMenu.getItems().addAll(colorSubMenu, penSizeSubMenu);
    } 

    private void addColorOptions(){ 
        colorSubMenu.getItems().add(createMenuItem("RedPenColor"));
        colorSubMenu.getItems().add(createMenuItem("BluePenColor"));
        colorSubMenu.getItems().add(createMenuItem("PinkPenColor"));
        colorSubMenu.getItems().add(createMenuItem("BlackPenColor"));
    }

    private void addPenSizeOptions(){
        penSizeSubMenu.getItems().addAll(createMenuItem("1"),createMenuItem("2"),createMenuItem("3"),createMenuItem("4"),createMenuItem("5"));
    }



}
