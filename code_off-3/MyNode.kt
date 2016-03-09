package com.coocloud

/**
 * Created by coocloud on 2016-03-09.
 */
class MyNode(var posX:

             Int, var posY: Int, var type: Char, var isVisited:

             Boolean) {

    override fun equals(obj: Any?): Boolean {
        if (this === obj)
            return true
        if (obj == null)
            return false
        if (javaClass != obj.javaClass)
            return false
        val other = obj as MyNode?
        if (this.posX != obj.posX)
            return false
        if (this.posY != obj.posY)
            return false
        return true
    }

}
