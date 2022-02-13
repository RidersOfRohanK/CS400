// --== CS400 File Header Information ==--
// Name: <Aayush Agarwal>
// Email: <agarwal65@wisc.edu>
// Team: <Red>
// Group: <ED>
// TA: <Yelun>
// Lecturer: <Gary Dahl>
// Notes to Grader: <n/a>
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface{
  
  @Override 
  /**
   * Reads the data in the file
   * @param a file that contains information about the movie
   * @return a List of MovieInterface with the information about the movie
   * @throws IOException, DataFormatException Exceptions for if the input is formatted incorrectly or invalid
   */
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws  IOException, DataFormatException {
    //creates an array of MovieInterface objects
    List<MovieInterface> values = new ArrayList<MovieInterface>();
    //creates mew scanner
    Scanner scanner = new Scanner(inputFileReader);
    
    //checks if file is valid
    if(!scanner.hasNextLine()) {
      scanner.close();
      throw new IOException();
    }
    //skips the first line that contains headers
    scanner.nextLine();
    
    //while loop to run through all the movies
    while(scanner.hasNextLine()) {
      //new Scanner with info for that line
      Scanner readLine = new Scanner(scanner.nextLine());
      //splits the line based off of commas
      readLine.useDelimiter(",");
      //gets the title of the movie
      String title = multipleItems(readLine);
      //System.out.println(title);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
        //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      
      //skips the original title
      skip(readLine);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
        //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      //stores the year 
      int year = readLine.nextInt();
      //System.out.println(year);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      
      //creates array for genre
      ArrayList<String> genre = new ArrayList<String>();
      //String genreTypes = multipleItems(readLine);
      String numGenre = readLine.next();
      //checks if string starts with \
      if(numGenre.substring(0,1).equals("\"")) {
        //removes \
        numGenre = numGenre.substring(1);
        //keeps adding the genres separated by commas
        while(!numGenre.endsWith("\"")) {
          genre.add(numGenre);
          //checks if there is another column
          try {
            hasNextError(readLine);
          //closes scanner and throws exception if not
          } catch(DataFormatException e) {
            scanner.close();
            throw new DataFormatException();
          }
          //gets next genre
          numGenre = readLine.next();
        }
        //gets the last genre before the last \
        numGenre = numGenre.substring(0,numGenre.length()-1);
      }
      genre.add(numGenre);
     // System.out.println(genre);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      
      //skips the duration
      readLine.next();
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      //skips the country
      skip(readLine);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      //skips the language
      readLine.next();
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      
      //gets the director
      String director = multipleItems(readLine);
      //System.out.println(director);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      
      //skips the writer
      skip(readLine);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      //skips the production_company
      readLine.next();
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      
      //skips the actors
      skip(readLine);
      
      //checks if there is another column
      try {
        hasNextError(readLine);
      //closes scanner and throws exception if not
      } catch(DataFormatException e) {
        scanner.close();
        throw new DataFormatException();
      }
      
      //gets the description
      String description = multipleItems(readLine);
      
      //System.out.println(description);
      
      //checks if there is another column
      if(!readLine.hasNextFloat()) {
        readLine.close();
        scanner.close();
      //closes scanner and throws exception if not
        throw new DataFormatException();
      }
      
      //gets average votes
      Float avgVote = readLine.nextFloat();
      //System.out.println(avgVote);
      
      //checks if there is another column
      if(readLine.hasNext()) {
        readLine.close();
        scanner.close();
      //closes scanner and throws exception if so
        throw new DataFormatException();
      }
      
      //creates new movie object
      Movie movieDetails = new Movie(title, year, genre, director, description, avgVote);
      //adds movie object to List of MovieInterface objects
      values.add(movieDetails);
      
    }
    //System.out.println(values.size());
    //closes scanner
    scanner.close();
    return values;
  }
  
  /**
   * Adds items that have multiple Strings separated by commas that are in one category
   * @param inputFileReader The file to be read
   * @return a String of everything separated by commas that fall in a certain column/category
   * @throws DataFormatException Throws exception if there aren't a right amount of columns
   */
  private String multipleItems(Scanner inputFileReader) throws DataFormatException {
    //new scanner
    Scanner scanner = inputFileReader;
    String name = scanner.next();
    //System.out.println(name.substring(0,1));
    //checks if column will have multiple entries
    if(name.startsWith("\"")) {
      //removes the \
      name = name.substring(1);
      if(!scanner.hasNext()) {
        scanner.close();
        throw new DataFormatException();
      }
      //gets the next string
      String next = scanner.next();
      //continues getting strings from that column
      while(!next.endsWith("\"")){
        name += "," + next;
        hasNextError(scanner);
        next = scanner.next();
      }
      //gets the last string in the column
      next = next.substring(0, next.length()-1);
      name += "," + next;
    }
    //return all the comma separated strings in that column
    return name;
  }
  
  /**
   * skips past columns that have multiple strings separated by commas
   * @param inputFileReader the file to be read
   * @throws DataFormatException Throws exception if not enough columns
   */
  private void skip(Scanner inputFileReader) throws DataFormatException {
    //creates new scanner
    Scanner scanner = inputFileReader;
    String name = scanner.next();
    //checks if the string starts with \
    if(name.startsWith("\"")){
      //keeps skipping past all the strings within this column
      while(!name.endsWith("\"")) {
        hasNextError(scanner);
        name = scanner.next();
      }
    }
  }
  
  /**
   * Checks if there is a next column
   * @param inputFileReader The file to be read
   * @throws DataFormatException Throws exception if there aren't enough columns
   */
  private void hasNextError(Scanner inputFileReader) throws DataFormatException{
    //creates new scanner
    Scanner scanner = inputFileReader;
    //checks if there is a next column
    if(!scanner.hasNext()) {
      scanner.close();
      //if not throw exception and close scanner
      throw new DataFormatException();
    }
  }

}

