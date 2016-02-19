package com.coocloud;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Map<Mult, ArrayList<String>> myMap = new LinkedHashMap<>();
        ArrayList<Mult> arrayList = new ArrayList<>();
        //readString("Hi");
        File file = new File("C:\\Users\\kuan-hsiang.fu\\Desktop\\CodeOff\\src\\com\\coocloud\\code_off-1.in");
        int numInput = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            numInput = Integer.parseInt(br.readLine().trim());
            //System.out.println(numInput);
            for(String line; (line = br.readLine()) != null; ) {
                int myvalue = readString(line.trim());
                ArrayList<String> myarr = new ArrayList<>();
                Mult myMult = new Mult(myvalue, myarr, line.trim());
                arrayList.add(myMult);
            }
            for (Mult m:arrayList){
                ArrayList<String> myString = m.getMatchedStrings();
                for (Mult n:arrayList){
                    if ((!m.getTheString().equals(n.getTheString())) && m.getValue()== n.getValue()){
                        myString.add(n.getTheString());
                    }
                }
                m.setMatchedStrings(myString);
            }
            for (Mult m:arrayList){
                ArrayList<String> myString = m.getMatchedStrings();
                System.out.println(m.getTheString());
                System.out.println(isPalindrome(m.getTheString()));
                for (String s:myString){
                    System.out.println(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(getValue('A'));
        //System.out.println(getValue('a'));
        //System.out.println(getValue('D'));
        //System.out.println(getValue('d'));
    }


    public static int readString(String theString){
        int total = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < theString.length(); i++) {
            //System.out.println(getValue(theString.charAt(i)));
            total += getValue(theString.charAt(i));
        }

        return total;
    }

    public static int getValue(char c){
        if (Character.isUpperCase(c)){
            return c-64;
        }
        return c-97;
    }

    public static boolean isPalindrome(String s) {
        //boolean isBool = false;
        int n = s.length();
        for (int i=0; i<(n/2); ++i) {
            if (s.charAt(i) != s.charAt(n-i-1)) {
                return false;
            }
        }

        return true;
    }
}
