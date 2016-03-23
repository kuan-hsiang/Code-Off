package com.coocloud;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by coocloud on 2016-02-26.
 */
public class Week2 {
    public static class MyJarComparator implements Comparator<MyJar> {
        @Override
        public int compare(MyJar j1, MyJar j2) {
            if (j1.getVal() > j2.getVal())
                return -1;
            if (j1.getVal() < j2.getVal())
                return 1;
            return 0;
        }
    };
    public static class MyJarComparator2 implements Comparator<MyJar> {
        @Override
        public int compare(MyJar j1, MyJar j2) {
            if (j1.getNumber() > j2.getNumber())
                return 1;
            if (j1.getNumber() < j2.getNumber())
                return -1;
            return 0;
        }
    };
    public static void main(String[] args) throws IOException {
        try {
            System.setIn(new FileInputStream(new File("C:\\Users\\kuan-hsiang.fu\\Desktop\\CodeOff\\src\\com\\coocloud\\code_off-2.in")));
        } catch (FileNotFoundException e1) {
            System.out.println("Input file not found");
            System.exit(1);
        }

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        ArrayList<Integer> liquidType = new ArrayList<>();
        ArrayList<MyJar> myJarArrayList = new ArrayList<>();
        int numberLiquid =  Integer.parseInt(in.readLine().trim());
        for (int i=0; i<numberLiquid; i++){
            liquidType.add(Integer.parseInt(in.readLine().trim()));
        }
        int numberJars = Integer.parseInt(in.readLine().trim());
        for (int i=0; i<numberJars; i++){
            String [] theString = in.readLine().split(",");
            int [] theIntArray = new int[theString.length-1];
            for (int j=1; j<theString.length; j++){
                theIntArray[j-1] = Integer.parseInt(theString[j]);
            }
            myJarArrayList.add(new MyJar(Integer.parseInt(theString[0]),theIntArray,i));
        }
        int[] type = new int[liquidType.size()];
        for (int i=0; i<liquidType.size(); i++){
            type[i] = liquidType.get(i);
        }
        for (MyJar jar:myJarArrayList){
            if (jar.getTypes().length==1){
                int currentMax = type[jar.getTypes()[0]] - jar.getVal();
                if (currentMax < 0) {
                    currentMax = type[jar.getTypes()[0]];
                }else {
                    currentMax = jar.getVal();
                }
                jar.setAssigned(jar.getTypes()[0]);
                jar.setVolume(currentMax);
                type[jar.getAssigned()] -= currentMax;
            }
        }
        Collections.sort(myJarArrayList, new MyJarComparator());
        for (MyJar jar:myJarArrayList){
            int maxVal = -1;
            int maxIndex = -1;
            if (jar.getAssigned()==-1){
                //System.out.println("not assigned yet");
                for (int i=0; i<jar.getTypes().length; i++){
                    int currentMax = type[jar.getTypes()[i]] - jar.getVal();
                    if (currentMax < 0) {
                        currentMax = type[jar.getTypes()[i]];
                    }else {
                        currentMax = jar.getVal();
                    }
                    if (currentMax>maxVal){
                        maxVal = currentMax;
                        maxIndex = i;
                    }
                }
                jar.setAssigned(maxIndex);
                jar.setVolume(maxVal);
                type[jar.getTypes()[maxIndex]] -= maxVal;
            }
        }
        Collections.sort(myJarArrayList, new MyJarComparator2());
        int left = 0;
        for (int i:type){
            left += i;
        }
        System.out.println(left);
        for (MyJar jar:myJarArrayList){
            System.out.println(jar.getAssigned() + "," + jar.getVolume());
        }
    }
}
