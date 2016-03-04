package com.coocloud;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by coocloud on 2016-03-04.
 */
public class Week3 {
    static int gridLength;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        try {
            System.setIn(new FileInputStream(new File("C:\\Users\\kuan-hsiang.fu\\Desktop\\CodeOff\\src\\com\\coocloud\\code_off-3-2.in")));
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
//        for (char[] c: char2DArray){
//            System.out.println(c);
//        }
        List<Queue<MyNode>> myArrayList = new ArrayList<>();
        Queue<MyNode> queue = new LinkedList<>();
        myArrayList.add(queue);
        for (int i = 0; i < charLength; i++) {
            for (int j = 0; j < charLength ; j++) {
                if (char2DArray[i][j]!='#' && !visited[i][j]){
                    //System.out.print(char2DArray[i][j]);
                    boolean found = false;
                    for (Queue<MyNode>q : myArrayList){
                        if (q.contains(new MyNode(i,j,'-',false))){
                            found = true;
                        }
                    }
                    if (!found){
                        Queue<MyNode> mn = new LinkedList<>();
                        mn.add(new MyNode(i,j,char2DArray[i][j],true));
                        getWaterLevel(i, j, char2DArray, mn);
                        myArrayList.add(mn);
                    }
                }else {
                }
            }
        }

        //getWaterLevel(0,4,char2DArray);

//        for (boolean[] b: visited){
//            for (boolean ans:b){
//                System.out.print(ans+" ");
//            }
//            System.out.println();
//        }
        int largest = 0;
        int index = -1;
        for (int i = 0; i < myArrayList.size() ; i++) {
            if (myArrayList.get(i).size()>largest){
                largest = myArrayList.get(i).size();
                index = i;
            }
        }
        Queue<MyNode> waterSource = myArrayList.get(index);
        for(MyNode n:waterSource){
            char2DArray[n.posX][n.posY] = '*';
        }
        for (char[] c: char2DArray){
            System.out.println(c);
        }
//        for (Queue<MyNode>q : myArrayList){
//            System.out.println(q.size());
//        }
    }
    public static void getWaterLevel(int posX, int posY, char[][] char2Darray, Queue<MyNode> q){
        if (posX<0 || posX > gridLength-1 || posY < 0 || posY > gridLength-1){
            //System.out.println("out of bounds: " + posX + ","+ posY);
            return;
        }
        if (visited[posX][posY] || char2Darray[posX][posY]=='#'){
            return;
        }
        visited[posX][posY] = true;
        q.add(new MyNode(posX, posY, char2Darray[posX][posY], true));
        //char2Darray[posX][posY] = '*';
        getWaterLevel(posX - 1, posY, char2Darray, q);
        getWaterLevel(posX,posY-1,char2Darray,q);
        getWaterLevel(posX+1,posY,char2Darray,q);
        getWaterLevel(posX,posY+1,char2Darray,q);

    }
}
