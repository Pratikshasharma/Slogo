/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the third level of my hierarchy and basic functionality for ActorQueries.
 * See CommandInterface for more complete description.
 * 
 */

package ActorQueries;

import Command.BasicCommand;

/**
 * Abstract class for Actor commands.
 * Determines how those commands deal with multiple active actors (shouldn't need to repeat a bunch for actorqueries-want to return only one number still).
 * 
 * 
 * @author Vincent
 *
 */
public abstract class ActorQuery extends BasicCommand{}
