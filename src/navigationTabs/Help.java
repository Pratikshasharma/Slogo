package navigationTabs;
import commandreference.HTMLReferencePage;
import javafx.scene.control.MenuItem;

/**
 * 
 * @author pratikshasharma
 * Dependencies: MenuTemplate Super Class and MenuLayout Interface
 */

public class Help extends MenuTemplate {
	public Help () {
	super("HelpCommand");	
	}

	/**
	 * adds items in the menu 
	 */
	@Override
	protected void addItems () {
		MenuItem displayCommandReference = new MenuItem("CommandReferencesCommand");
		displayCommandReference.setOnAction(e -> openHTMLReference());
		myMenu.getItems().add(displayCommandReference);
	}

	private void openHTMLReference(){
		HTMLReferencePage page = new HTMLReferencePage();
		page.getPage();
	}

}


