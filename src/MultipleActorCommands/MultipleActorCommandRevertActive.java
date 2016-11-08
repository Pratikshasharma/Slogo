/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the third level of my hierarchy and functionality for MultipleActorCommands that revert the active actors when done running.
 * See CommandInterface for more complete description.
 * 
 */

package MultipleActorCommands;

import Command.RevertActiveCommand;

/**
 * Abstract class for Multiple Actor commands that reset the active actors.
 * Determines how those commands deal with multiple active actors (execute once since will see how execute on all after).
 * 
 * @author Vincent
 *
 */
public abstract class MultipleActorCommandRevertActive extends RevertActiveCommand{}
