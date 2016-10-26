package Commands;

import java.util.List;
import java.util.Map;
import Actors.Actor;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public interface Command {
    public double call(CommandStorage myCommandStorage, List<Integer> ActorsChanged,List<InfoNode> args);
    public double execute(CommandStorage myCommandStorage, List<Integer> ActorsChanged,List<InfoNode> args);
}
