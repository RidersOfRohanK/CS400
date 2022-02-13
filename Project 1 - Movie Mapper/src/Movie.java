// --== CS400 File Header Information ==--
// Name: <Aayush Agarwal>
// Email: <agarwal65@wisc.edu>
// Team: <Red>
// Group: <ED>
// TA: <Yelun>
// Lecturer: <Gary Dahl>
// Notes to Grader: <n/a>
import java.util.List;

public class Movie implements MovieInterface{
  private String title;
  private int year;
  private List<String> genres;
  private String director;
  private String description;
  private float avgVote;
  /**
   * Initializes all the instance fields
   * @param title The title of the movie
   * @param year the year the movie was released
   * @param genres the different genres in the movie
   * @param director the director of the movies
   * @param description the movie description
   * @param avgVote The avgVotes of the movie
   */
  public Movie(String title, int year, List<String> genres, String director, String description, float avgVote) {
    this.title = title;
    this.year = year;
    this.genres = genres;
    this.director = director;
    this.description = description;
    this.avgVote = avgVote;
  }
  @Override
  /**
   * Returns the title of the movie
   * @return title of the movie
   */
  public String getTitle() {
    // returns the title
    return title;
  }

  @Override
  /**
   * Returns the year of the movie
   * @return year of the movie
   */
  public Integer getYear() {
    // returns the year
    return year;
  }

  @Override
  /**
   * Returns the list of genres
   * @return a List of the genres
   */
  public List<String> getGenres() {
    // return the genres
    return genres;
  }

  @Override
  /**
   * Returns the directors
   * @return The names of the directors
   */
  public String getDirector() {
    // return director
    return director;
  }

  @Override
  /**
   * Returns a description of the movie
   * @return a description of the movie
   */
  public String getDescription() {
    // returns description
    return description;
  }

  @Override
  /**
   * Returns the average votes for the movie
   * @return a float of the average votes
   */
  public Float getAvgVote() {
    // returns avgVote
    return avgVote;
  }

  @Override
  /**
   * Returns if the input is greater or less than or equal to another movie
   * @param the movie being compared to
   * @return an int of 1 if greater, -1 if less, and 0 if greater
   */
  public int compareTo(MovieInterface otherMovie) {
    // returns -1 if otherMovie avgVote is less
    if(otherMovie.getAvgVote() < getAvgVote()) {
      return -1;
    } 
    //return 1 if otherMovie avgVote is more
    if (getAvgVote() < otherMovie.getAvgVote()){
      return 1;
      //return 0 if equal
    } else {
      return 0;
    }
  }

  @Override
  /**
   * Returns a string of all the instance fields
   * @return a string with information about the movie
   */
  public String toString() {
    String s = "";
    s += getTitle() + ", " + getYear() + ", "; 
    //adds all elements of genres into string
    for(int i = 0; i < getGenres().size(); i++) {
      s += getGenres().get(i) + ", ";
    }
    //adds rest of elements to string
    s += getDirector() + ", " + getDescription() + ", " + getAvgVote();
    return s;
  }
}
