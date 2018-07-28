public class PastMove{
    
    int xloc;
    int yloc;
    int move;
    
    public PastMove(int x,int y,int m){
        xloc=x;
        yloc=y;
        move=m;
    }
    
    public int getx(){
        return xloc;
    }
    
    public int gety(){
        return yloc;
    }
    
    public int getmove(){
        return move;
    }
    
    
}