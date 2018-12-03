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
   /* PART 1 & 2 */
   private static Scanner input;

   //display the user menu
   private static void printMenu()
   {
      System.out.println("Choose one of the following operations:");
      System.out.println("- add edge (enter a)");
      System.out.println("- delete edge (enter d)");
      System.out.println("- edge count (enter e)");
      System.out.println("- vertex count (enter v)");
      System.out.println("- print graph (enter p)");
      System.out.println("- topological sort (enter t)");
      System.out.println("- detect path (enter i)");
      System.out.println("- path length (enter l)");
      System.out.println("- print path (enter s)");
      System.out.println("- print BFS tree (enter b)");
      System.out.println("- Quit (enter q)");
   }

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
      input.nextLine();                    //flush input
      System.out.println("Removed edge from " + from + " to " + to);
   }

   private static void sortGraph(DiGraph graph)
   {  
      System.out.println("Sorting the graph:");
      try{
         int[] sorted = graph.topSort();
         for (int i = 0; i < graph.vertexCount(); i++){
            System.out.print(sorted[i]);
            if(i < graph.vertexCount() - 1){
               System.out.print(", ");
            }

         }    
      System.out.println();
      }
      catch(IllegalArgumentException e){
         System.out.println("Cycle detected");
      }
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
         //print the sorted graph
         case 't':
            sortGraph(graph);
            break;
         //detect path
         case 'i':
            detectPath(graph);
            break;
         //find length of shortest path
         case 'l':
            findLength(graph);
            break;
         //print shortest path
         case 's':
            displayPath(graph);
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
      input.nextLine();                      //flush input
      DiGraph graph = new DiGraph(N);

      //print menu
      printMenu();

      //user operations
      String cmd = "dummy";

      while (!cmd.equals("q"))
      {
         System.out.print("Enter command: ");
         cmd = input.nextLine();             //flush input
         if (!cmd.equals("q"))
            parse(cmd, graph);
      } 

      System.out.println("Quitting. Have a nice day.");
   }

   /* PART 3 & 4 */
   private static void detectPath(DiGraph graph)
   {
      System.out.println("Give 'from' then 'to' verticies:");
      int from = input.nextInt();
      int to = input.nextInt();
      input.nextLine();                      //flush input

      if (graph.isTherePath(from, to))
         System.out.println("There is a path from " + from + " to " + to);
      else
         System.out.println("No path from " + from + " to " + to);
   }

   //prints -1 as length if no path exists
   private static void findLength(DiGraph graph)
   {
      System.out.println("Give 'from' then 'to' verticies:");
      int from = input.nextInt();
      int to = input.nextInt();
      input.nextLine();                      //flush input

      System.out.println("Path length: " + graph.lengthOfPath(from, to));
   }

   //prints shortest path (if it exists)
   private static void displayPath(DiGraph graph)
   {
      System.out.println("Give 'from' then 'to' verticies:");
      int from = input.nextInt();
      int to = input.nextInt();
      input.nextLine();                      //flush input

      graph.printPath(from, to);
   }
}
