package Simulation;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import Actors.Actor;
import Simulation.Node.InfoNode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CommandExecute {
    private static final String COMMAND_INPUTS_FILE = "resources/CommandInputs";
    private static final String ERROR_TITLE="Back End: Command Execution Error";
    private static final String ERROR_COMMAND_EXECUTE_START="Error in starting command execution class.";
    private static final String[] LOOPS_AND_CONDITIONALS=new String[]{"Repeat","DoTimes","For","If","IfElse"};

    Object myCommandArchive;
    private ResourceBundle myResources;
    private Map<String,Double> Variables;
    private Actor myActor;
    /*
     * one parameter: left
     * two parameters: left, right
     * three parameters: left, middle, right
     */
    
    public CommandExecute (Actor actor) {
        myActor=actor;
        myResources= ResourceBundle.getBundle(COMMAND_INPUTS_FILE);
        Variables=new HashMap<String,Double>();
        try {
            Class cls = Class.forName("Simulation.CommandArchive");
            Constructor cons = cls.getConstructor(Actor.class);
            myCommandArchive=cons.newInstance(myActor);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            showError(ERROR_COMMAND_EXECUTE_START);
        }
    }
    
    public List<Double> executeCommands(InfoNode myNode){
        List<Double> returnVals=new ArrayList<Double>();
        while(myNode!=null){
            returnVals.add(execute(myNode));
            System.out.println(returnVals.get(returnVals.size()-1));
            myNode=myNode.next();
        }
        System.out.println(returnVals.size());
        return returnVals;
    }
    
    private double execute(InfoNode myNode){
        String type=myNode.getToken();
        switch(type){
            case ("Constant"):
                return Double.parseDouble(myNode.getName());
            case ("Variable"):
                return Variables.get(myNode.getName());
            case ("Command"):
                executeCommand(myNode);
            default:
                return (Double) null;
        }
    }
    
    private double executeCommand(InfoNode myNode){
        try {
            if(Arrays.asList(LOOPS_AND_CONDITIONALS).contains(myNode.getName())){
                Class cls2 = Class.forName("Simulation.CommandArchive");
                Constructor cons2 = cls2.getConstructor(Actor.class);
                Object myComEx=cons2.newInstance(myActor);
                Method myMethod= myComEx.getClass().getMethod(myNode.getName());
                switch(myMethod.getParameterCount()){
                    case 0:
                        return (double) myMethod.invoke(myComEx);
                    case 1:
                        return (double) myMethod.invoke(myComEx,myNode.left());
                    case 2:
                        return (double) myMethod.invoke(myComEx,myNode.left(),myNode.right());
                    case 3:
                        return (double) myMethod.invoke(myComEx,myNode.left(),myNode.middle(),myNode.right());
                }              
            }
            else{
                Method myMethod= myCommandArchive.getClass().getMethod(myNode.getName());
                switch(myMethod.getParameterCount()){
                    case 0:
                        return (double) myMethod.invoke(myCommandArchive);
                    case 1:
                        return (double) myMethod.invoke(myCommandArchive,execute(myNode.left()));
                    case 2:
                        return (double) myMethod.invoke(myCommandArchive,execute(myNode.left()),execute(myNode.right()));
                    case 3:
                        return (double) myMethod.invoke(myCommandArchive, execute(myNode.left()),execute(myNode.middle()),execute(myNode.right()));
                }
            }
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
            // TODO Auto-generated catch block
            showError("Error in executing commmand: " + myNode.getName());
        }
        return (Double) null;
    }
    
    /*
     * LOOPS AND CONDITIONALS
     */
    
    //left side will always be single expression, right side will always be list of commands.
    private double Repeat(InfoNode conditional, InfoNode commands){
        InfoNode startNode=commands;
        double lastresult=0;
        int limit=(int) execute(conditional);
        for(int i=1;i<=limit;i++){
            Variables.put(":repcount", (double) i);
            List<Double> results=executeCommands(commands);
           // Variables.put(":repcount", (double) i);//reset in case changed by nested loops, not sure if needed         
            lastresult=results.get(results.size()-1);         
        }
        return lastresult;
    }
    
    private double DoTimes(InfoNode conditional, InfoNode commands){
        InfoNode startNode=commands;
        double lastresult=0;
        int limit=(int) execute(conditional.next());
        String var=conditional.getName();
        for(int i=0;i<=limit;i++){
            Variables.put(var, (double) i);
            List<Double> results=executeCommands(commands);
           // Variables.put(var, (double) i);//reset in case changed by nested loops, not sure if needed         
            lastresult=results.get(results.size()-1); 
        }     
        return lastresult;
    }
    
    private double For(InfoNode conditional, InfoNode commands){
        InfoNode startNode=commands;
        double lastresult=0;
        String var=conditional.getName();
        int start=(int) execute(conditional.next());
        int end=(int) execute(conditional.next().next());
        int increment=(int) execute(conditional.next().next().next());
        for(int i=start;i<=end;i+=increment){
            Variables.put(var, (double) i);
            List<Double> results=executeCommands(commands);
           // Variables.put(var, (double) i);//reset in case changed by nested loops, not sure if needed         
            lastresult=results.get(results.size()-1); 
        }     
        return lastresult;
    }
    
    //left side will always be single expression, right side will always be list of commands.
    private double If(InfoNode conditional, InfoNode commands){
        double lastresult=0;
        if((int)execute(conditional)!=0){
            List<Double> results=executeCommands(commands);
            lastresult=results.get(results.size()-1); 
        }
        return lastresult;
    }
    
    //left side will always be single expression, right side will always be list of commands.
    private double IfElse(InfoNode conditional, InfoNode commandsIf, InfoNode commandsElse){
        double lastresult=0;
        if((int)execute(conditional)!=0){
            List<Double> results=executeCommands(commandsIf);
            lastresult=results.get(results.size()-1); 
        }
        else{
            List<Double> results=executeCommands(commandsElse);
            lastresult=results.get(results.size()-1); 
        }
        return lastresult;
    }
    
    
    
    // causes error to display in place of stacktrace message when exceptiosn are reached
    private void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setContentText(myResources.getString(message));
        alert.showAndWait();
    }

}
