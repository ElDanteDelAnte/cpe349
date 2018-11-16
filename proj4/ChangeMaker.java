/**
 * Project 4.
 *
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 11/7/18
 */

import java.util.Scanner;
import java.util.Arrays;

public class ChangeMaker
{
   public static int[] change_DP(int n, int[] d)
   {
      /* Definitions */
      int[] count = new int[d.length];       //array B
      int[] minCoins = new int[n + 1];       //array C
      int[] coinTaken = new int[n + 1];      //array A
      int j;

      minCoins[0] = 0;  //base case

      /* Solve sub-problems */
      //fill out minCoins
      for (j = 1; j <= n; j++)
      {
         int currMin = n;

         //find best coin
         for (int i = 0; i < d.length; i++)
         {
            //take coin
            if ((j >= d[i]) && (minCoins[j - d[i]] < currMin))
            {
               //System.out.print(minCoins[j - d[i]] + " ");
               currMin = minCoins[j - d[i]];
               coinTaken[j] = i;
            }
         }
         //System.out.print(currMin + " ");

         minCoins[j] = currMin + 1;
      }

      /* Construct optimal solution */
      j = n;
      while (j > 0)
      {
         int coinType = coinTaken[j];
         count[coinType]++;
         j -= d[coinType];
      }

      //TESTING
      //System.out.println(Arrays.toString(coinTaken));
      //System.out.println(Arrays.toString(minCoins));
      //System.out.println(Arrays.toString(count));
      return count;
   }
   
   //print results of the DP algorithm
   private static void printCoins(int n, int[] d, int[] counts)
   {
      System.out.println("Amount: " + n);
      
      //print each coin
      System.out.print("Optimal distribution: ");
      int c = 0;
      boolean hasPrinted = false;
      for (int i = 0; i < d.length; i++)
      {
         //print coin count and size
         if (counts[i] > 0)
         {
            //print + if needed
            if (hasPrinted)
               System.out.print(" + ");

            hasPrinted = true;
            System.out.print(counts[i] + "*" + d[i] + "c");
         }

         //tally total
         c += counts[i];
      }

      System.out.println();
      System.out.println("Optimal coin count: " + c);

      System.out.println();
   }

   public static int[] change_greedy(int n, int[] d)
   {
      int[] count = new int[d.length];
      int j = n;

      //iterate across coin types
      for (int i = 0; i < d.length; i++)
      {
         //take as many as you can
         while (j >= d[i])
         {
            j -= d[i];
            count[i]++;
         }
      }

      return count;
   }

   public static void main(String[] args)
   {
      /* Receive input */
      Scanner input = new Scanner(System.in);
      System.out.println("Number of coin types: ");
      int k = input.nextInt();
      int[] d = new int[k];

      for (int i = 0; i < k; i++)
         d[i] = input.nextInt();

      System.out.println("Enter a positive amount to be changed (enter 0 to quit):");

      int n = input.nextInt(); 
      while (n > 0)
      {
         //Dynamic Programing
         int[] change = change_DP(n, d);
         System.out.println("DP algorithm results");
         printCoins(n, d, change);

         //Greedy Algorithm
         change = change_greedy(n, d);
         System.out.println("Greedy algorithm results");
         printCoins(n, d, change);
         
         //next input
         System.out.println("Enter a positive amount to be changed (enter 0 to quit):");
         n = input.nextInt();
      }

      System.out.println("Thanks for playing. Good Bye.");
   }
}
