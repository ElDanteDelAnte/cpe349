public class SortCounts
{
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
      final int size = 12800;
      final int start = 100;
      final int iters = 100;

      int[] a1 = new int[size];
      int[] a2 = new int[size];
      int[] a3 = new int[size];
      long ss, ms, qs;

      //double N
      for (int N = start; N <= size; N *= 2)
      {
         ss = ms = qs = 0;
         //sample 100 at each N
         for (int i = 0; i < iters; i++)
         {
            //generate random
            randInit(a1, a2, a3, N);

            //call each sort
            ss += Sorts1.selectionSort(a1, N);
            ms += Sorts1.mergeSort(a2, N);
            qs += Sorts1.quickSort(a3, N);
         }
         //print counts
         System.out.print("N=" + N + ": ");
         System.out.print("C_ss=" + ss / iters + ", ");
         System.out.print("C_ms=" + ms / iters + ", ");
         System.out.print("C_qs=" + qs / iters);
         System.out.println();
      }
   }
}
