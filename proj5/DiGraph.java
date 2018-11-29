/**
 * Project 5.
 * 
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 11/27/18
 */

import java.util.LinkedList;
import java.util.Queue;

public class DiGraph
{
   private LinkedList<Integer>[] adj;    //adjacency matrix

   //constructor, initalizes matrix
   public DiGraph(int N)
   {
      /* Create list */
      adj = (LinkedList<Integer>[]) new LinkedList[N];

      /* Fill list */
      for(int i=0; i<N; i++){
         adj[i] = new LinkedList<Integer>();
      }
   }

   //adds directed edge to the matrix
   public void addEdge(int from, int to)
   {
      LinkedList<Integer> temp = adj[from - 1];
      Integer e = new Integer(to - 1);

      //TO is the neighbor
      //check if edge exists
      if (!temp.contains(e)){
         temp.add(e);
      }    
      //NOTE: vertexes are input as natural indexing
   }

   //removes edge from matrix
   public void deleteEdge(int from, int to)
   {
      LinkedList<Integer> temp = adj[from - 1];
      Integer e = new Integer(to - 1);
      
      //check if edge exists
      //remove
      if (temp.contains(e)){
         temp.remove(e);
      }
   }

   //computes number of edges in graph
   public int edgeCount()
   {
      int sum = 0;
      //for each list in the array
      for( int i = 0; i < adj.length; i++){
         sum += adj[i].size();
      }
         //add edge counts of the list
      return sum;
   }

   //give count of verticies of the graph
   public int vertexCount()
   {
      //this is literally the length of the array
      return adj.length;
   }
   private int[] indegrees(){
      int n = vertexCount();
      int[] inArr = new int[n];
      for(int u = 0; u < n; u++){
        for(Integer v : adj[u]){
            inArr[v]++;
        }
      }
      return inArr;
   }

   public int[] topSort(){
      int n = vertexCount();
      int u;
      int[] inArr = indegrees();
      int[] sorted = new int[n];
      Queue<Integer> q = new LinkedList<Integer>();
      for (u = 0; u < n; u++){
         if (inArr[u] == 0){
             q.add(u);
         }
      }
      int i = 0;
      while (q.peek() != null){
         u = q.remove();
         sorted[i] = u + 1;
         i++;
         for(Integer v : adj[u]){
            inArr[v]--;
            if(inArr[v] == 0){
                q.add(v);
            }
         }
      }
      return sorted;
   }     

   //print the edges of the graph
   public void print()
   {
      /* NOTE: print with natural indexing */
      //for each list in the matrix
      for(int i = 0; i < adj.length; i++){
         //print #, " is connected to: ",
         System.out.print((i + 1)+ " is connected to:");
         //for each element in list
         for(int j = 0; j < adj[i].size(); j++){
            //print #
            System.out.print(" " + (adj[i].get(j) + 1));
            //if not last element
            if(j < adj[i].size() - 1){
               //print ","
               System.out.print(",");
            }
         }
         System.out.println();
      }

   }
}
