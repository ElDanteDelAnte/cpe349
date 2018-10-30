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
            t1[i] = fileReader.nextInt();

         //add entry and exit times to first and last stations? (might help)

         //actually run the method
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
      //output result
   }
}
