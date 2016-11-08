/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the third level of my hierarchy and provides bounds error checking for DisplayCommands.
 * See CommandInterface for more complete description.
 * 
 */

package DisplayCommands;

import Command.BasicCommand;

/**
 * Abstract class for display commands that need bounds checks.
 * Determines how those commands deal with multiple active actors (should only execute once- doesnt involve actors).
 * 
 * @author Vincent
 *
 */
public abstract class DisplayCommandBoundsCheck extends BasicCommand{
    /**
     * Error checking for bounds values
     * 
     * @param val
     * @param lower
     * @param upper
     * @return
     */
    public boolean boundsErrorCheck(double val,double lower,double upper){
        return(val<=upper && val>=lower);
    }
}
