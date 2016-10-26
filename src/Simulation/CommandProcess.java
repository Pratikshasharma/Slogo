package Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Simulation.Node.InfoNode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ActorCommands.Forward;
import Command.ActorCommand;
import Command.Command;
import java.lang.reflect.InvocationTargetException;


public class CommandProcess {
    private static final String ERROR_TITLE="Back End: Command Execution Error";
    private static final String PACKAGE_NAME_FILE="resources/CommandPackageName";
    private ResourceBundle myResources;
    
    public CommandProcess(){
        myResources = ResourceBundle.getBundle(PACKAGE_NAME_FILE);
    }

    
    public double executeList(CommandStorage myCommandStorage, List<Integer> ActorsChanged,InfoNode myNode){
        double result=Double.NaN;
        while(myNode!=null){
            result=chooseProcess(myCommandStorage, ActorsChanged, myNode);
            myNode=myNode.next();
        }
        return result;
    }
    
    private double chooseProcess(CommandStorage myCommandStorage, List<Integer> ActorsChanged,InfoNode myNode){
        String type=myNode.getToken();
        switch(type){
            case ("Constant"):
                return Double.parseDouble(myNode.getName());
            case ("Variable"):
                return myCommandStorage.getVariable(myNode.getName());
            default:
                return executeCommand(myCommandStorage,ActorsChanged,myNode);
        }
    }
    
    private double executeCommand(CommandStorage myCommandStorage, List<Integer> ActorsChanged,InfoNode myNode){
        Class cls;
        Command clsInstance;
        try {
            cls = Class.forName(myResources.getString(myNode.getToken()) + myNode.getToken());
            clsInstance = (Command) cls.newInstance();
   //         return clsInstance.call(myCommandStorage, ActorsChanged, myNode.getParameters());
            List<InfoNode> myList=new ArrayList<InfoNode>();
            myList.add(myNode.left());
            return clsInstance.call(myCommandStorage, ActorsChanged, myList);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
//            showError(myNode.getName());
        }
        return Double.NaN;
    }
    
    // causes error to display in place of stacktrace message when exceptiosn are reached
    private void showError (String commandName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setContentText("Error in executing commmand: " + commandName);
        alert.showAndWait();
    }
}
