package navigationTabs;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;



public class Tools extends MenuCreator {
    private Menu colorSubMenu;
    private Menu penSizeSubMenu;
    private Menu backgroundColorMenu;
    private String penSizeIndex="1";
    private String penColorIndex="1";

    public Tools () {
        super("ToolsCommand");
    }

    @Override
    protected void addItems () {
        colorSubMenu = new Menu(myResources.getString("PenColorCommand"));
        penSizeSubMenu = new Menu(myResources.getString("PenSizeCommand"));
        backgroundColorMenu = new Menu(myResources.getString("BackgroundColorCommand"));
        addColorOptions(colorSubMenu);
        addColorOptions(backgroundColorMenu);
        addPenSizeOptions();
        addSizeColorListeners();
        myMenu.getItems().addAll(colorSubMenu, penSizeSubMenu, backgroundColorMenu);	
    }

    private void addColorOptions(Menu menu){ 
//        menu.getItems().add(createMenuItem("RedPenColor"));
//        menu.getItems().add(createMenuItem("BluePenColor"));
//        menu.getItems().add(createMenuItem("PinkPenColor"));
//        menu.getItems().add(createMenuItem("BlackPenColor"));
        menu.getItems().add(new MenuItem("1"));
        menu.getItems().add(new MenuItem("2"));
        menu.getItems().add(new MenuItem("3"));
        
    }

    private void addPenSizeOptions(){
        penSizeSubMenu.getItems().addAll(createMenuItem("1"),createMenuItem("2"),createMenuItem("3"),createMenuItem("4"),createMenuItem("5"));
    }

    public Menu getBackgroundColorMenu(){
        return backgroundColorMenu;
    }

    public Menu getPenSizeSubMenu(){
        return this.penSizeSubMenu;
    }

    public Menu getPenColorSubMenu(){
        return this.colorSubMenu;
    }

    private void addSizeColorListeners (){
        penSizeSubMenu.getItems().stream().forEach(size -> size.setOnAction(e-> penSizeIndex = size.getText()));
        colorSubMenu.getItems().stream().forEach(color ->color.setOnAction( e-> penColorIndex = color.getText()));      
    }

    public String getPenSizeIndex(){
        return this.penSizeIndex;
    }
    
    public String getPenColorIndex(){
        return this.penColorIndex;
    }

}
