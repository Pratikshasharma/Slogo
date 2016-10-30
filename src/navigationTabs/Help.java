package navigationTabs;

import commandreference.HTMLReferencePage;
import javafx.scene.control.MenuItem;

public class Help extends MenuCreator {

	public Help () {
		super("HelpCommand");
		
	}

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


