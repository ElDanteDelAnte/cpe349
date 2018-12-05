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
   /* PART 1 & 2 */
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
      if(i != n){
         throw new IllegalArgumentException();
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

   /* PART 3 & 4 */
   //built in BFS
   private class VertexInfo
   {
      public int dist = -1;
      public int parent = -1;
   }

   //S is a natural index
   private VertexInfo[] BFS(int s)
   {
      VertexInfo[] nodes = new VertexInfo[vertexCount()];
      //initialize predecessors and distance
      for (int i = 0; i < vertexCount(); i++)
      {
         nodes[i] = new VertexInfo();
      }

      nodes[s - 1].dist = 0;        //set source to 0
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(new Integer(s - 1));

      while (q.peek() != null)
      {
         Integer u = q.remove();

         for (Integer v : adj[u])
         {
            if (nodes[v].dist < 0)
            {
               nodes[v].dist = nodes[u].dist + 1;
               nodes[v].parent = u; 
               q.add(v);
            }
         }
      }

      return nodes;
   }

   //detects path between verticies
   public boolean isTherePath(int from, int to)
   {
      VertexInfo[] search = BFS(from);    //S is a natural index

      return search[to - 1].dist >= 0;
   }

   //count edges between verticies
   public int lengthOfPath(int from, int to)
   {
      VertexInfo[] search = BFS(from);    //S is a natural index
      return search[to - 1].dist;
   }

   //display path between verticies
   public void printPath(int from, int to)
   {
      //check for path (natural indexing)
      if (!isTherePath(from, to))
      {
         System.out.println("There is no path");
         return;
      }
      //else: path exists
      VertexInfo[] search = BFS(from);    //call with natural index
      String output = "";
      while (from != to)
      {
         output = "->" + to + output;
         to = search[to - 1].parent + 1;  //from natural indexing and back
      }
      output = from + output;
      System.out.println(output);
   }

   //node for the BFS tree
   private class TreeNode
   {
      public int v;  //non-natural index
      public LinkedList<TreeNode> children;

      public TreeNode(int v)
      {
         this.v = v;
         this.children = new LinkedList<TreeNode>();
      }
   }

   //S is natural index
   private TreeNode buildTree(int s)
   {
      //build nodes
      TreeNode root = new TreeNode(s);

      return root;
   }

   //S is natural index
   public void printTree(int s)
   {
      //build tree
      TreeNode root = buildTree(s);

      printNode(root, 0);
   }

   //recursive print call
   private void printNode(TreeNode root, int depth)
   {
      //indentation
      for (int i = 0; i < depth; i++)
      {
         System.out.print("    ");
      }
      //print self (as natural index)
      System.out.println(root.v + 1);

      //print children
      for (TreeNode child : root.children)
      {
         printNode(child, depth + 1);
      }
   }
}
