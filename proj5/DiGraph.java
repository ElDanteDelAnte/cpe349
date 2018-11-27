/**
 * Project 5.
 * 
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 11/27/18
 */

import java.util.LinkedList;

public class DiGraph
{
   private LinkedList[] adj;    //adjacency matrix

   //constructor, initalizes matrix
   public DiGraph(int N)
   {
   }

   //adds directed edge to the matrix
   public void addEdge(int from, int to)
   {
      //check if edge exists
      //TO is the neighbor
      //NOTE: vertexes are input as natural indexing
   }

   //removes edge from matrix
   public void deleteEdge(int from, int to)
   {
      //check if edge exists
      //remove
   }

   //computes number of edges in graph
   public int edgeCount()
   {
      //for each list in the array
         //add edge counts of the list
      return -1;
   }

   //give count of verticies of the graph
   public int vertexCount()
   {
      //this is literally the length of the array
      return adj.length;
   }

   //print the edges of the graph
   public void print()
   {
      /* NOTE: print with natural indexing */
      //for each list in the matrix
         //print #, " is connected to: ",
         //for each element in list
            //print #
            //if not last element
               //print ","
   }
}
