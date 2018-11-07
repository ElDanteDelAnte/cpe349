/**
 * Project 4.
 *
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 11/7/18
 */

public class ChangeMaker
{
   public static int[] change_DP(int n, int[] d)
   {
   }

   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      System.out.println("Number of coin types: ");
      int k = input.nextInt();
      int[] d = new int[k];

      for (int i = 0; i < k; i++)
         d[i] = input.nextInt();

      System.out.println("Value for n: ");

      int n = input.nextInt(); 
      while (n > 0)
      {
         int[] change = change_DP(n, d);
      }
   }
}
