package Simulation;

import Actors.Actor;

/**
 * In the interest of having a general database of all the commands and where they come from.
 * Also allows easy access from reflection as all commands in same place and refer to one actor.
 * 
 * @author Vincent
 *
 */
public class CommandArchive {
    private ActorCommands changeActor;
    private ActorQueries askActor;
    private BooleanOperations booleans;
    private MathOperations math;    
    private Actor myActor;

    
    public CommandArchive (Actor actor) {
        myActor=actor;
        changeActor = new ActorCommands();
        askActor=new ActorQueries();
        booleans=new BooleanOperations();
        math=new MathOperations();
    }
    
    /*
     * ACTOR COMMANDS
     */
    public double Forward(double pixels){
        return changeActor.Forward(myActor, pixels);
    }
    
    public double Backward(double pixels){
        return changeActor.Backward(myActor, pixels);
    }   
    
    public double Left(double angle){
        return changeActor.Left(myActor, angle);
    }
    
    public double Right(double angle){
        return changeActor.Right(myActor, angle);
    }
    
    public double SetHeading(double angle){
        return changeActor.SetHeading(myActor, angle);
    }
    
    public double SetTowards(double x, double y){
        return changeActor.SetTowards(myActor, x, y);
    } 
    
    public double SetPosition(int x, int y){
        return changeActor.SetPosition(myActor, x, y);
    }     
    
    public double PenDown(){
        return changeActor.PenDown(myActor);
    }
    
    public double PenUp(){
        return changeActor.PenUp(myActor);
    }
    
    public double ShowTurtle(){
        return changeActor.ShowTurtle(myActor);
    }
    
    public double HideTurtle(){
        return changeActor.HideTurtle(myActor);
    }
    
    public double Home(){
        return changeActor.Home(myActor);
    }
    
    public double ClearScreen(){
        //set to clear screen in front end
        return changeActor.ClearScreen(myActor);
    }
    
    /*
     * ACTOR QUERIES
     */
    
    public double XCoordinate(){
        return askActor.XCoordinate(myActor);
    }
    
    public double YCoordinate(){
        return askActor.YCoordinate(myActor);
    }
    
    public double Heading(){
        return askActor.Heading(myActor);
    }
    
    public double IsPenDown(){
        return askActor.IsPenDown(myActor);
    }
    
    public double IsShowing(){
        return askActor.IsShowing(myActor);
    }
    
    /*
     * MATH OPERATIONS
     */    
    public double Sum(double a, double b){
        return math.Sum(a, b);
    }
    
    public double Difference(double a, double b){
        return math.Difference(a, b);
    }
    
    public double Product(double a, double b){
        return math.Product(a, b);
    }
    
    public double Quotient(double a, double b){
        return math.Quotient(a, b);
    }
    
    public double Remainder(double a, double b){
        return math.Remainder(a, b);
    }
    
    public double Minus(double a){
        return math.Minus(a);
    }
    
    public double Random(double max){
        return math.Random(max);
    }
    
    public double Sine(double angle){
        return math.Sine(angle);
    }
    
    public double Cosine(double angle){
        return math.Cosine(angle);
    }
    
    public double Tangent(double angle){
        return math.Tangent(angle);
    }
    
    public double ArcTangent(double angle){
        return math.ArcTangent(angle);
    }
    
    public double NaturalLog(double a){
        return math.NaturalLog(a);
    }
    
    public double Power(double base, double exp){
        return math.Power(base, exp);
    }
    
    public double Pi(){
        return math.Pi();
    }
    
    /*
     * BOOLEAN OPERATIONS
     */
    
    public double LessThan(double a, double b){
        return booleans.LessThan(a, b);
    }
    
    public double GreaterThan(double a, double b){
        return booleans.GreaterThan(a, b);
    }
    
    public double Equal(double a, double b){
        return booleans.Equal(a, b);
    }
    
    public double NotEqual(double a, double b){
        return booleans.NotEqual(a, b);
    }
    
    public double And(double a, double b){
        return booleans.And(a, b);
    }
    
    public double Or(double a, double b){
        return booleans.Or(a, b);
    }
    
    public double Not(double a){
        return booleans.Not(a);
    }
}
