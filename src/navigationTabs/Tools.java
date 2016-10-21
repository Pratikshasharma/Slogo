package navigationTabs;

import gui.BackgroundChangeable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Tools extends MenuCreator {

	private Menu colorSubMenu;
	private Menu penSizeSubMenu;
	private Menu backgroundColorMenu;

	public Tools () {
		super("ToolsCommand");
		addItems();
	}

	@Override
	protected void addItems () {
		colorSubMenu = new Menu(myResources.getString("PenColorCommand"));
		penSizeSubMenu = new Menu(myResources.getString("PenSizeCommand"));
		backgroundColorMenu = new Menu(myResources.getString("BackgroundColorCommand"));
		addColorOptions(colorSubMenu);
		addColorOptions(backgroundColorMenu);
		//        setSelectedBackgroundProperties();
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

	public BackgroundChangeable getBackgroundChanger(MenuItem m){
		BackgroundChangeable backgroundChanger = (root) -> {
			VBox pane = (VBox) root.getLeft();
			Pane p = (Pane) pane.getChildren().get(0);
			p.setStyle("-fx-background-color: " + m.getText().toLowerCase() + "; -fx-border-color: black; -fx-border-width:4px");
			root.setLeft(pane);
		};
		return backgroundChanger;
	}

	public Menu getBackgroundColorMenu(){
		return backgroundColorMenu;
	}
}
