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

      public void printSolution()
      {
         int n = f1.length;
         System.out.println("Fastest time is: " + ((lastLine == 1) ? f1[n - 1] :
                                                                     f2[n - 1]));
         System.out.println();
         System.out.println("The optimal route is:");

         printSolution(n - 1, lastLine);
      }

      //print method
      private void printSolution(int end, int line)
      {
         //base case
         //if (n < 1)
         //else

         if (end >= 0)
         {
            printSolution(end - 1, (line == 1) ? l1[end] : l2[end]);
            System.out.println("station " + (end + 1) + ", line " + line);
         }
      }
   }

   //actual algorithm
   public FactorySolution Compute(int[] a1, int[] a2, int[] t1, int[] t2,
                                  int e1, int e2, int x1, int x2, int n)
   {
      //add entry and exit times to first and last stations? (might help)
      a1[n - 1] += x1;
      a2[n - 1] += x2;
      a1[0] += e1;
      a2[0] += e2;

      //define time table and line table
      int[] f1 = new int[n];
      int[] f2 = new int[n];
      int[] l1 = new int[n];
      int[] l2 = new int[n];

      //on entry
      f1[0] = a1[0];
      f2[0] = a2[0];
      l1[0] = 0;
      l2[0] = 0;

      int stay1, stay2, trans1, trans2;

      for (int i = 1; i < n; i++)
      {
         /* fill f1 */
         //add transfer to next station across
         stay1 = f1[i - 1] + a1[i];
         trans1 = f2[i - 1] + t2[i - 1] + a1[i];

         //compare with next station
         if (trans1 < stay1)
         {
            //record each min and line
            f1[i] = trans1;
            l1[i] = 2;
         }
         else
         {
            //record each min and line
            f1[i] = stay1;
            l1[i] = 1;
         }
         /* fill f2 */
         //add transfer to next station across
         stay2 = f2[i - 1] + a2[i];
         trans2 = f1[i - 1] + t1[i - 1] + a2[i];

         //compare with next station
         if (trans2 < stay2)
         {
            //record each min and line
            f2[i] = trans2;
            l2[i] = 1;
         }
         else
         {
            //record each min and line
            f2[i] = stay2;
            l2[i] = 2;
         }
      }

      //determine lastLine
      int lastLine = (f1[n - 1] < f2[n - 1]) ? 1 : 2;

      //create and return result
      return new FactorySolution(f1, f2, l1, l2, lastLine);
   }

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      FactoryProblem problem = new FactoryProblem();

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
         FactorySolution solution = problem.Compute(a1, a2, t1, t2, e1, e2, x1, x2, n);

         //output result
         solution.printSolution();
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }

   }
}
