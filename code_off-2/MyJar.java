package com.coocloud;

/**
 * Created by kuan-hsiang.fu on 2016-02-26.
 */
public class MyJar {
    int val;

    public int[] getTypes() {
        return types;
    }

    public void setTypes(int[] types) {
        this.types = types;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

    int[] types;
    int assigned;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    int volume;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    int number;

    public MyJar(int val, int[] types, int number){
        this.val = val;
        this.types = types;
        this.assigned = -1;
        this.number = number;
    }
}
