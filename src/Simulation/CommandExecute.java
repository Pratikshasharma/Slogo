package Simulation;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import Actors.Actor;
import Simulation.Node.InfoNode;

public class CommandExecute {
    private static final String COMMAND_INPUTS_FILE = "CommandInputs";

    Object myCommandArchive;
    private ResourceBundle myResources;
    /*
     * one parameter: left
     * two parameters: left, middle
     * three parameters: left, middle, right
     */
    
    public CommandExecute (Actor actor) {
        myResources= ResourceBundle.getBundle(COMMAND_INPUTS_FILE);
        try {
            Class cls = Class.forName("Simulation.CommandArchive");
            Constructor cons = cls.getConstructor(Actor.class);
            myCommandArchive=cons.newInstance(actor);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public double execute(InfoNode myNode){
        String type=myNode.getToken();
        switch(type){
            case ("Constant"):
                return Double.parseDouble(myNode.getToken());
            case ("Command"):
                executeCommand(myNode);
            default:
                return (Double) null;
        }
    }
    
    public double executeCommand(InfoNode myNode){
        try {
            Method myMethod= myCommandArchive.getClass().getMethod(myNode.getName());
            switch(myMethod.getParameterCount()){
                case 0:
                    return (double) myMethod.invoke(myCommandArchive);
                case 1:
                    return myMethod.invoke(myCommandArchive,execute(myNode.left()));
                case 2:
                    return myMethod.invoke(myCommandArchive,execute(myNode.left()),execute(myNode.right()));
                case 3:
                    return myMethod.invoke(myCommandArchive, execute(myNode.left()),execute(myNode.right()),execute(myNode.middle()));
            }
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
