package Simulation;

public class BooleanOperations {
    public double lessThan(double a, double b){
        return (a<b)?1:0;
    }
    
    public double greaterThan(double a, double b){
        return (a>b)?1:0;
    }
    
    public double equal(double a, double b){
        return (a==b)?1:0;
    }
    
    public double notEqual(double a, double b){
        return (a!=b)?1:0;
    }
    
    public double and(double a, double b){
        return (a>0 && b>0)?1:0;
    }
    
    public double or(double a, double b){
        return (a>0 || b>0)?1:0;
    }
    
    public double not(double a){
        return (a==0)?1:0;
    }
}
