/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the third level of my hierarchy and functionality for ActorCommands that use error checking for setting values.
 * See CommandInterface for more complete description.
 * 
 */

package ActorCommands;

import java.util.List;
import Command.ExecuteMultipleActorsCommand;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
    
/**
 * Abstract class for Actor commands that need to check values set.
 * Determines how those commands deal with multiple active actors (execute on all).
 * Deals with error checking for setting values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class ActorCommandSetValueErrorCheck extends ExecuteMultipleActorsCommand{
    protected static final double FULL_CIRCLE_DEGREES=360;
    protected static final double HALF_CIRCLE_DEGREES=360;
    protected static final double TO_RADIANS=Math.PI/180;
    protected static final String LEFTOVER_TYPE="Constant";
    
    /**
     * Error checkign for setting values
     * 
     * @param result
     * @param original
     * @return
     */
    public double setValErrorCheck(double result,double original){
        if(result==Double.NaN){
            return original;
        }
        return result;
    }
}
