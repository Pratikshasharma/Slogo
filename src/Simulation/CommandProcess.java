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
import ComplexCommands.CustomCommand;
import java.lang.reflect.InvocationTargetException;


/**
 * This is the main class of the execution process that moves through the tree of Nodes.
 * It returns constants and variables and calls the appropriate commands when needed.
 * 
 * Called by SimulationController, other command packages.
 * Calls the other command packages through reflection, ExecutionException.
 * 
 * @author Vincent
 *
 */
public class CommandProcess {
    private static final String ERROR_TITLE="Back End: Command Execution Error";
    private static final String PACKAGE_NAME_FILE="resources/CommandPackageName";
    private ResourceBundle myResources;
    
    /**
     * Constructor
     */
    public CommandProcess(){
        myResources = ResourceBundle.getBundle(PACKAGE_NAME_FILE);
    }
    
    /**
     * The main execution method called on every node. 
     * Main purpose is to make sure is to iterate through the tree properly until completed.
     * Also deals with errors by catching incorrect processes as well as invalidating other commands that follow.
     * 
     * @param myCommandStorage
     * @param myNode
     * @return
     */
    public double executeList(CommandStorage myCommandStorage, InfoNode myNode){
        double result=Double.NaN;
        String lastcommandname = "No command run";
        //only run if no errors previously in same execution
            try{
                while(myNode!=null && !myCommandStorage.getKillCommands()){
                    lastcommandname=myNode.getName();
                    //System.out.println(lastcommandname);
                    result=chooseProcess(myCommandStorage, myNode);
                    if(result==Double.NaN){
                        myCommandStorage.setKillCommands(true);
                        throw new ExecutionException();
                    }
                    myNode=myNode.next();
                }
            }
            catch(ExecutionException e){
                showError(lastcommandname);
            }
        return result;
    }
    
    
    /**
     * Differentiates between the type of nodes and executes accordingly.
     * 
     * @param myCommandStorage
     * @param myNode
     * @return
     */
    private double chooseProcess(CommandStorage myCommandStorage,InfoNode myNode){
        String type=myNode.getToken();
        switch(type){
            case ("Constant"):
                return Double.parseDouble(myNode.getName());
            case ("Variable"):
                return myCommandStorage.getVariable(myNode.getName());
            case("Command"):
                return executeCustomCommand(myCommandStorage,myNode);
            default:
                return executeCommand(myCommandStorage,myNode);
        }
    }
    
    /**
     * Executes commands that are provided in the original list of commands.
     * 
     * @param myCommandStorage
     * @param myNode
     * @return
     */
    private double executeCommand(CommandStorage myCommandStorage, InfoNode myNode){
        Class cls;
        Command clsInstance;
        try {
            cls = Class.forName(myResources.getString(myNode.getToken()) + myNode.getToken());
            clsInstance = (Command) cls.newInstance();
            return clsInstance.call(myCommandStorage, myNode.getParameters());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            showError(myNode.getName());
        }
        //if error, will just return Double.NaN,set up error change up till top level (that way you can kind of see where error is by following path)
        //only thing is need to make sure for all the set value commands, dont set them to Double.Nan
        //also will kill all commands after
        return Double.NaN;
    }
    
    /**
     * Executes custom commands that are created by the user.
     * 
     * @param myCommandStorage
     * @param myNode
     * @return
     */
    private double executeCustomCommand(CommandStorage myCommandStorage, InfoNode myNode){
        CustomCommand myCustomCommand=new CustomCommand();
        myNode.getParameters().add(0,myNode);//so can get name
        return myCustomCommand.call(myCommandStorage, myNode.getParameters());
    }
    
    // causes error to display in place of stacktrace message when exceptiosn are reached
    private void showError (String commandName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setContentText("Error in executing commmand: " + commandName);
        alert.showAndWait();
    }
}
