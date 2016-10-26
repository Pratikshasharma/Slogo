package ThingsToRemoveLater;

public class MathOperations {
    public double Sum(double a, double b){
        return a+b;
    }
    
    public double Difference(double a, double b){
        return a-b;
    }
    
    public double Product(double a, double b){
        return a*b;
    }
    
    public double Quotient(double a, double b){
        return a/b;
    }
    
    public double Remainder(double a, double b){
        return a%b;
    }
    
    public double Minus(double a){
        return -a;
    }
    
    public double Random(double max){
        return Math.random()*max;
    }
    
    public double Sine(double angle){
        return Math.sin(angle);
    }
    
    public double Cosine(double angle){
        return Math.cos(angle);
    }
    
    public double Tangent(double angle){
        return Math.tan(angle);
    }
    
    public double ArcTangent(double angle){
        return Math.atan(angle);
    }
    
    public double NaturalLog(double a){
        return Math.log(a);
    }
    
    public double Power(double base, double exp){
        return Math.pow(base, exp);
    }
    
    public double Pi(){
        return Math.PI;
    }
}
