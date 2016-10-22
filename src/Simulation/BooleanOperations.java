package Simulation;

public class BooleanOperations {
    public double lessThan(double a, double b){
        return booleantodouble(a<b);
    }
    
    public double greaterThan(double a, double b){
        return booleantodouble(a>b);
    }
    
    public double equal(double a, double b){
        return booleantodouble(a==b);
    }
    
    public double notEqual(double a, double b){
        return booleantodouble(a!=b);
    }
    
    public double and(double a, double b){
        return booleantodouble(a>0 && b>0);
    }
    
    public double or(double a, double b){
        return booleantodouble(a>0 || b>0);
    }
    
    public double not(double a){
        return booleantodouble(a==0);
    }
    
    private double booleantodouble(boolean bool){
    	return bool ? 1 : 0;
    }
}
