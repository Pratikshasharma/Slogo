package navigationTabs;


public class Window extends MenuCreator {

    public Window () {
        super("WindowCommand");
    }
    @Override
    protected void addItems () {
        myMenu.getItems().add(createMenuItem("NewWindowCommand"));
    }
}





