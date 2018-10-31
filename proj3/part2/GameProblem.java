/**
 * Project 3.
 * Part 2
 *
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 10/31/18
 */

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class GameProblem
{
   enum Direction {DOWN, RIGHT, EXIT};

   //solution class
   private static class GameSolution
   {
      private int[][] S;
      private Direction[][] R; 
      private int bestI, bestJ, bestS;
      
      public GameSolution(int[][] S, Direction[][] R)
      {
         this.S = S;
         this.R = R;
         bestI = bestJ = -1;
         bestS = S[0][0];
      }

      //sweep for best starting point
      private void findBest()
      {
         for (int i = 0; i <= S.length - 1; i++)
         {
            for (int j = 0; j <= S[0].length - 1; j++)
            {
               if (S[i][j] >= bestS)
               {
                  bestS = S[i][j];
                  bestI = i;
                  bestJ = j;
               }
            }
         }
      }

      //test method
      private void printAux()
      {
         for (int i = 0; i < R.length; i++)
            System.out.println(Arrays.deepToString(R[i]));
      }

      //print solution
      public void printSolution()
      {
         findBest(); //find best start point

         //print score
         System.out.println("Best score: " + bestS);
         System.out.print("Best route: ");

         int i = bestI;
         int j = bestJ;

         //continue until EXIT
         while (R[i][j] != Direction.EXIT)
         {
            //print current
            printBlock(i, j);

            //increment
            if (R[i][j] == Direction.RIGHT)
               j++;
            //DOWN
            else
               i++;
         } 

         //on EXIT
         printBlock(i, j);
         System.out.println("exit");
      }

      private void printBlock(int i, int j)
      {
         System.out.print("[" + (i + 1) + "," + (j + 1) + "] to ");
      }
   }

   //actual algoritm
   public static void playGame(int n, int m, int[][] board)
   {
      /* work */
      int[][] S = new int[n][m];
      Direction[][] R = new Direction[n][m];
      int tempR, tempD;

      //bottom-right corner
      S[n - 1][m - 1] = board[n - 1][m - 1];
      R[n - 1][m - 1] = Direction.EXIT;

      //bottom row
      for (int j = m - 2; m > 1 && j >= 0; j--)
      {
         tempR = S[n - 1][j + 1];
         //continue RIGHT
         if (tempR > 0)
         {
            S[n - 1][j] = tempR + board[n - 1][j];
            R[n - 1][j] = Direction.RIGHT;
         }
         //or EXIT
         else
         {
            S[n - 1][j] = 0 + board[n - 1][j];
            R[n - 1][j] = Direction.EXIT;
         }
      }

      //right col
      for (int i = n - 2; n > 1 && i >= 0; i--)
      {
         tempD = S[i + 1][m - 1];
         //continue DOWN
         if (tempD > 0)
         {
            S[i][m - 1] = tempD + board[i][m - 1];
            R[i][m - 1] = Direction.DOWN;
         }
         //or EXIT
         else
         {
            S[i][m - 1] = 0 + board[i][m - 1];
            R[i][m - 1] = Direction.EXIT;
         }
      }

      //rest
      for (int i = n - 2; i >= 0; i--)
      {
         for (int j = m - 2; j >= 0; j--)
         {
            tempD = S[i + 1][j];
            tempR = S[i][j + 1];

            //determine DOWN
            if (tempD > tempR)
            {
               S[i][j] = S[i + 1][j] + board[i][j];
               R[i][j] = Direction.DOWN;
            }
            //or RIGHT
            else
            {
               S[i][j] = S[i][j + 1] + board[i][j];
               R[i][j] = Direction.RIGHT;
            }
         }
      }

      //create solution
      GameSolution solution = new GameSolution(S, R);

      //print solution
      solution.printSolution();
   }

   public static void main(String[] args)
   {
      //prompt file
      Scanner in = new Scanner(System.in);
      System.out.println("File name:");
      String filename = in.next();

      //read from file
      try (Scanner fileReader = new Scanner(new File(filename)))
      {
         //get dimensions
         int rows = fileReader.nextInt();
         int cols = fileReader.nextInt();

         int[][] board = new int[rows][cols];
         
         //fill board
         for (int i = 0; i < rows; i++)
         {
            for (int j = 0; j < cols; j++)
            {
               board[i][j] = fileReader.nextInt();
            }
         }

         //run algorithm (includes print)
         playGame(rows, cols, board);
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}
