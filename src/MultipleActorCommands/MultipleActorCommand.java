/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the third level of my hierarchy and basic functionality for MultipleActorCommands.
 * See CommandInterface for more complete description.
 * 
 */

package MultipleActorCommands;

import Command.BasicCommand;

/**
 * Abstract class for Multiple Actor commands.
 * Determines how those commands deal with multiple active actors (execute once since will see how execute on all after).
 * 
 * @author Vincent
 *
 */
public abstract class MultipleActorCommand extends BasicCommand{}
