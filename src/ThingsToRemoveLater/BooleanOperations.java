package ThingsToRemoveLater;

public class BooleanOperations {
    public double LessThan(double a, double b){
        return (a<b)?1:0;
    }
    
    public double GreaterThan(double a, double b){
        return (a>b)?1:0;
    }
    
    public double Equal(double a, double b){
        return (a==b)?1:0;
    }
    
    public double NotEqual(double a, double b){
        return (a!=b)?1:0;
    }
    
    public double And(double a, double b){
        return (a>0 && b>0)?1:0;
    }
    
    public double Or(double a, double b){
        return (a>0 || b>0)?1:0;
    }
    
    public double Not(double a){
        return (a==0)?1:0;
    }
}
