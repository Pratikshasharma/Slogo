package navigationTabs;

/**
 * @author pratikshasharma
 *Dependencies: MenuTemplate super class
 *Assumptions: addItems() abstract method
 */
public class Language extends MenuTemplate {
    public Language (){
        super("LanguageCommand");
        addItems();
    }

    @Override
    public void addItems() {
        myMenu.getItems().addAll(createMenuItem("ChineseProperty"),createMenuItem("EnglishProperty"),createMenuItem("FrenchProperty"),createMenuItem("SpanishProperty"),createMenuItem("SyntaxProperty"));
        myMenu.getItems().addAll(createMenuItem("GermanProperty"),createMenuItem("ItalianProperty"),createMenuItem("PortugueseProperty"),createMenuItem("RussianProperty"));                  
    }
}
