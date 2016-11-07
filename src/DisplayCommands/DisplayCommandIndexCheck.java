/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the third level of my hierarchy and provides index error checking for DisplayCommands.
 * See CommandInterface for more complete description.
 * 
 */

package DisplayCommands;

import java.util.Set;
import Command.BasicCommand;

/**
 * Abstract class for display commands that need index checks.
 * Determines how those commands deal with multiple active actors (should only execute once- doesnt involve actors).
 * 
 * @author Vincent
 *
 */
public abstract class DisplayCommandIndexCheck extends BasicCommand{
    /**
     * Error checking for index values.
     * 
     * @param val
     * @param indexvals
     * @return
     */
    public boolean indexErrorCheck(double val,Set<Integer> indexvals){
        return(indexvals.contains((int)val));
    }
}
