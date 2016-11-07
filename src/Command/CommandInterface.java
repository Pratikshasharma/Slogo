/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * 
 * The masterpiece I am submitting is the hierarchy of my commands. 
 * I have included the top command interface and the 2nd and 3rd level of abstract classes for the sake of submitting only a little code, but the actual commands that use these levels are implicit.
 * 
 * 
 * 
 * The top level is this CommandInterface which sets what every command needs (how it executes, and what it will execute on when run)
 * 
 * The 2nd level of abstract classes consist of the rest of the classes in this package.
 * These classes set up the general repetitive structures (what execute on) that we see often to provided a base of reusability as well as remove some duplication of code.
 * These should be useful in many cases which is why they are kept in this package and at this level to be resued across command types.
 * 
 * The 3rd level of abstract classes is more specific and primarily deals with error checking.
 * The way we error check/what we want to error check is more specific per command type, which is why these commands are kept in their separate packages by command type.
 * 
 * Last are the base command classes. These are the classes that have the actual function of the commands.
 * They utilize the methods inherited to deal with error checking, multiple actors, etc.
 * 
 * 
 * 
 * I believe this design is good as it demonstrates a good usage of hierarchy and inheritance as needed.
 * Only the necessary methods and functionality is passed onto the classes through extending the proper abstract classes.
 * This also helps a lot with duplicated code since I was able to place a lot of the reused code higher up in the hierarchy.
 * 
 * Note that there are some tradeoffs:
 * 
 * Some of the 3rd level of abstract classes are empty, which might be considered unecessary.
 * This decision was made deliberately to keep the base levels the same. 
 * This also helps with readability by providing an easy to follow hierarchy with distinct level of usable classes.
 * 
 * There is also some duplicate code I left in in the base commands. The reasoning for this is I wanted this to be the "base level" for easy reading through the hierarchy and reusability/flexibility.
 * I wanted them to be able to work as standalone commands without having to call other classes which is why there is some similar code (ie left and right are similar).
 * As a result I decided not to refactor code that specifically affect execution as opposed to an extra feature (ie scope or multiple actors).
 * 
 */

package Command;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * The general command interface. Every command has to figure out how to deal with multiple actors (eg ignore, loop through) and how to execute over multiple inputs.
 * 
 * @author Vincent
 *
 */
public interface CommandInterface {
    /**
     * Take care of repetition of function (what it executes on)
     * @param myCommandStorage
     * @param ActorsChanged
     * @param args
     * @return
     */
    public double call(CommandStorage myCommandStorage, List<InfoNode> args);
    /**
     * Take care of actual function (how it executes)
     * @param myCommandStorage
     * @param ActorsChanged
     * @param args
     * @return
     */
    public double execute(CommandStorage myCommandStorage, List<InfoNode> args);
}
