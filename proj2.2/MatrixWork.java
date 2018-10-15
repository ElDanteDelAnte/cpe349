/**
 * Multiplies two matricies.
 *
 * @author Chris Pestano pestano
 * @author Sean Reddell sreddell
 * @version 10/15/18
 */

import java.util.Scanner;
import java.io.*; 

public class MatrixWork
{

   /*
   public static int[][] matrixProduct(int[][] A, int[][] B) throws IllegalArgumentException {
      //try catch block? throw IllegalArgumentException if A[0].length != B.length
      int n = A.length;
      int k = B.length;
      int m = B[0].length;

      //incompatible dimensions
      if (A[0].length != k)
         throw new IllegalArgumentException();

      //DefineC[1..n, 1..m], 
      int[][] product = new int[n][m];

      //initialize all cells with 0 // C has n rows and m columns
      for (int i = 0; i < n; i++)
      {
         for (int j = 0; j < m; j++)
            product[i][j] = 0;
      }


      //for i = 1 to n
      for (int i = 0; i < n; i++)
      {
         //for j = 1 to m
         for (int j = 0; j < m; j++)
         {
            //for l = 1 to k 
            for (int l = 0; l < k; l++)
            {
               //C[i, j] = C[i, j] + A[i,l] * B[l, j]
               product[i][j] = product[i][j] + A[i][l] * B[l][j];
            }
         }
      }
      return product;
   }
   */

   private static void printMatrix(int[][] product)
   {
      //output results (nested forloop of prints?)
      for (int i = 0; i < product.length; i++)
      {
         for (int j = 0; j < product[0].length; j++)
         {
            System.out.print(product[i][j] + " ");
         }

         System.out.println();
      }
   }

   public static void main(String[] args){
      //initialize scanner for user i/o and another for file reading
      Scanner input = new Scanner(System.in);
      Scanner fileReader = new Scanner("dummy");

      //prompt user for input and scan user Input
      System.out.println("File name:");
      String filename = input.next();

      int[][] product = new int[1][1];

      //Open file
      try
      {
         File file = new File(filename);
         fileReader = new Scanner(file);
         
         //scan two ints as sizes for first  array
         int row, col;
         row = fileReader.nextInt();
         col = fileReader.nextInt();

         //create 2d array given scanned values
         int[][] matrix1 = new int[row][col];

         //create nested for loop where they terminate at array dimensions?
         for (int i = 0; i < row && fileReader.hasNextInt(); i++)
         {
            for (int j = 0; j < col && fileReader.hasNextInt(); j++)
            {
               //scan file and fill out array as needed. (nextInt()?)
               matrix1[i][j] = fileReader.nextInt();
            }
         }

         //scan two ints as sizes for second array
         row = fileReader.nextInt();
         col = fileReader.nextInt();

         //create 2d array given scanned values 
         int[][] matrix2 = new int[row][col];

         //create nested for loop...
         for (int i = 0; i < row && fileReader.hasNextInt(); i++)
         {
            for (int j = 0; j < col && fileReader.hasNextInt(); j++)
            {
               //scan file and fill out array as needed. (nextInt()?)
               matrix2[i][j] = fileReader.nextInt();
            }
         }

         //call matrixProduct
         product = MatrixProduct.matrixProduct_DAC(matrix1, matrix2);
         printMatrix(product);
         product = MatrixProduct.matrixProduct_Strassen(matrix1, matrix2);
         printMatrix(product);
      }
      catch (IOException e)
      {
         System.out.println("Error reading file: " + e.getMessage());
      }
      catch(IllegalArgumentException e)
      {
         System.out.println("Illegal matrix dimensions" );
      }

      //close scanners
      input.close();
      fileReader.close();

      //println after inner forloop to start next row in 2d array
   }
}
