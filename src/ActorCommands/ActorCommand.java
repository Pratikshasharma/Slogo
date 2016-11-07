/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the third level of my hierarchy and basic functionality for ActorCommands.
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
 * Abstract class for Actor commands.
 * Determines how those commands deal with multiple active actors (execute on all).
 * 
 * @author Vincent
 *
 */
public abstract class ActorCommand extends ExecuteMultipleActorsCommand{}
