public class MapLoc{
    
    double up;
    double down;
    double right;
    double left;
    boolean relevant;
    
    public MapLoc(double u, double d, double l, double r, boolean rel){
        up=u;
        down=d;
        right=r;
        left=l;
        relevant=rel;
    }
    
    public double getUp(){
        return up;
    }
    
    public double getDown(){
        return down;
    }
    
    public double getRight(){
        return right;
    }
    
    public double getLeft(){
        return left;
    }
    
    public boolean isRelevant(){
        return relevant;
    }
    
}