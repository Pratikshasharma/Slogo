package ActorCommands;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

public class Forward extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        double x=0,y=0,pixels=0;
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        for(InfoNode parameter:args){
            double segmentedpixels=myCommandProcess.executeList(myCommandStorage, parameter);
            pixels+=segmentedpixels;
            x+=Math.cos(actor.getAngle()*Math.PI/180)*segmentedpixels;
            y+=Math.sin(actor.getAngle()*Math.PI/180)*segmentedpixels;


            if(x+actor.getX().get()<0){
                x=0-actor.getX().get();
                y=Math.tan(actor.getAngle()*Math.PI/180)*(x);
            }
            if(x+actor.getX().get()>MainGUI.TURTLE_PANE_WIDTH){
                x=MainGUI.TURTLE_PANE_WIDTH-actor.getX().get();
                y=Math.tan(actor.getAngle()*Math.PI/180)*(x);
            }
            if(y+actor.getY().get()<0){
                y=0-actor.getY().get();
                x=y/Math.tan(actor.getAngle()*Math.PI/180);
            }
            if(y+actor.getY().get()>MainGUI.TURTLE_PANE_HEIGHT){
                y=MainGUI.TURTLE_PANE_HEIGHT-actor.getY().get();
                x=y/Math.tan(actor.getAngle()*Math.PI/180);
            }
            if(x==MainGUI.TURTLE_PANE_WIDTH-actor.getX().get()|| x==-actor.getX().get()){
                actor.setAngle(180-actor.getAngle());
            }
            if(y==MainGUI.TURTLE_PANE_HEIGHT-actor.getY().get() || y==-actor.getY().get()){
                actor.setAngle(360-actor.getAngle());
            }
            
            actor.setPos(setValErrorCheck(actor.getX().get()+x,actor.getX().get()),setValErrorCheck(actor.getY().get()+y,actor.getY().get()));

            //deal with fact that wont  get exact 0
            if(segmentedpixels-actor.getDistance()>1){
                Forward leftoverforward=new Forward();
                double leftover=segmentedpixels-actor.getDistance();
                List<InfoNode> leftoverargs=new ArrayList<InfoNode>();
                leftoverargs.add(new InfoNode(Double.toString(leftover),"Constant"));
                leftoverforward.call(myCommandStorage, leftoverargs);
            }
            
        }
        return pixels;
    }

}
