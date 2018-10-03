public class Sorts
{
   public static void selectionSort(int[] arr, int N)
   {
      int minIndex, tempVal;
      
      //find min
      for(int i = 0; i < N - 1; i++){
         minIndex = i;
         for(int j = i; j < N; j++){
            if(arr[j] < arr[minIndex]){
               minIndex = j;
            }    
         }    

      //swap
      tempVal = arr[i];
      arr[i] = arr[minIndex];
      arr[minIndex] = tempVal;
      }    
   }

   //initial call
   public static void mergeSort(int[] arr, int N)
   {
      mergeSort(arr, 0, N - 1);
   }

   //recursion
   private static void mergeSort(int[] list, int first, int last)
   {
      //base case
      if (first < last)
      {
         int middle = (first + last) / 2;
         mergeSort(list, first, middle);
         mergeSort(list, middle + 1, last);
         mergeSortedHalves(list, first, middle, last);   //merge halves
      }
   }

   //merge halves
   private static void mergeSortedHalves(int[] arr, int left, int middle, int right)
   {
      int[] temp = new int[right - left + 1];
      int index1 = left;
      int index2 = middle + 1;
      int index = 0;

      //begin merge
      while (index1 <= middle && index2 <= right)
      {
         //compare
         if (arr[index1] < arr[index2])
         {
            temp[index++] = arr[index1++];    //save smaller
         }
         else
         {
            temp[index++] = arr[index2++];
         }
      }
      
      //find unfinished
      while (index1 <= middle)
      {
         temp[index++] = arr[index1++];
      }

      while (index2 <= right)
      {
         temp[index++] = arr[index2++];
      }

      //copy over
      index = 0;
      for (int i = left; i < right; i++)
      {
         arr[i] = temp[index++];
      }
   }

   public static void quickSort(int[] arr, int N)
   {
   
   }
}
