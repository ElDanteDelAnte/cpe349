/**
 * Project 5.
 *
 * @author Sean Reddell sreddell
 * @author Chris Pestano pestano
 * @version 11/27/18
 */

import java.util.Scanner;

public class DiGraphTest
{
   private static Scanner input;

   //add an edge to the graph
   private static void promptAdd(DiGraph graph)
   {
      System.out.print("Give 'from' then 'to' verticies: ");
      int from = input.nextInt();
      int to = input.nextInt();
      graph.addEdge(from, to);
      input.nextLine();          //flush input
      System.out.println("Added edge from " + from + " to " + to);
   }

   //remove edge
   private static void promptDelete(DiGraph graph)
   {
      System.out.print("Give 'from' then 'to' verticies: ");
      int from = input.nextInt();
      int to = input.nextInt();
      graph.deleteEdge(from, to);
      input.nextLine();          //flush input
      System.out.println("Removed edge from " + from + " to " + to);
   }

   //interpret a user command
   private static void parse(String cmd, DiGraph graph)
   {
      if (cmd.length() > 1)
      {
         System.out.println("Command must be single character");
         return;
      }

      char c = cmd.charAt(0);

      //find command
      switch (c)
      {
         //add edge
         case 'a':
            promptAdd(graph);
            break;
         //delete edge
         case 'd':
            promptDelete(graph);
            break;
         //edge count
         case 'e':
            System.out.print("Edge count is: ");
            System.out.println(graph.edgeCount());
            break;
         //vertex count
         case 'v':
            System.out.print("Vertex count is: ");
            System.out.println(graph.vertexCount());
            break;
         //print graph
         case 'p':
            System.out.println("The graph is the following:");
            graph.print();
            break;
         //invalid input
         default:
            System.out.println("Command not recognized");
            break;

      }
   }

   public static void main(String[] args)
   {
      //input reader
      input = new Scanner(System.in);

      /* create graph */
      System.out.print("Enter number of verticies: ");
      int N = input.nextInt();
      input.nextLine();                //flush input
      DiGraph graph = new DiGraph(N);

      //print menu

      //user operations
      String cmd = "dummy";

      while (cmd != "q")
      {
         System.out.print("Enter command: ");
         cmd = input.nextLine();
         parse(cmd, graph);
      } 

      System.out.println("Quitting. Have a nice day.");
   }
}
