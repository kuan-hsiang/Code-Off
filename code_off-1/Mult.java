package com.coocloud;

import java.util.ArrayList;

/**
 * Created by kuan-hsiang.fu on 2016-02-19.
 */
public class Mult {
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<String> getMatchedStrings() {
        return matchedStrings;
    }

    public void setMatchedStrings(ArrayList<String> matchedStrings) {
        this.matchedStrings = matchedStrings;
    }

    public Mult(int value, ArrayList<String> matchedStrings, String theString) {
        this.value = value;
        this.matchedStrings = matchedStrings;
        this.theString = theString;
    }

    int value;
    ArrayList<String> matchedStrings;

    public String getTheString() {
        return theString;
    }

    public void setTheString(String theString) {
        this.theString = theString;
    }

    String theString;
}
