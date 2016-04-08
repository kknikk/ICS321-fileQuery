/**
 * Tester 
 */
import java.io.*;
import java.util.*;

public class AutoRun
{
  public static void main(String[] args) throws IOException
  {
    int commandMode = 0;
    Assignment1 student_assignment;
    ArrayList<String> commands = new ArrayList<String>();
    //Input is assumed to be a file filled with commands. Do a base case if no args.
    if (args.length==1)
    {
      commandMode = 1;
    }

    //Attempt to link in student code. Fail gracefully if student has failed.
    try
    {
      student_assignment = new Assignment1();
    }
    catch (Exception e)
    {
      System.out.println("Failed to create Assignment1 object. WHY MUST LIFE BE SO HARD!?");
      System.out.println(e);
      return;
    }

    //Base case for no command file being provided.
    if (commandMode==0)
    {
      commands.add("naiveLoad fiveH.txt");
      commands.add("naiveSearchEq 3 F");
      commands.add("naiveSearchGtr 4 15000");
      commands.add("bufferLoad fiveH.txt");
      commands.add("bufferSearchEq 3 F");
      commands.add("bufferSearchGtr 4 15000");
      commands.add("objectLoad fiveH.txt");
      commands.add("objectSearchEq 3 F");
      commands.add("objectSearchGtr 4 15000");
    }
    else if (commandMode==1)
    {
      try
      {
        String line = new String();
        BufferedReader commandFile = new BufferedReader(new FileReader(args[0]));
        while ((line=commandFile.readLine())!=null)
        {
          commands.add(line);
        }
      }
      catch (Exception e)
      {
        System.out.println("Many errors have occured and all is doomed!");
        System.out.println(e);
      }
    }

    //Execute commands. Lots of cases here, but each case is pretty simple.
    for (int i = 0; i<commands.size(); i++)
    {
      String[] command = commands.get(i).split(" ");
      ArrayList<String> results = new ArrayList<String>();
      //Switch based on first token
      switch (command[0].toLowerCase())
      {
        //Load cases
        case "naiveload" :
        student_assignment.naiveLoad(command[1]);
        break;
        case "bufferload" :
        student_assignment.bufferLoad(command[1]);
        break;
        case "objectload" :
        student_assignment.objectLoad(command[1]);
        break;

        //Fetch cases
        case "naivesearcheq" :
        results = student_assignment.naiveSearchEq(Integer.parseInt(command[1]), command[2]);
        for (String s : results)
        {
          System.out.println(s);
        }
        break;
        case "naivesearchgtr" :
        results = student_assignment.naiveSearchGtr(Integer.parseInt(command[1]), command[2]);
        for (String s : results)
        {
          System.out.println(s);
        }
        break;
        case "buffersearcheq" :
        results = student_assignment.bufferSearchEq(Integer.parseInt(command[1]), command[2]);
        for (String s : results)
        {
          System.out.println(s);
        }
        break;
        case "buffersearchgtr" :
        results = student_assignment.bufferSearchGtr(Integer.parseInt(command[1]), command[2]);
        for (String s : results)
        {
          System.out.println(s);
        }
        break;
        case "objectsearcheq" :
        results = student_assignment.objectSearchEq(Integer.parseInt(command[1]), command[2]);
        for (String s : results)
        {
          System.out.println(s);
        }
        break;
        case "objectsearchgtr" :
        results = student_assignment.objectSearchGtr(Integer.parseInt(command[1]), command[2]);
        for (String s : results)
        {
          System.out.println(s);
        }
        break;

        //Default case
        default :
        System.out.println("Command not understood");
        System.out.println("Command contents = "+Arrays.toString(command));
      }
    }
  }
}
