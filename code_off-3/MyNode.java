package com.coocloud;

/**
 * Created by coocloud on 2016-03-04.
 */
public class MyNode{

    int posX;
    int posY;
    char type;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    boolean visited;

    public MyNode (int posX, int posY, char type, boolean visited){
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.visited = visited;
    }

//    @Override
//    public int hashCode() {
//        int hashCode = 1;
//
//        hashCode = hashCode * 37 + this.posX.hashCode();
//        hashCode = hashCode * 37 + this.posY.hashCode();
//
//        return hashCode;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyNode other = (MyNode) obj;
        if (this.posX != other.posX)
            return false;
        if (this.posY != other.posY)
            return false;
        return true;
    }

}
