/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the third level of my hierarchy and basic functionality for MathOperations.
 * See CommandInterface for more complete description.
 * 
 */

package MathOperations;

import Command.BasicCommand;

/**
 * Abstract class for Math Operations.
 * Determines how those commands deal with multiple active actors (should only execute once- doesnt involve actors).
 * Deals with error checking for setting values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class MathOperation extends BasicCommand{
    protected static final double TO_RADIANS=Math.PI/180;
}