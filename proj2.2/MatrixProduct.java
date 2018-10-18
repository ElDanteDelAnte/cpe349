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

   private static int[][] addQuad(int[][] A, int startrowA, int startcolA,
                                  int[][] B, int startrowB, int startcolB,
                                  int n)
   {
      int[][] S = new int[n][n];

      for (int i = 0; i < n; i++)
      {
         for (int j = 0; j < n; j++)
         {
            S[i][i] = A[startrowA + i][startcolA + j] + B[startrowB + i][startcolB +j];
         }
      }
      return S;
   }

   private static int[][] subQuad(int[][] A, int startrowA, int startcolA,
                                  int[][] B, int startrowB, int startcolB,
                                  int n)
   {
      int[][] S = new int[n][n];

      for (int i = 0; i < n; i++)
      {
         for (int j = 0; j < n; j++)
         {
            S[i][i] = A[startrowA + i][startcolA + j] - B[startrowB + i][startcolB +j];
         }
      }
      return S;
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
                                            int[][] B, int startrowB, int startcolB, 
                                            int n){
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
      return matrixProduct_Strassen(A, 0, 0, B, 0, 0, A.length);
   }

   public static int[][] matrixProduct_Strassen(int[][] A, int startrowA, int startcolA,
                                                int[][] B, int startrowB, int startcolB,
                                                int n)
   {
      int[][] C = new int[A.length][A.length];
      //base case
      if (n == 1)
      {
         C[0][0]= A[startrowA][startcolA] * B[startrowB][startcolB];
      }
      //Get quadrants of product matrix
      else
      {
         int[][] C11, C12, C21, C22, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, P1, P2, P3, P4, P5, P6, P7;

         /* Calculate S values */
         //S1 = B12 - B22
         S1 = subQuad(B, startrowB, startcolB + (n/2),
                      B, startrowB + (n/2), startcolB + (n/2),
                      n/2);

         //S2 = A11 + A12
         S2 = addQuad(A, startrowA, startcolA,
                      A, startrowA, startcolA + (n/2),
                      n/2);

         //S3 = A21 + A22
         S3 = addQuad(A, startrowA + (n/2), startcolA,
                      A, startrowA + (n/2), startcolA + (n/2),
                      n/2);

         //S4 = B21 - B11
         S4 = subQuad(B, startrowB + (n/2), startcolB,
                      B, startrowB, startcolB,
                      n/2);

         //S5 = A11 + A22
         S5 = addQuad(A, startrowA, startcolA,
                      A, startrowA + (n/2), startcolA +(n/2),
                      n/2);

         //S6 = B11 + B22
         S6 = addQuad(B, startrowB, startcolB,
                      B, startrowB + (n/2), startcolB + (n/2),
                      n/2);

         //S7 = A12 - A22
         S7 = subQuad(A, startrowA, startcolA + (n/2),
                      A, startrowA + (n/2), startcolA + (n/2),
                      n/2);

         //S8 = B21 + B22
         S8 = addQuad(B, startrowB + (n/2), startcolB,
                      B, startrowB + (n/2), startcolB + (n/2),
                      n/2);

         //S9 = A11 - A21
         S9 = subQuad(A, startrowA, startcolA,
                      A, startrowA + (n/2), startcolA,
                      n/2);

         //S10 = B11 + B12
         S10 = addQuad(B, startrowB, startcolB,
                       B, startrowB, startcolB + (n/2),
                       n/2);
         
         /* Calculate P values */
         //P1 = A11 * S1
         P1 = matrixProduct_Strassen(A, startrowA, startcolA,
                                     S1, 0, 0,
                                     n/2);

         //P2 = S2 * B22
         P2 = matrixProduct_Strassen(S2, 0, 0,
                                     B, startrowB + (n/2), startcolB + (n/2), 
                                     n/2);

         //P3 = S3 * B11
         P3 = matrixProduct_Strassen(S3, 0, 0,
                                     B, startrowB, startcolB,
                                     n/2);

         //P4 = A22 * S4
         P4 = matrixProduct_Strassen(A, startrowA + (n/2), startcolA + (n/2),
                                     S4, 0, 0,
                                     n/2);

         //P5 = S5 * S6
         P5 = matrixProduct_Strassen(S5, 0, 0,
                                     S6, 0, 0,
                                     n/2);

         //P6 = S7 * S8
         P6 = matrixProduct_Strassen(S7, 0, 0,
                                     S8, 0, 0,
                                     n/2);

         //P7 = S9 * S10
         P7 = matrixProduct_Strassen(S9, 0, 0,
                                     S10, 0, 0,
                                     n/2);

         /* Calculate C quadrants */
         //C11 = P5 + P4 - (P2 - P6)
         //C12 = P1 + P2
         //C21 = P3 + P4
         //C22 = P5 + P1 - (P3 + P7)

         /* Fill C matrix */

      }
      return C;
   }
}
