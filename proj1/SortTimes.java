public class SortTimes
{
   //time trackers
   private static long selectionTime(int[] arr, int N)
   {
      long endTime, startTime;
      startTime = System.nanoTime();
      Sorts.selectionSort(arr, N);
      endTime = System.nanoTime();
      return (endTime - startTime) / 1000000;
   }

   private static long mergeTime(int[] arr, int N)
   {
      long endTime, startTime;
      startTime = System.nanoTime();
      Sorts.mergeSort(arr, N);
      endTime = System.nanoTime();
      return (endTime - startTime) / 1000000;
   }

   private static long quickTime(int[] arr, int N)
   {
      long endTime, startTime;
      startTime = System.nanoTime();
      Sorts.quickSort(arr, N);
      endTime = System.nanoTime();
      return (endTime - startTime) / 1000000;
   }

   //generates random lists to be sorted
   private static void randInit(int[] a1, int[] a2, int[] a3, int N)
   {
      for (int i = 0; i < N; i++)
      {
         int rand = (int)((Math.random()) * N);
         a1[i] = rand;
         a2[i] = rand;
         a3[i] = rand;
      }
   }

   public static void main(String[] args)
   {
      private final long size = 160000;
      private final long start = 5000;
      private final int iters = 5;

      int[] a1 = new int[size];
      int[] a2 = new int[size];
      int[] a3 = new int[size];
      long ss, ms, qs;

      //5 times for each N
      for (int N = start; N <= size; N *= 2)
      {
         for (int i = 0; i < iters; i++)
         {
            //generate random
            randInit(a1, a2, a3, N);

            //call each sort
            ss = selectionTime(a1, N);
            ms = mergeTime(a2, N);
            qs = quickTime(a3, N);

            //print times
            System.out.print("N=" + N + ": ");
            System.out.print("T_ss=" + ss + ", ");
            System.out.print("T_ms=" + ms + ", ");
            System.out.print("T_qs=" + qs);
            System.out.println();
         }
         System.out.println();
      }
   }
}
