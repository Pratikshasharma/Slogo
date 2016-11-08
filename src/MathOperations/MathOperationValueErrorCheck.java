/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the third level of my hierarchy and functionality for MathOperations that use value error checking.
 * See CommandInterface for more complete description.
 * 
 */

package MathOperations;

import Command.BasicCommand;

/**
 * Abstract class for Math Operations that need to error check result values.
 * Determines how those commands deal with multiple active actors (should only execute once- doesnt involve actors).
 * Deals with error checking for setting values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class MathOperationValueErrorCheck extends BasicCommand{
    /**
     * Error checking for math values.
     * 
     * @param val
     * @param exception
     * @return
     */
    public double mathValErrorCheck(double val, double exception){
        if(val==exception){
            return Double.NaN;
        }
        return val;
    }
}
