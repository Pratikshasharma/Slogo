/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the third level of my hierarchy and basic functionality for Complex Commands that use local variables.
 * See CommandInterface for more complete description.
 * 
 */

package ComplexCommands;

import Command.VariableScopeCommand;

/**
 * Abstract class for Complex Commands that needs to store previous variable names to deal with scope/local variables.
 * Determines how those commands deal with multiple active actors (outsource elsewhere).
 * 
 * @author Vincent
 *
 */
public abstract class ComplexCommandScope extends VariableScopeCommand{}
