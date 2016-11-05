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
public interface Command {
    /**
     * Take care of running multiple actors
     * @param myCommandStorage
     * @param ActorsChanged
     * @param args
     * @return
     */
    public double call(CommandStorage myCommandStorage, List<InfoNode> args);
    /**
     * Take care of running multiple inputs
     * @param myCommandStorage
     * @param ActorsChanged
     * @param args
     * @return
     */
    public double execute(CommandStorage myCommandStorage, List<InfoNode> args);
}
