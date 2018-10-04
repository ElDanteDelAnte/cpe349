public class Sorts
{
   public static void selectionSort(int[] arr, int N)
   {
      int minIndex, tempVal;
      
      //find min
      for(int i = 0; i < N - 1; i++){
         minIndex = i;
         for(int j = i + 1; j < N; j++){
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
      for (int i = left; i <= right; i++, index++)
      {
         arr[i] = temp[index];
      }
   }

   //initial method
   public static void quickSort(int[] arr, int N)
   {
   
   }

   private static void quickSort(int[] list, int first, int last)
   {
      if (first < last)
      {
         setPivotToEnd(list, first, last);
         int pivotIndex = splitList(list, first, last);
         quickSort(list, first, pivotIndex - 1);
         quickSort(lost, pivotIndex + 1, last);
      }
   }
   
   private static void setPivotToEnd(arr, int left, int right)
   {
      int center = (left + right) / 2;
      //compare first & center, swap smaller to left (if needed)
      //compare first & last, swap smaller to left (if needed)
      //compare center & last, swap larger to center (if needed)
   }

   //returns index of pivot
   private static int splitList(int[] arr, int left, int right)
   {
      //set indexL to first cell of list-segment
      //set indexR to second-from-last cell of list segment
      //set pivot to last element of list-segment
      //WHILE two indicies don't cross over (includes when they are equal)
         //move indexL right as long as vals < pivot
         //move indexR left as long as vals > pivot  (check indicies first!)
         //IF indecies don't cross over (including equal)
            //swap values at indexL and indexR
            //inc indexL, dec indexR
      //swap value at indexL with pivot
      //return indexL
   }
}
