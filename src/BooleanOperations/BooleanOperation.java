/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the third level of my hierarchy and basic functionality for Boolean Operations (all use error checking).
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
public abstract class BooleanOperation extends BasicCommand{
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
