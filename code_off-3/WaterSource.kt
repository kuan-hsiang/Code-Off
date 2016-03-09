package com.coocloud


import java.io.*
import java.nio.charset.StandardCharsets
import java.util.ArrayList
import java.util.LinkedList
import java.util.Queue

/**
 * Created by coocloud on 2016-03-09.
 */
object WaterSource {
    internal var gridLength: Int = 0

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        try {
            System.setIn(FileInputStream(File("C:\\Users\\kuan-hsiang.fu\\Desktop\\CodeOff\\src\\com\\coocloud\\code_off-3-1.in")))
        } catch (e1: FileNotFoundException) {
            println("Input file not found")
            System.exit(1)
        }
        val visited: Array<BooleanArray>
        val converter = InputStreamReader(System.`in`)
        val `in` = BufferedReader(converter)
        var theString = `in`.readLine().trim { it <= ' ' }
        val myCharArray = theString.toCharArray()
        val charLength = myCharArray.size
        gridLength = charLength
        visited = Array(gridLength) { BooleanArray(gridLength) }
        val char2DArray = Array(charLength) { CharArray(charLength) }
        char2DArray[0] = myCharArray
        for (i in 1..charLength - 1) {
            theString = `in`.readLine().trim { it <= ' ' }
            char2DArray[i] = theString.toCharArray()
        }
        val myArrayList = ArrayList<Queue<MyNode>>()
        val queue = LinkedList<MyNode>()
        myArrayList.add(queue)
        for (i in 0..charLength - 1) {
            for (j in 0..charLength - 1) {
                if (char2DArray[i][j] != '#' && !visited[i][j]) {
                    var found = false
                    for (q in myArrayList) {
                        if (q.contains(MyNode(i, j, '-', false))) {
                            found = true
                        }
                    }
                    if (!found) {
                        val mn = LinkedList<MyNode>()
                        mn.add(MyNode(i, j, char2DArray[i][j], true))
                        getWaterLevel(i, j, char2DArray, mn, visited)
                        myArrayList.add(mn)
                    }
                }
            }
        }
        var largest = 0
        var index = -1
        for (i in myArrayList.indices) {
            if (myArrayList[i].size > largest) {
                largest = myArrayList[i].size
                index = i
            }
        }
        val waterSource = myArrayList[index]
        for (n in waterSource) {
            char2DArray[n.posX][n.posY] = '*'
        }
        val file = File("C:\\Users\\kuan-hsiang.fu\\Desktop\\CodeOff\\src\\com\\coocloud\\output-3-1.txt")

        try {
            FileOutputStream(file).use { fop ->

                if (!file.exists()) {
                    file.createNewFile()
                }
                for (c in char2DArray) {
                    val contentInBytes = String(c).toByteArray(StandardCharsets.UTF_8)
                    fop.write(contentInBytes)
                    fop.write("\n".toByteArray(StandardCharsets.UTF_8));
                }
                fop.flush()
                fop.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getWaterLevel(posX: Int, posY: Int, char2Darray: Array<CharArray>, q: Queue<MyNode>, visited: Array<BooleanArray>) {
        if (posX < 0 || posX > gridLength - 1 || posY < 0 || posY > gridLength - 1) {
            return
        }
        if (visited[posX][posY] || char2Darray[posX][posY] == '#') {
            return
        }
        visited[posX][posY] = true
        q.add(MyNode(posX, posY, char2Darray[posX][posY], true))
        getWaterLevel(posX - 1, posY, char2Darray, q, visited)
        getWaterLevel(posX, posY - 1, char2Darray, q, visited)
        getWaterLevel(posX + 1, posY, char2Darray, q, visited)
        getWaterLevel(posX, posY + 1, char2Darray, q, visited)
    }
}
