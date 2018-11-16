/**
 * Project 4.
 *
 * Tester class.
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 11/14/18
 */

public class Tester
{
   private static boolean match(int[] dp, int[] greedy)
   {
      if (dp.length != greedy.length)
         return false;

      for (int i = 0; i < dp.length; i++)
         if (dp[i] != greedy[i])
            return false;
             
      return true;
   }

   private static int test(int[] d)
   {
      int matches = 0;

      for (int i = 1; i <= 200; i++)
      {
         int[] dp = ChangeMaker.change_DP(i, d);
         int[] greedy = ChangeMaker.change_greedy(i, d);
         if (match(dp, greedy))
            matches++;
      }

      return matches;
   }

   public static void main(String[] args)
   {
      int[] US = {100, 50, 25, 10, 5, 1};
      int[] SOVIET = {100, 50, 20, 15, 10, 5, 3, 2, 1};
      int[] POW2 = {64, 32, 16, 8, 4, 2, 1};
      int[] SANSNICK = {100, 50, 25, 10, 1};
      int[] WEIRD = {66, 35, 27, 18, 10, 1};
      
      int[][] sets = new int[5][];
      sets[0] = US;
      sets[1] = SOVIET;
      sets[2] = POW2;
      sets[3] = SANSNICK;
      sets[4] = WEIRD;

      System.out.println("Testing change_DP and change_greedy algorithms");
      for (int i = 0; i < sets.length; i++)
      {
         int matches = test(sets[i]);
         System.out.println("Testing set" + (i + 1) + ": " + matches + " in 200 tests");
      }
   }
}
