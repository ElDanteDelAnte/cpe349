/**
 * A class for multiplying matricies via recursion.
 *
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 10/15/18
 */

public class MatrixProduct
{
   private static void validityCheck(int[][] A, int[][] B) //throws IllegalArgumentException
   {
      int n = A.length;
      if (n != A[0].length ||
          n != B.length ||
          n != B[0].length)
         throw new IllegalArgumentException("Matricies not same size squares");

      boolean pow2 = n > 0 && ((n & (n - 1)) == 0);
      if (!pow2)
         throw new IllegalArgumentException("Dimensions not power of 2");
   }

   public static int[][] matrixProduct_DAC(int[][] A, int[][] B)
   {
      validityCheck(A, B);
      int[][] product = new int[A.length][A.length];
      return product;
   }

   public static int[][] matrixProduct_Strassen(int[][] A, int[][] B)
   {
      validityCheck(A, B);
      int[][] product = new int[A.length][A.length];
      return product;
   }
}
