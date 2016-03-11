package com.coocloud;


import java.io.*;
import java.util.*;

/**
 * Created by coocloud on 2016-03-11.
 */
public class Week4 {
    static int gridLength;
    static boolean[][] visited;
    public static class MyComparator implements Comparator< Queue<MyNode>> {
        @Override
        public int compare( Queue<MyNode> j1,  Queue<MyNode> j2) {
            if (j1.size() > j2.size())
                return -1;
            if (j1.size() < j2.size())
                return 1;
            return 0;
        }
    }
    public static void main(String[] args) throws IOException{
        try {
            System.setIn(new FileInputStream(new File("C:\\Users\\kuan-hsiang.fu\\Desktop\\CodeOff\\src\\com\\coocloud\\code_off-4-2.in")));
            //System.setIn(new FileInputStream(new File("C:\\Users\\kuan-hsiang.fu\\Desktop\\CodeOff\\src\\com\\coocloud\\sample3.in")));
        } catch (FileNotFoundException e1) {
            System.out.println("Input file not found");
            System.exit(1);
        }

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        String theString = in.readLine().trim();
        char[] myCharArray = theString.toCharArray();
        int charLength = myCharArray.length;
        gridLength = charLength;
        visited = new boolean[gridLength][gridLength];
        char[][] char2DArray = new char[charLength][charLength];
        char2DArray[0] = myCharArray;
        for (int i = 1; i < charLength ; i++) {
            theString = in.readLine().trim();
            char2DArray[i] = theString.toCharArray();
        }

        List<Queue<MyNode>> myArrayList = new ArrayList<>();
        Queue<MyNode> queue = new LinkedList<>();
        myArrayList.add(queue);
        for (int i = 0; i < charLength; i++) {
            for (int j = 0; j < charLength ; j++) {
                if (char2DArray[i][j]!='#' && !visited[i][j]){
                    boolean found = false;
                    for (Queue<MyNode>q : myArrayList){
                        if (q.contains(new MyNode(i,j,'-',false))){
                            found = true;
                        }
                    }
                    if (!found){
                        Queue<MyNode> mn = new LinkedList<>();
                        //mn.add(new MyNode(i,j,char2DArray[i][j],true));
                        getWaterLevel(i, j, char2DArray, mn);
                        myArrayList.add(mn);
                    }
                }else {
                }
            }
        }

        myArrayList.remove(0);
        Queue<MyNode> waterQueue = new LinkedList<>();
        int largest = 0;
        for (int i = 0; i < myArrayList.size() ; i++) {
            //System.out.println(myArrayList.get(i).size());
            if (myArrayList.get(i).size()>largest){
                largest = myArrayList.get(i).size();
            }
        }

//        for (char[] c: char2DArray){
//            System.out.println(c);
//        }

        Collections.sort(myArrayList, new MyComparator());
        combineWater(waterQueue, myArrayList.get(0));
        myArrayList.remove(0);

        int outLoop = myArrayList.size();
        for (int o = 0; o <outLoop ; o++) {
            int listLength = myArrayList.size();
            int dummyVar = 0;
            //double globalMin = 99.9;
            int globalMin = 99;
            int globalMinIndex = -1;
            MyNode globalFrom = null;
            MyNode globalTo = null;
            while(listLength>0){
                //System.out.println(myArrayList.size()+" size");
                //System.out.println(myArrayList.get(0).size() +" queue size");
                Queue<MyNode> myQueue = myArrayList.get(0);
                //double shortestDist = 99.9;
                int shortestDist = 99;
                MyNode fromNode = null;
                MyNode toNode = null;
                for (int i=0; i<waterQueue.size();i++){
                    //shortestDist = 99.9;
                    MyNode x = waterQueue.poll();
                    for (int j=0; j<myQueue.size();j++){
                        MyNode y = myQueue.poll();
                        //double dist = getEuclideanDist(x, y);
                        int dist = getManhattanDist(x,y);
                        if (dist <= shortestDist && dist!=0.0 && dist!=1.0){
                            shortestDist = dist;
                            fromNode = x;
                            toNode = y;
                        }
                        myQueue.add(y);
                    }
                    waterQueue.add(x);
                }
                //System.out.println(shortestDist);
                if (shortestDist < globalMin){
                    globalMin = shortestDist;
                    globalMinIndex = dummyVar;
                    globalFrom = fromNode;
                    globalTo = toNode;
                }
                myArrayList.remove(0);
                myArrayList.add(myQueue);
                listLength--;
                dummyVar++;
            }
//            System.out.println("global min index: "+ globalMinIndex);
//            System.out.println("global min dist: "+ globalMin);
//            System.out.println("From "+ globalFrom.getPosX() +" :" +globalFrom.getPosY());
//            System.out.println("To "+ globalTo.getPosX() +" :" +globalTo.getPosY());
            if (globalMin != 99){
                destroyWalls(globalFrom, globalTo, char2DArray, waterQueue);
                combineWater(waterQueue, myArrayList.get(globalMinIndex));
                myArrayList.remove(globalMinIndex);
            }
//            System.out.println(waterQueue.size());
        }
        System.out.println("output");
        for (char[] c: char2DArray){
            System.out.println(c);
        }
    }
    public static void getWaterLevel(int posX, int posY, char[][] char2Darray, Queue<MyNode> q){
        if (posX<0 || posX > gridLength-1 || posY < 0 || posY > gridLength-1){
            return;
        }
        if (visited[posX][posY] || char2Darray[posX][posY]=='#'){
            return;
        }
        visited[posX][posY] = true;
        q.add(new MyNode(posX, posY, char2Darray[posX][posY], true));
        getWaterLevel(posX - 1, posY, char2Darray, q);
        getWaterLevel(posX,posY-1,char2Darray,q);
        getWaterLevel(posX+1,posY,char2Darray,q);
        getWaterLevel(posX,posY+1,char2Darray,q);

    }

    public static double getEuclideanDist(MyNode x, MyNode y){
        double dist=0.0;
        double xDist = Math.abs(x.getPosX()-y.getPosX());
        double yDist = Math.abs(x.getPosY()-y.getPosY());
        dist = Math.sqrt((xDist*xDist)+(yDist*yDist));
        return dist;
    }

    public static int getManhattanDist(MyNode x, MyNode y) {
        return Math.abs(x.getPosX()-y.getPosX()) + Math.abs(x.getPosY()-y.getPosY());
    }

    public static void combineWater(Queue<MyNode> w1, Queue<MyNode> w2){
        for (MyNode n:w2){
            w1.add(n);
        }
    }

    public static void destroyWalls(MyNode x, MyNode y, char[][]char2DArray, Queue<MyNode> n ){
        if (getEuclideanDist(x,y)==1.0){
            return;
        }
        if (x.getPosX()==y.getPosX()){
            if (x.getPosY()<y.getPosY()){
                for (int i=x.getPosY()+1;i<y.getPosY();i++){
                    if(char2DArray[x.getPosX()][i]=='#'){
                        char2DArray[x.getPosX()][i] = '*';
                        n.add(new MyNode(x.getPosX(),i,'*',true));
                    }
                }
                return;
            }else {
                for (int i=y.getPosY()+1;i<x.getPosY();i++){
                    if(char2DArray[x.getPosX()][i]=='#'){
                        char2DArray[x.getPosX()][i] = '*';
                        n.add(new MyNode(x.getPosX(),i,'*',true));
                    }
                }
                return;
            }
        }
        if (x.getPosY()==y.getPosY()){
            if(x.getPosX()<y.getPosX()){
                for (int i=x.getPosX()+1;i<y.getPosX();i++){
                    if(char2DArray[i][x.getPosY()]=='#'){
                        char2DArray[i][x.getPosY()] = '*';
                        n.add(new MyNode(i,x.getPosY(),'*',true));
                    }
                }
                return;
            }else {
                for (int i=y.getPosX()+1;i<x.getPosX();i++){
                    if(char2DArray[i][x.getPosY()]=='#'){
                        char2DArray[i][x.getPosY()] = '*';
                        n.add(new MyNode(i,x.getPosY(),'*',true));
                    }
                }
                return;
            }
        }
        if (x.getPosX()<y.getPosX()){
            if (x.getPosY()<y.getPosY()){
                if (x.getPosY()+1 > gridLength-1){
                    return;
                }
                n.add(new MyNode(x.getPosX(),x.getPosY()+1,'*',true));
                char2DArray[x.getPosX()][x.getPosY()+1] = '*';
                destroyWalls(new MyNode(x.getPosX(), x.getPosY()+1, '*', true),y,char2DArray,n);
                return;
            }
            else{
                if (x.getPosY()-1 < 0){
                    return;
                }
                n.add(new MyNode(x.getPosX(),x.getPosY()-1,'*',true));
                char2DArray[x.getPosX()][x.getPosY()-1] = '*';
                destroyWalls(new MyNode(x.getPosX(), x.getPosY()-1, '*', true),y,char2DArray,n);
                return;
            }
        }
        if (x.getPosX()>y.getPosX()){
            if (x.getPosY()<y.getPosY()){
                n.add(new MyNode(x.getPosX(),x.getPosY()+1,'*',true));
                char2DArray[x.getPosX()][x.getPosY()+1] = '*';
                destroyWalls(new MyNode(x.getPosX(), x.getPosY()+1, '*', true),y,char2DArray,n);
                return;
            }
            else{
                n.add(new MyNode(x.getPosX(),x.getPosY()-1,'*',true));
                char2DArray[x.getPosX()][x.getPosY()-1] = '*';
                destroyWalls(new MyNode(x.getPosX(), x.getPosY()-1, '*', true),y,char2DArray,n);
                return;
            }
        }
    }
}
