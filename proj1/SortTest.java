import java.util.Arrays;

public class SortTest
{
   private static boolean proclaim(boolean exp, String m){
       if(!exp){
           System.out.println(m);
       }
       return exp;
   }
   //test uniform arrays (just print)
   public static boolean testInit(){
      final int k = 1000;
      boolean v1 = true;
      int[] arr1 = new int[k];
      int[] arr2 = new int[k];
      int[] arr3 = new int[k];
      SortTimes.randInit(arr1, arr2, arr3, k);
      v1 = v1 && proclaim(arr1[25] == arr2[25], "1 and 2 mismatch");
      v1 = v1 && proclaim(arr2[25] == arr3[25], "2 and 3 mismatch");
      return v1;
   }
   //test bounds
   public static boolean testBounds(){
      final int k = 1000;
      boolean v1 = true;
      int[] arr1 = new int[k];
      int[] arr2 = new int[k];
      int[] arr3 = new int[k];
      SortTimes.randInit(arr1, arr2, arr3, k);
      for (int i = 0; i < k; i++){
         v1 = v1 && proclaim(arr1[i] < k, "Upperbound broken");
         v1 = v1 && proclaim(arr1[i] >= 0, "Lowerbound broken");
         
      }
      return v1;
   }

   //test order
   public static boolean testOrder(int[] arr1, int N){
      boolean v1 = true;
      final int k = 1000;
      for(int i = 0; i < (N - 1); i++){
         if (arr1[i] > arr1[i+1]){
            System.out.println("Problem at index " + i + ": " + arr1[i]
               + ", " + (i + 1) + ": " + arr1[i + 1]);
            return false;
         }
      }    
      return true;
   }

   public static void main(String[] args){
      boolean validity = true;
      final int k = 1001;
      int[] arr1 = new int[k];
      int[] arr2 = new int[k];
      int[] arr3 = new int[]{7, 6, 6, 5, 4, 3, 1};
      //SortTimes.randInit(arr1, arr1, arr3, k);
      //Sorts.selectionSort(arr1, k);
      //Sorts.mergeSort(arr2, 7);
      Sorts.quickSort(arr3, 7);
      validity = validity && testBounds();
      validity = validity && testInit();
      //validity = validity && testOrder(arr1, k);
      //validity = validity && testOrder(arr2, k);
      validity = validity && testOrder(arr3, 7);
      if (validity)
          System.out.println("passed all tests");
      else
          System.out.println("oof, shits broke :(");
   }
}
