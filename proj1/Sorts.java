import java.util.Arrays;
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
      quickSort(arr, 0, N - 1);
   }

   private static void quickSort(int[] list, int first, int last)
   {
      if (first < last)
      {
         setPivotToEnd(list, first, last);
         int pivotIndex = splitList(list, first, last);
         quickSort(list, first, pivotIndex - 1);
         quickSort(list, pivotIndex + 1, last);
      }
   }
   
   private static void setPivotToEnd(int[] arr, int left, int right)
   {
      int center = (left + right) / 2;
      int temp = 0;

      //compare first & center, swap smaller to left (if needed)
      if (arr[left] > arr[center])
      {
         temp = arr[left];
         arr[left] = arr[center];
         arr[center] = temp;
      }

      //compare first & last, swap smaller to left (if needed)
      if (arr[left] > arr[right])
      {
         temp = arr[left];
         arr[left] = arr[right];
         arr[right] = temp;
      }

      //compare center & last, swap larger to center (if needed)
      if (arr[center] < arr[right])
      {
         temp = arr[center];
         arr[center] = arr[right];
         arr[right] = temp;
      }
      System.out.println("After setPivotToEnd" +  Arrays.toString(arr));
   }

   //returns index of pivot
   private static int splitList(int[] arr, int left, int right)
   {
      int indexL = left;         //set indexL to first cell of list-segment
      int indexR = right - 1;    //set indexR to second-from-last cell of list segment
      int pivot = arr[right];    //set pivot to last element of list-segment

      //WHILE two indicies don't cross over (includes when they are equal)
      while (indexL <= indexR)
      {
         //move indexL right as long as vals < pivot
         while (arr[indexL] < pivot)
         {
            indexL++;
         }

         //move indexR left as long as vals > pivot  (check indicies first!)
         while (indexL <= indexR && arr[indexR] > pivot)
         {
            indexR--;
         }

         //IF indecies don't cross over (including equal)
         if (indexL <= indexR)
         {
            //swap values at indexL and indexR
            int temp = arr[indexL];
            arr[indexL] = arr[indexR];
            arr[indexR] = temp;

            //inc indexL, dec indexR
            indexL++;
            indexR--;
         }
      }
      System.out.println("Pivot value preswap:" + pivot);
      //swap value at indexL with pivot
      int tempP = pivot;
      pivot = arr[indexL];
      arr[right] = pivot;
      arr[indexL] = tempP;
      System.out.println("After SplitList" + Arrays.toString(arr));
      //return indexL
      return indexL;
   }
}
