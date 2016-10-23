package navigationTabs;

import javafx.scene.control.Menu;


public class Tools extends MenuCreator {

	private Menu colorSubMenu;
	private Menu penSizeSubMenu;
	private Menu backgroundColorMenu;

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

	
	public Menu getBackgroundColorMenu(){
		return backgroundColorMenu;
	}
	
	public Menu getPenSizeSubMenu(){
	    return this.penSizeSubMenu;
	}
	
	public Menu getPenColorSubMenu(){
	    return this.colorSubMenu;
	}
	
}
