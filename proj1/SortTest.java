public class SortTest
{
   private static boolean proclaim(boolean exp, String m){
       if(!exp){
           System.out.println(m);
       }
       return exp;
   }
   //test uniform arrays (just print)

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


   public static void main(String[] args){
      boolean validity = true;
      validity = validity && testBounds();
      if (validity)
          System.out.println("passed all tests");
      else
          System.out.println("oof, shits broke :(");
   }
}

