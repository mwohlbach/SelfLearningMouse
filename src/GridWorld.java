import java.io.BufferedReader;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class GridWorld {

    //cheese=9, empty=0, outsidewall=1, horizontalwall=2, vertical wall=3, mouse=5
    //easy map
    static int[][] map = new int[][]{
        {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,2,2,3,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,9,3,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
        {3,0,0,0,2,3,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
        {3,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
        {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
    };
    //hard map
//    static int[][] map = new int[][]{
//        {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,2,2,3,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,3,9,3,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,3,0,3,0,0,0,0,3},
//        {3,0,0,0,2,3,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
//        {3,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//        {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
//    };
    
    static ArrayList<PastMove> pastmoves = new ArrayList<PastMove>();
    static MapLoc[][] mapval= new MapLoc[20][20];
    static int currx;
    static int curry;
    static double dist=1.0;
    static double lambda=.95;
    static double alpha=.0000001;
    static ArrayList<Integer> arrlist = new ArrayList<Integer>();
    static int grandtotal=0;
    static int thousands=0;
    
    public static void main(String[] args) {
        //makeRandomMapValues();
        makeZeroMapValues();
        //readMapValues();
        printMapValues();
        printMapWithPreferredMove();
        
        
        
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        System.out.println("How many times do you wish to drop Mr.Mousey?");
        int times = scan.nextInt();
        //int times=1000;
        for(int i=0;i<times;i++){
            //easy map
            map = new int[][]{
                    {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,2,2,3,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,9,3,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
                    {3,0,0,0,2,3,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
                    {3,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                    {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
            };
            //hard map
//            map = new int[][]{
//                    {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,2,2,3,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,3,9,3,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,3,0,3,0,0,0,0,3},
//                    {3,0,0,0,2,3,0,0,0,0,0,0,0,0,3,0,0,0,0,3},
//                    {3,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                    {3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
//            };
            pastmoves.clear();
            placeMouse();
            boolean nomnomtime=false;
            while(!nomnomtime){
                double choice = ran.nextDouble();
                if (choice<=lambda){
                    randomMove();
                }
                else{
                    educatedMove();
                }
                //scan.next();
                //printMap();
                nomnomtime=nomnomtime();
            }
            //System.out.println("Final move was from (" + pastmoves.get(pastmoves.size()-1).getx() + ", " + pastmoves.get(pastmoves.size()-1).gety() + ") with the move: " + pastmoves.get(pastmoves.size()-1).getmove());
            updateValues();
            //printMapValues();
            //printMap();
            arrlist.add(pastmoves.size());
            if(arrlist.size()==1000){
                int total=0;
                for(int k=0;k<1000;k++){
                    total=total+arrlist.get(k);
                }
                grandtotal=total+grandtotal;
                thousands++;
                System.out.println("Average of last 1000: " + total/1000);
                arrlist.clear();
            }
        //System.out.println("(" + (i+1) + ") " + "Mr.Mousey took " + pastmoves.size() + " moves before he found the cheese.");
            if(lambda>.1){
                lambda=lambda-alpha;
            }
        }
        if(thousands>0){
            System.out.println("Average of all: " + grandtotal/(thousands*1000));
        }
        makeMoveSheet();
        writeMapValues();
    }
    
    public static void makeMoveSheet(){
        File mapvalues = new File("movesheet.txt");
        try{
            FileWriter fw = new FileWriter(mapvalues.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
        if(mapval[i][j].getUp() > mapval[i][j].getDown() && mapval[i][j].getUp() > mapval[i][j].getRight() && mapval[i][j].getUp() > mapval[i][j].getLeft()){
            bw.write("U  ");
        }
        else if(mapval[i][j].getDown() > mapval[i][j].getUp() && mapval[i][j].getDown() > mapval[i][j].getRight() && mapval[i][j].getDown() > mapval[i][j].getLeft()){
            bw.write("D  ");
        }
        else if(mapval[i][j].getRight() > mapval[i][j].getDown() && mapval[i][j].getRight() > mapval[i][j].getUp() && mapval[i][j].getRight() > mapval[i][j].getLeft()){
            bw.write("L  ");
        }
        else if(mapval[i][j].getLeft() > mapval[i][j].getDown() && mapval[i][j].getLeft() > mapval[i][j].getRight() && mapval[i][j].getLeft() > mapval[i][j].getUp()){
            bw.write("R  ");
        }
        else{
            if(map[i][j]==5){
                bw.write("C  ");
            }else{
            bw.write("-  ");
            }
        }
                }
                bw.newLine();bw.newLine();
            }  
            bw.close();
        }
        catch(Exception e){
            
        }
        
            
    }
    
    public static void updateValues(){
        double toadd = dist/pastmoves.size();
        //System.out.println("The size of array is: " + pastmoves.size() + " and the toadd is: " + toadd);
        for(int i=(pastmoves.size()-1);i>-1;i--){
            double add = i*toadd;
            int xl = pastmoves.get(i).getx();
            int yl = pastmoves.get(i).gety();
            if(pastmoves.get(i).getmove()==0){
                mapval[xl][yl].up=mapval[xl][yl].up+add;
            }
            if(pastmoves.get(i).getmove()==1){
                mapval[xl][yl].down=mapval[xl][yl].down+add;
            }
            if(pastmoves.get(i).getmove()==2){
                mapval[xl][yl].right=mapval[xl][yl].right+add;
            }
            if(pastmoves.get(i).getmove()==3){
                mapval[xl][yl].left=mapval[xl][yl].left+add;
            }
        }
    }
    
    public static boolean nomnomtime(){
        if(currx==13 && curry ==13){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void educatedMove(){
        //System.out.println("(" + currx + ", " + curry + ") U:" + mapval[currx][curry].getUp() + " D:" + mapval[currx][curry].getDown() + " L:" + mapval[currx][curry].getLeft() + " R:" + mapval[currx][curry].getRight() );
        boolean invalidmove=true;
        //while(invalidmove){
            invalidmove=false;
        if(mapval[currx][curry].getUp() > mapval[currx][curry].getDown() && mapval[currx][curry].getUp() > mapval[currx][curry].getRight() && mapval[currx][curry].getUp() > mapval[currx][curry].getLeft() && (map[currx-1][curry]==0 || map[currx-1][curry]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,0);
            currx=currx-1;
            map[currx][curry]=5;
            pastmoves.add(temp);
        }
        else if(mapval[currx][curry].getDown() > mapval[currx][curry].getUp() && mapval[currx][curry].getDown() > mapval[currx][curry].getRight() && mapval[currx][curry].getDown() > mapval[currx][curry].getLeft() && (map[currx+1][curry]==0 || map[currx+1][curry]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,1);
            pastmoves.add(temp);
            currx=currx+1;
            map[currx][curry]=5;
        }
        else if(mapval[currx][curry].getRight() > mapval[currx][curry].getDown() && mapval[currx][curry].getRight() > mapval[currx][curry].getUp() && mapval[currx][curry].getRight() > mapval[currx][curry].getLeft() && (map[currx][curry+1]==0 || map[currx][curry+1]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,3);
            pastmoves.add(temp);
            curry=curry+1;
            map[currx][curry]=5;
        }
        else if(mapval[currx][curry].getLeft() > mapval[currx][curry].getDown() && mapval[currx][curry].getLeft() > mapval[currx][curry].getRight() && mapval[currx][curry].getLeft() > mapval[currx][curry].getUp() && (map[currx][curry-1]==0 || map[currx][curry-1]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,2);
            pastmoves.add(temp);
            curry=curry-1;
            map[currx][curry]=5;
        }
        else{
            invalidmove=true;
        }
        //}
    }
    
    public static void randomMove(){
        Random r = new Random();
        boolean invalidmove=true;
        while(invalidmove){
            invalidmove=false;
        int move = r.nextInt(4);
        if(move==0 && (map[currx-1][curry]==0 || map[currx-1][curry]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,0);
            pastmoves.add(temp);
            currx=currx-1;
            map[currx][curry]=5;
        }
        else if(move==1 && (map[currx+1][curry]==0 || map[currx+1][curry]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,1);
            pastmoves.add(temp);
            currx=currx+1;
            map[currx][curry]=5;            
        }
        else if(move==2 && (map[currx][curry-1]==0 || map[currx][curry-1]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,2);
            pastmoves.add(temp);
            curry=curry-1;
            map[currx][curry]=5;
        }
        else if(move==3 && (map[currx][curry+1]==0 || map[currx][curry+1]==9)){
            map[currx][curry]=0;
            PastMove temp = new PastMove(currx,curry,3);
            pastmoves.add(temp);
            curry=curry+1;
            map[currx][curry]=5;
        }
        else{
            invalidmove=true;
        }
        }
    }
    
    public static void readMapValues(){     
        try{
        BufferedReader br = new BufferedReader(new FileReader("mapvalues.txt"));
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                MapLoc temp;
                boolean kappa=false;
                if(map[i][j]==0){
                    kappa=true;
                }
                String line = br.readLine();
                String line1 = br.readLine();
                String line2 = br.readLine();
                String line3 = br.readLine();
                //System.out.println(line + " " + line1 + " " + line2 + " " + line3);
                temp = new MapLoc(Double.parseDouble(line),Double.parseDouble(line1),Double.parseDouble(line2),Double.parseDouble(line3),kappa);
                //System.out.println(temp.getUp() + " " + temp.getDown() + " " + temp.getRight() + " " + temp.getLeft());
                //System.out.println("i:" + i  + " j:" + j);
                mapval[i][j]=temp;
            }
        }
        
        
        }
        catch(Exception e){
            
        }
        
    }
    
    public static void writeMapValues(){
        File mapvalues = new File("mapvalues.txt");
        try{
        FileWriter fw = new FileWriter(mapvalues.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                    try{
                        

                        String s =Double.toString(mapval[i][j].getUp());
                                //+ " "+ Double.toString(mapval[i][j].getDown()) + " " + Double.toString(mapval[i][j].getLeft()) + " " + Double.toString(mapval[i][j].getRight());
                        String s1 =Double.toString(mapval[i][j].getDown());
                        String s2 =Double.toString(mapval[i][j].getLeft());
                        String s3 =Double.toString(mapval[i][j].getRight());
                                

                        bw.write(s); bw.newLine();
                        bw.write(s1); bw.newLine();
                        bw.write(s2); bw.newLine();
                        bw.write(s3); bw.newLine();

                    }
                    catch(Exception e){
            
                    }
            }
        }
        bw.close();
        }
        catch(Exception e){
            
        }
        
    }
    
    public static void printMapValues(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                System.out.println("(" + i + ", " + j + ")");
                if(mapval[i][j].isRelevant()){
                    System.out.println("U:" + mapval[i][j].getUp() + " D:" + mapval[i][j].getDown() + " L:" + mapval[i][j].getLeft() + " R:" + mapval[i][j].getRight());
                }
                else{
                    System.out.println("Point is irrelevant");
                }
            }
        }
    }
    
    public static void makeRandomMapValues(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                MapLoc temp;
                if(map[i][j]==0){
                    Random r = new Random();
                    double ran1 = r.nextDouble();
                    double ran2 = r.nextDouble();
                    double ran3 = r.nextDouble();
                    double ran4 = r.nextDouble();
                    temp = new MapLoc(ran1,ran2,ran3,ran4,true);
                }
                else{
                    temp = new MapLoc(0,0,0,0,false);
                }
                mapval[i][j]=temp;
            }
        }
    }
    
    public static void makeZeroMapValues(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                MapLoc temp;
                if(map[i][j]==0){                  
                    temp = new MapLoc(0,0,0,0,true);
                }
                else{
                    temp = new MapLoc(0,0,0,0,false);
                }
                mapval[i][j]=temp;
            }
        }
    }
    
    public static void placeMouse(){
        Random r = new Random();
        boolean notplaced = true;
        
        while(notplaced){
            int x = r.nextInt(20);
            int y = r.nextInt(20);
            if(map[x][y]==0){
                map[x][y]=5;
                currx=x;
                curry=y;
                notplaced=false;
            }
        }
    }

    public static void printMapWithPreferredMove(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(map[i][j]==0){
                    if(mapval[i][j].getUp() > mapval[i][j].getDown() && mapval[i][j].getUp() > mapval[i][j].getRight() && mapval[i][j].getUp() > mapval[i][j].getLeft()){
                        System.out.print("U ");
                    }
                    else if(mapval[i][j].getDown() > mapval[i][j].getUp() && mapval[i][j].getDown() > mapval[i][j].getRight() && mapval[i][j].getDown() > mapval[i][j].getLeft()){
                        System.out.print("D ");
                    }
                    else if(mapval[i][j].getRight() > mapval[i][j].getDown() && mapval[i][j].getRight() > mapval[i][j].getUp() && mapval[i][j].getRight() > mapval[i][j].getLeft()){
                        System.out.print("R ");
                    }
                    else if(mapval[i][j].getLeft() > mapval[i][j].getDown() && mapval[i][j].getLeft() > mapval[i][j].getRight() && mapval[i][j].getLeft() > mapval[i][j].getUp()){
                        System.out.print("L ");
                    }
                    else{
                        System.out.print("  ");
                    }
                }
                else if(map[i][j]==9){
                    System.out.print("C ");
                }
                else if(map[i][j]==2){
                    System.out.print("--");
                }
                else if(map[i][j]==3){
                    System.out.print("| ");
                }
                else if(map[i][j]==5){
                    System.out.print("M ");
                }
                else{
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public static void printMap(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(map[i][j]==0){
                    System.out.print("  ");
                }
                else if(map[i][j]==9){
                    System.out.print("C ");
                }
                else if(map[i][j]==2){
                    System.out.print("--");
                }
                else if(map[i][j]==3){
                    System.out.print("| ");
                }
                else if(map[i][j]==5){
                    System.out.print("M ");
                }
                else{
                System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
}
