/**
 * A class for multiplying matricies via recursion.
 *
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 10/15/18
 */

public class MatrixProduct
{
   private static int[][] addMatrices(int[][] product1, int[][] product2){
      int n = product1.length;
      int[][] C = new int[n][n];
      for (int i = 0; i < n; i++){
          for(int j = 0; j < n; j++){
             C[i][j] = product1[i][j] + product2[i][j];
          }    
      }
      return C;
   }
   private static int[][] subMatrices(int[][] product1, int[][] product2){
      int n = product1.length;
      int[][] C = new int[n][n];
      for (int i = 0; i < n; i++){
          for(int j = 0; j < n; j++){
             C[i][j] = product1[i][j] - product2[i][j];
          }    
      }
      return C;
   }
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
      return matrixProduct_DAC(A, 0, 0, B, 0, 0, A.length);
   }

   private static int[][] matrixProduct_DAC(int[][] A, int startrowA, int startcolA,
                                            int[][] B, int startrowB, int startcolB, int n){
      int[][] C = new int[n][n];
      //Base case
      if (n == 1){
          C[0][0]= A[startrowA][startcolA] * B[startrowB][startcolB];
      }
      //Get quadrants of product matrix
      else{
          int[][] C11, C12, C21, C22;
          
          C11 = addMatrices(matrixProduct_DAC(A, startrowA, startcolA, 
                                              B, startrowB, startcolB, 
                                              n/2),
                            matrixProduct_DAC(A, startrowA, startcolA + (n/2),
                                              B, startrowB + (n/2), startcolB,
                                              n/2));

          C12 = addMatrices(matrixProduct_DAC(A, startrowA, startcolA,
                                              B, startrowB, startcolB + (n/2),
                                              n/2),
                            matrixProduct_DAC(A, startrowA, startcolA + (n/2),
                                              B, startrowB + (n/2), startcolB + (n/2), 
                                              n/2));

          C21 = addMatrices(matrixProduct_DAC(A, startrowA + (n/2), startcolA,
                                              B, startrowB, startcolB, 
                                              n/2),
                            matrixProduct_DAC(A, startrowA + (n/2), startcolA + (n/2),
                                              B, startrowB + (n/2), startcolB, 
                                              n/2));

          C22 = addMatrices(matrixProduct_DAC(A, startrowA + (n/2), startcolA,
                                              B, startrowB, startcolB + (n/2),
                                              n/2),
                            matrixProduct_DAC(A, startrowA + (n/2), startcolA + (n/2),
                                              B, startrowB + (n/2), startcolB + (n/2), 
                                              n/2));
          //Fill product matrix by quadrant
          //C11
          for (int i = 0; i < n/2; i++){
              for (int j = 0; j < n/2; j++){
                  C[i][j] = C11[i][j];
              }
          }
          
          //C12
          for (int i = 0; i < n/2; i++){
              for (int j = 0; j < n/2; j++){
                  C[i][j + n/2] = C12[i][j];
              }
          }

          //C21
          for (int i = 0; i < n/2; i++){
              for (int j = 0; j < n/2; j++){
                  C[i + n/2][j] = C21[i][j];
              }
          }

          //C22
          for (int i = 0; i < n/2; i++){
              for (int j = 0; j < n/2; j++){
                  C[i + n/2][j + n/2] = C22[i][j];
              }
          }
       }
       return C;
   }
   public static int[][] matrixProduct_Strassen(int[][] A, int[][] B)
   {
      validityCheck(A, B);
      int[][] product = new int[A.length][A.length];
      return product;
   }
}
