/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the third level of my hierarchy and basic functionality for DisplayCommands.
 * See CommandInterface for more complete description.
 * 
 */

package DisplayCommands;

import Command.BasicCommand;

/**
 * Abstract class for display commands.
 * Determines how those commands deal with multiple active actors (should only execute once- doesnt involve actors).
 * 
 * @author Vincent
 *
 */
public abstract class DisplayCommand extends BasicCommand{}
