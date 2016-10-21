package navigationTabs;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Tools extends MenuCreator {
	
    private Menu colorSubMenu;
    private Menu penSizeSubMenu;
    private Menu backgroundColorMenu;

    public Tools (BorderPane canvas) {
        super("ToolsCommand", canvas);
        addItems();
    }

    @Override
    protected void addItems () {
        colorSubMenu = new Menu(myResources.getString("PenColorCommand"));
        penSizeSubMenu = new Menu(myResources.getString("PenSizeCommand"));
        backgroundColorMenu = new Menu(myResources.getString("BackgroundColorCommand"));
        addColorOptions(colorSubMenu);
        addColorOptions(backgroundColorMenu);
        setSelectedBackgroundProperties();
        addPenSizeOptions();
        myMenu.getItems().addAll(colorSubMenu, penSizeSubMenu, backgroundColorMenu);
    }

    private void addColorOptions(Menu menu){ 
        menu.getItems().add(createMenuItem("RedPenColor"));
        menu.getItems().add(createMenuItem("BluePenColor"));
        menu.getItems().add(createMenuItem("PinkPenColor"));
        menu.getItems().add(createMenuItem("BlackPenColor"));
    }

    private void addPenSizeOptions(){
        penSizeSubMenu.getItems().addAll(createMenuItem("1"),createMenuItem("2"),createMenuItem("3"),createMenuItem("4"),createMenuItem("5"));
    }
    
    private void setSelectedBackgroundProperties(){
    	for(MenuItem m : backgroundColorMenu.getItems()){
    		m.setOnAction(e -> {
    			VBox pane = (VBox) myCanvas.getLeft();
    			Pane p = (Pane) pane.getChildren().get(0);
    			p.setStyle("-fx-background-color: " + m.getText().toLowerCase() + "; -fx-border-color: black; -fx-border-width:4px");
    			myCanvas.setLeft(pane);
    		});
    	}
    }
}
