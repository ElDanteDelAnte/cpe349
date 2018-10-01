import static org.junit.AssertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SortTest
{
    MyClass tester;

   //test uniform arrays (just print)

   //test bounds
   @test
   public void testBounds(){
      final int k = 1000;
      int[] arr1 = new int[k];
      int[] arr2 = new int[k];
      int[] arr3 = new int[k];
      SortTimes.randInit(arr1, arr2, arr3, k);
      for (int i = 0; i < k; i++){
         assertTrue(arr1[i] < k, "Upperbound broken");
         assertTrue(arr1[i] > 0, "Lowerbound broken");
         
      }
   }

   //test order
}
