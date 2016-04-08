/*
 * @Author kurtnikaitani
 * @date 2/5/16
 * Loader and search methods for assignment 1
 */

import java.io.*;
import java.util.*;
import java.io.FileReader;

public class Assignment1 {

  // private variables
  private FileReader fr;
  private FileReader fr2;
  private FileReader input;
  private FileReader input2;
  private BufferedReader br;
  private BufferedReader br2;
  private BufferedReader br3;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private ObjectInputStream in2;


  // Loader methods
  
  
  /**
   * uses FileReader to load file
   * @param fileName the file to query
   */
  public void naiveLoad(String fileName) {
    
    long startTime = System.nanoTime();

    try {
      fr = new FileReader(fileName);
      fr2 = new FileReader(fileName);
      input = new FileReader(fileName);
      input2 = new FileReader(fileName);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("File Loaded");
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return;
  }


  /**
   * uses a buffered reader to load file
   * @param fileName the file to query
   */
  public void bufferLoad(String fileName) {
    long startTime = System.nanoTime();
    
    File file = new File(fileName);
    try {
      br = new BufferedReader(new FileReader(file));
      br3 = new BufferedReader(new FileReader(file));
    }
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Buffered File Loaded. \n");
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return;
  }


  /**
   * creates an object instance of the file with ObjectOutputStream and loads for querying
   * @param fileName the file to query
   * @throws IOException 
   */
  public void objectLoad(String fileName) throws IOException {
    
    long startTime = System.nanoTime();

    
    File file = new File(fileName);
    int commaCounter;
    String line;
    String target;
    int intTarget;
    float floatTarget;
    int j = 0;

    FileOutputStream fout = new FileOutputStream("OutputTest.txt");
    out = new ObjectOutputStream(fout);

    try {
      br2 = new BufferedReader(new FileReader(file));

      while ((line = br2.readLine()) != null) {
        Line row = new Line();
        commaCounter = 0;
        j = 0;
        
        // check line for
        while (j < line.length() - 1) {
          target = "";

          // first name
          if (commaCounter == 0) {
            while (line.charAt(j) != ',') {
              target = target + line.charAt(j);
              j++;
            }
            row.setFirstName(target);
          }
          // last name
          if (commaCounter == 1) {
            while (line.charAt(j) != ',') {
              target = target + line.charAt(j);
              j++;
            }
            row.setLastName(target);

          }
          // gender
          if (commaCounter == 2) {
            while (line.charAt(j) != ',') {
              target = target + line.charAt(j);
              j++;
            }
            char c = target.charAt(0);
            row.setGender(c);
          }
          // fourth row
          if (commaCounter == 3) {
            while (line.charAt(j) != ',') {
              target = target + line.charAt(j);
              j++;
            }
            intTarget = Integer.parseInt(target);
            row.setFourth(intTarget);
          }
          // fifth
          if (commaCounter == 4) {
            while (line.charAt(j) != ',') {
              target = target + line.charAt(j);
              j++;
            }
            floatTarget = Float.parseFloat(target);
            row.setFifth(floatTarget);
          }
          // date
          if (commaCounter == 5) {
            while (j != line.length()) {
              target = target + line.charAt(j);
              j++;

            }
            row.setDate(target);

          }
          j++;
          commaCounter++;
        }// for loop
        
        //write to output file
        out.writeObject(row);
      }

    }
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Objectload File Loaded. \n");
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return;
  }


  // Fetch methods
  /**
   * Uses a file reader to do a character by character query
   * @param columnNumber column of data to search
   * @param value the value of the target to search
   * @return all rows matching the query
   * @throws IOException
   */
  public ArrayList<String> naiveSearchEq(int columnNumber, String value) throws IOException {
    
    long startTime = System.nanoTime();
    
    System.out.println("naiveSearchEq Searching column #" + columnNumber + " for a value == " + value);

    ArrayList<String> rows = new ArrayList<String>();
    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder build = new StringBuilder();
    String line;
    String target = null;
    char currentChar = 0;
    int rowCounter = 0, commaCounter = 0;
    int c, count = 0;

    // get count
    while ((c = fr2.read()) != -1) {
      do {
        try {
          currentChar = (char) fr2.read();
          stringBuilder.append(currentChar);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        line = stringBuilder.toString();
      }
      while (currentChar != '\n');
      count++;
    }
    fr2.close();

    // print out line count
    // System.out.println(count);

    // position string by indexing char by char until commas are reached
    while (rowCounter < count) {

      commaCounter = 0;
      rowCounter++;

      // reset stringbuilders, refresh lines
      stringBuilder.setLength(0);
      build.setLength(0);

      // Build line
      do {
        try {
          currentChar = (char) fr.read();
          stringBuilder.append(currentChar);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        line = stringBuilder.toString();
        // rowCounter++;
      }
      while (currentChar != '\n');

      // Line built, position pointer to target column
      for (int i = 0; i <= line.length(); i++) {
        int j = i;

        // if char is a comma
        if (line.charAt(i) == ',') {
          commaCounter++;
        }

        // if target column
        if (commaCounter == columnNumber - 1) {
          do {
            if (line.charAt(j) == ',') {
              j++;
            }

            // build target string
            else {
              build.append(line.charAt(j));
              target = build.toString();
              j++;
            }
            if (line.charAt(j) == '\n') {
              break;
            }
          }
          while (line.charAt(j) != ',');
          // increment comma
          commaCounter++;
        }

        // if target string built exit for loop
        if (commaCounter == columnNumber) {
          break;
        }
      }

      if (target.equals(value)) {

        // target is in line; add line to rows arraylist
        rows.add(line);

      }

    }

    if (fr != null) {
      fr.close();
    }
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return rows;
  }


  /**
   * Uses a file reader to perform a character by character search and returns all rows that are greater than the query
   * @param columnNumber the column to search
   * @param value the value of the target to search
   * @return all rows that match the query
   * @throws IOException
   */
  public ArrayList<String> naiveSearchGtr(int columnNumber, String value) throws IOException {
    
    long startTime = System.nanoTime();

    ArrayList<String> rows = new ArrayList<String>();
    System.out.println("naiveSearchGtr Searching column #" + columnNumber + " for a value >= " + value);

    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder build = new StringBuilder();
    String line;
    char currentChar = 0;
    int rowCounter = 0, commaCounter = 0;
    int c, x = 0;
    String target = null;

    // get count
    while ((c = input2.read()) != -1) {
      do {
        try {
          currentChar = (char) input2.read();
          stringBuilder.append(currentChar);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        line = stringBuilder.toString();
        // rowCounter++;
      }
      while (currentChar != '\n');
      x++;
    }

    // print out line count
    // System.out.println(x);

    //
    while (rowCounter < x) {

      commaCounter = 0;
      rowCounter++;

      // reset stringbuilders
      stringBuilder.setLength(0);
      build.setLength(0);

      // Build line
      do {
        try {
          currentChar = (char) input.read();
          stringBuilder.append(currentChar);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        line = stringBuilder.toString();
        // rowCounter++;
      }
      while (currentChar != '\n');
      // print line
      // System.out.println("Next line.");
      // System.out.println(line + "\n");

      for (int i = 0; i <= line.length(); i++) {
        int j = i;

        // if char is a comma
        if (line.charAt(i) == ',') {
          commaCounter++;
        }

        // if target column
        if (commaCounter == columnNumber - 1) {
          do {
            if (line.charAt(j) == ',') {
              j++;
            }

            // build target string
            else {
              build.append(line.charAt(j));
              target = build.toString();
              j++;
            }
            if (line.charAt(j) == '\n') {
              break;
            }
          }
          while (line.charAt(j) != ',');
          commaCounter++;
        }

        // if target string built exit for loop
        if (commaCounter == columnNumber) {
          break;
        }

      }

      if (Float.parseFloat(target) >= Float.parseFloat(value)) {
        // System.out.println(target + "\n\n");
        // target is in line; add line to rows arraylist
        rows.add(line);

      }

    }
    input2.close();
    input.close();
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return rows;
  }


  /**
   * returns the rows of the table where the value in column number columnNumber is equal to the given value
   * @param columnNumber the column to search
   * @param value the value of the query
   * @return rows matching query
   */
  public ArrayList<String> bufferSearchEq(int columnNumber, String value) {
    
    long startTime = System.nanoTime();

    System.out.println("bufferSearchEq Searching column #" + columnNumber + " for a value == " + value);
    System.out.println("");
    ArrayList<String> rows = new ArrayList<String>();
    String line = null;
    int commaCounter = 0;
    char currentChar = 0;
    String target = "";

    try {
      while ((line = br.readLine()) != null) {
        commaCounter = 0;
        target = "";
        // check line for
        for (int i = 0; i <= line.length(); i++) {

          int j = i;

          // if char is a comma
          if (line.charAt(i) == ',') {
            commaCounter++;
          }

          // if target column
          if (commaCounter == columnNumber - 1) {
            do {

              if (line.charAt(j) == ',') {
                j++;
              }
              // build target string
              else {
                target = target + line.charAt(j);
                j++;
              }
              // exit if end of line
              if (j == line.length()) {
                break;
              }
            }
            while (line.charAt(j) != ',');
            commaCounter++;
          }

          // if target string built exit for loop
          if (commaCounter == columnNumber) {
            break;
          }

        }// for loop

        if (target.equals(value)) {
          rows.add(line);
        }
      }
      br.close();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return rows;
  }


  /**
   * returns the rows of the table where the value in column number columnNumber is greater than the given value.
   * @param columnNumber the column to search
   * @param value the value of the query
   * @return all rows that have values that are greater than the query
   */
  public ArrayList<String> bufferSearchGtr(int columnNumber, String value) {
    
    long startTime = System.nanoTime();

    ArrayList<String> rows = new ArrayList<String>();
    String line = null;
    int commaCounter = 0;
    char currentChar = 0;
    String target = "";

    System.out.println("bufferSearchGtr Searching column #" + columnNumber + " for a value >= " + value);
    System.out.println("");

    try {

      // reset br to beginning

      while ((line = br3.readLine()) != null) {
        commaCounter = 0;
        target = "";
        // check line for
        for (int i = 0; i <= line.length(); i++) {
          int j = i;

          // if char is a comma
          if (line.charAt(i) == ',') {
            commaCounter++;
          }

          // if target column
          if (commaCounter == columnNumber - 1) {
            do {
              if (line.charAt(j) == ',') {
                j++;
              }

              // build target string
              else {
                target = target + line.charAt(j);
                j++;
              }
              // exit if last column
              if (j == line.length()) {
                break;
              }
            }
            while (line.charAt(j) != ',');
            commaCounter++;
          }

          // if target string built exit for loop
          if (commaCounter == columnNumber) {
            break;
          }

        }// for loop

        if (Float.parseFloat(target) >= Float.parseFloat(value)) {
          rows.add(line);
        }
      }
      br3.close();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return rows;
  }


  /**
   * returns the rows of the table where the value in column number columnNumber is equal to the given value. Column numbers start from one.
   * @param columnNumber
   * @param value 
   * @return all rows that contain a value equal to the query
   * @throws IOException
   */
  public ArrayList<String> objectSearchEq(int columnNumber, String value) throws IOException {
    
    long startTime = System.nanoTime();

    System.out.println("\n"+ "objectSearchEq Searching column #" + columnNumber + " for a value == " + value +"\n");
    ArrayList<String> rows = new ArrayList<String>();
    int commaCounter;
    String target;

    try {
      // new input stream created
      FileInputStream fin = new FileInputStream("OutputTest.txt");
      in = new ObjectInputStream(fin);

      while (fin.available() > 0) {
        Line input = (Line) in.readObject();
        // System.out.println(input);
        commaCounter = 0;
        target = "";
        // check line for
        for (int i = 0; i <= input.toString().length(); i++) {
          int j = i;

          // if char is a comma
          if (input.toString().charAt(i) == ',') {
            commaCounter++;
          }

          // if target column
          if (commaCounter == columnNumber - 1) {
            do {
              if (input.toString().charAt(j) == ',') {
                j++;
              }

              // build target string
              else {
                target = target + input.toString().charAt(j);
                j++;
              }
              // exit if last column
              if (j == input.toString().length()) {
                break;
              }
            }
            while (input.toString().charAt(j) != ',');
            commaCounter++;
          }

          // if target string built exit for loop
          if (commaCounter == columnNumber) {
            break;
          }

        }// for loop

        if (target.equals(value)) {
          rows.add(input.toString());
        }

      }

    }
    catch (Exception e) {

      // if any I/O error occurs
      e.printStackTrace();
    }
    finally {

      // releases system resources associated with this stream
      if (in != null)
        in.close();
    }
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return rows;
  }


  /**
   * returns the rows of the table where the value in column number columnNumber is greater than the given value.
   * @param columnNumber
   * @param value
   * @return 
   * @throws IOException
   */
  public ArrayList<String> objectSearchGtr(int columnNumber, String value) throws IOException {
    
    long startTime = System.nanoTime();

    ArrayList<String> rows = new ArrayList<String>();
    int commaCounter= 0;
    String target;
    
    System.out.println("\n" + "objectSearchGtr Searching column #" + columnNumber + " for a value >= " + value + "\n");


    try {
      
      // new input stream created
      FileInputStream fin2 = new FileInputStream("OutputTest.txt");
      in2 = new ObjectInputStream(fin2);

      while (fin2.available() > 0) {
        
        Line input = (Line) in2.readObject();
        commaCounter = 0;
        target = "";
        
        // check line for
        for (int i = 0; i <= input.toString().length(); i++) {
          int j = i;

          // if char is a comma
          if (input.toString().charAt(i) == ',') {
            commaCounter++;
          }

          // if target column
          if (commaCounter == columnNumber - 1) {
            do {
              if (input.toString().charAt(j) == ',') {
                j++;
              }

              // build target string
              else {
                target = target + input.toString().charAt(j);
                j++;
               // System.out.println(target);

              }
              // exit if last column
              if (j == input.toString().length()) {
                break;
              }
            }
            while (input.toString().charAt(j) != ',');
            commaCounter++;
          }

          // if target string built exit for loop
          if (commaCounter == columnNumber) {
            break;
          }
        }// for loop

        if (Float.parseFloat(target) >= Float.parseFloat(value)) {
          rows.add(input.toString());
        }

      }

    }
    catch (Exception e) {

      // if any I/O error occurs
      e.printStackTrace();
    }
    finally {

      // releases system resources associated with this stream
      if (in2 != null)
        in2.close();
    }
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Time Elapsed: " + elapsedTime + " nanoseconds.");
    return rows;

  }
}