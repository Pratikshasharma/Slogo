/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the third level of my hierarchy and compare checking functionality for Boolean Operations.
 * See CommandInterface for more complete description.
 * 
 */

package BooleanOperations;

import java.util.List;
import Command.BasicCommand;
import Command.ExecuteHelperCommnd;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for Boolean operations.
 * Determines how those commands deal with multiple active actors (should only need to execute once- doesn't involve actors).
 * Deals with error checking for checking values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class BooleanOperationCompare extends ExecuteHelperCommnd{    
    /* (non-Javadoc)
     * @see Command.ExecuteHelperCommnd#executeHelper(Simulation.CommandStorage, java.util.List)
     */
    protected double executeHelper(CommandStorage myCommandStorage, List<InfoNode> args){
        double a=myCommandProcess.executeList(myCommandStorage,args.get(0));
        double b=myCommandProcess.executeList(myCommandStorage, args.get(1));
        if(boolErrorCheck(a) && boolErrorCheck(b)){
            return Double.NaN;
        }
        return execute(myCommandStorage,args);
    }

    /**
     * Error checking for boolean values to see if valid values
     * 
     * 
     * @param result
     * @return
     */
    public boolean boolErrorCheck(double result){
        if(result==Double.NaN){
            return true;
        }
        return false;
    }
}
