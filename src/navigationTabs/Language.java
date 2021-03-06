package navigationTabs;

import javafx.scene.control.Menu;

public class Language extends MenuCreator {
    public Language () {
        super("LanguageCommand");
        addItems();
    }

    @Override
    protected void addItems () {
        myMenu.getItems().addAll(createMenuItem("ChineseProperty"),createMenuItem("EnglishProperty"),createMenuItem("FrenchProperty"),createMenuItem("SpanishProperty"),createMenuItem("SyntaxProperty"));
        myMenu.getItems().addAll(createMenuItem("GermanProperty"),createMenuItem("ItalianProperty"),createMenuItem("PortugueseProperty"),createMenuItem("RussianProperty"));                  
    }
    
    public Menu getLanguageMenu(){
        return this.myMenu;
    }
    
}
