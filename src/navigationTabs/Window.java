package navigationTabs;


public class Window extends MenuTemplate {

    public Window () {
        super("WindowCommand");
    }
    @Override
    protected void addItems () {
        myMenu.getItems().add(createMenuItem("NewWindowCommand"));
    }
}





