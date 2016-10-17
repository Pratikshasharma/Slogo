package Simulation;

public class MathOperations {
    public double sum(double a, double b){
        return a+b;
    }
    
    public double difference(double a, double b){
        return a-b;
    }
    
    public double product(double a, double b){
        return a*b;
    }
    
    public double quotient(double a, double b){
        return a/b;
    }
    
    public double remainder(double a, double b){
        return a%b;
    }
    
    public double minus(double a){
        return -a;
    }
    
    public double random(double max){
        return Math.random()*max;
    }
    
    public double sin(double angle){
        return Math.sin(angle);
    }
    
    public double cos(double angle){
        return Math.cos(angle);
    }
    
    public double tan(double angle){
        return Math.tan(angle);
    }
    
    public double atan(double angle){
        return Math.atan(angle);
    }
    
    public double log(double a){
        return Math.log(a);
    }
    
    public double pow(double base, double exp){
        return Math.pow(base, exp);
    }
    
    public double pi(){
        return Math.PI;
    }
}
