/**
 * Project 3.
 *
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 10/29/18
 */

import java.io.*;
import java.util.Scanner;

public class FactoryProblem
{
   private class FactorySolution
   {
      private int[] f1, f2, l1, l2;
      private int lastLine;

      public FactorySolution(int[] f1, int[] f2, int[] l1, int[] l2, int lastLine)
      {
         this.f1 = f1;
         this.f2 = f2;
         this.l1 = l1;
         this.l2 = l2;
         this.lastLine = lastLine;
      }

      //print method
      public void printSolution()
      {
         //for n = length; n > 0; n--
      }
   }

   //actual algorithm
   public static FactorySolution Compute(int[] a1, int[] a2, int[] t1, int[] t2,
                                         int e1, int e2, int x1, int x2, int n)
   {
      //add entry and exit times to first and last stations? (might help)
      //define time table and line table
      //for i = 0 to n
         //find min of each fork from top
            //add transfer to next station across
            //compare with next station
         //find min of each fork from bottom
            //add transfer to next station across
            //compare with next station
         //record each min and line

      //create and return result
   }

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);

      //prompt file name
      System.out.println("File name: ");
      String filename = in.next();

      //open file
      try (Scanner fileReader = new Scanner(new File(filename)))
      {
         //read file
         
         int n;
         int e1, e2, x1, x2;
         int[] a1, a2, t1, t2;

         //n
         n = fileReader.nextInt();

         //entry
         e1 = fileReader.nextInt();
         e2 = fileReader.nextInt();

         //exit
         x1 = fileReader.nextInt();
         x2 = fileReader.nextInt();

         //line 1
         a1 = new int[n];
         for (int i = 0; i < n; i++)
            a1[i] = fileReader.nextInt();

         //line 2
         a2 = new int[n];
         for (int i = 0; i < n; i++)
            a2[i] = fileReader.nextInt();

         //switch from line 1
         t1 = new int[n - 1];
         for (int i = 0; i < n - 1; i++)
            t1[i] = fileReader.nextInt();
         
         //switch from line 2
         t2 = new int[n - 1];
         for (int i = 0; i < n - 1; i++)
            t2[i] = fileReader.nextInt();


         //actually run the method

         //output result
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }

   }
}
