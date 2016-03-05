using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CodeOff
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] lines = System.IO.File.ReadAllLines(@"C:\Users\coocloud\Documents\Code-Off-master\code_off-3\code_off-3-2.in");
            Console.WriteLine(lines.Length);
            char[,] myCharArray = new char[lines.Length,lines.Length];
            bool[,] visited = new bool[lines.Length, lines.Length];
            bool[] waterSource = new bool[100];
            int[,] territory = new int[lines.Length, lines.Length];
            int row = 0;
            foreach (string line in lines)
            {
                int column = 0;
                foreach (char character in line)
                {
                    myCharArray[row,column] = character;
                    column++;
                }
                row++;
            }
            int xBound = myCharArray.GetUpperBound(0);
            Program P = new Program();
            int highestCount = -1;
            int largestWaterSource = -1;
            int source = P.getUnusedNumber(waterSource);

            for (int i = 0; i <= xBound; i++)
            {
                for (int j = 0; j <= xBound; j++)
                {
                    if (myCharArray[i, j] != '#' && !visited[i, j])
                    {
                        source = P.getUnusedNumber(waterSource);
                        int number = P.populateWater(i, j, visited, myCharArray, territory, source, lines.Length);
                        if (number > highestCount)
                        {
                            highestCount = number;
                            largestWaterSource = source;
                        }
                    }
                }
            }

            for (int i = 0; i <= xBound; i++)
            {
                for (int j = 0; j <= xBound; j++)
                {
                    if (territory[i, j]==largestWaterSource)
                    {
                        myCharArray[i, j] = '*';
                    }
                    Console.Write(myCharArray[i, j]);
                }
                Console.WriteLine();
            }
            Console.ReadKey();
        }
        public int getUnusedNumber(bool[] array)
        {
            for (int i=0; i<array.Length; i++)
            {
                if (!array[i])
                {
                    array[i] = true;
                    return i;
                }
            }
            return -9;
        }

        public int populateWater(int x, int y, bool[,] visited, char[,] charArray, int[,] territory, int sourceType, int length)
        {
            if (x<0 || x>length-1 || y<0 || y>length-1)
            {
                return 0;
            }
            if (charArray[x,y]=='#' || visited[x,y])
            {
                return 0;
            }
            visited[x, y] = true;
            territory[x, y] = sourceType;
            return 1 + populateWater(x-1,y,visited, charArray,territory,sourceType,length) + populateWater(x + 1, y, visited, charArray, territory, sourceType,length)
                + populateWater(x, y-1, visited, charArray, territory, sourceType, length) + populateWater(x, y+1, visited, charArray, territory, sourceType,length);
        }
    }
}
