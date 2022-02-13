// --== CS400 File Header Information ==--
// Name: <Rohan Kale>
// Email: <rkale2@wisc.edu>
// Team: <ED red>
// Role: <Frontend>
// TA: <Yelun BAO>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frontend {
  private String response;
  private int mode;
  private final int BASEMODE = 0;
  private final int GENREMODE = 1;
  private final int RATINGSMODE = 2;
  private Backend backend;
  Scanner scanner;


  public Frontend(Backend backend) { //initializes the backend
    mode = BASEMODE;
    this.backend = backend;
    init(); //sets everything up
    run(); //actually runs everything

  }

  private void run() {
    boolean running = true;
    response = scanner.nextLine();
    if (response.equals("x")) {
      mode = BASEMODE;
      System.out.println("do you want to quit? if yes hit x again");
      response = scanner.nextLine();
      if (response.equals("x"))
        running = false;
    } else if (response.equals("r")) {
      mode = RATINGSMODE;
    } else if (response.equals("g")) {
      mode = GENREMODE;
    } else {
      System.out.println("That is not a valid input");
    }//checks the initial inputs
    while (running) {//will be true always except when x and x are pressed
      if (mode == RATINGSMODE) {
        List<String> ratings = backend.getAllRatings(); //gets all ratings to
        System.out.println("Welcome to ratings mode. select or deslect a rating range by selecting the number");
        System.out.println("Selected genres will have a '-' next to them");
        System.out.println("ratings: ");
        for (int i = 0; i < 11; i++) {//initializes the visible ratings to be shown as selected
          ratings.set(i, "- " + i);
          System.out.println(ratings.get(i));
        }
        System.out.println("To return to base mode press x at any point");

        ratingsMode(ratings);
      } else if (mode == GENREMODE) {

        List<String> genres = backend.getAllGenres(); //gets all genres to display
        System.out.println("welcome to genre mode. select or deselect the genre using the number assigned to it");
        System.out.println("Selected genres will have a '-' next to them");
        System.out.println("genres: ");
        for (int i = 0; i < genres.size(); i++) {
          String genre = genres.get(i);
          System.out.println(i + " " + genre + " "); //numbers the genres to make selection easier
        }
        System.out.println("To return to base mode press x at any point");
        genreMode(genres);
      } else {//sets the mode
        System.out.println("enter an input");
        response = scanner.nextLine();
        if (response.equals("x")) {
          running = false;
        } else if (response.equals("r")) {
          mode = RATINGSMODE;
        } else if (response.equals("g")) {
          mode = GENREMODE;
        } else {
          System.out.println("That is not a valid input, please try again");//final case to avoid wrong inputs
        }
      }


    }
  }


  private void genreMode(List<String> genres) {//helper method to deal with genre mode
    boolean selecting = true;
    List<String> selectedVisible = genres; //the selections visible to the user
    while (selecting) {
      response = scanner.nextLine();
      for (int i = 0; i < selectedVisible.size(); i++) {//for all the genres shown
        if (response.equals(Integer.toString(i))) {
          String genre = selectedVisible.get(i);
          if (!genre.contains("-")) { //if the genre is not already selected
            backend.addGenre(genre); //adds to the backend object
            System.out.println(genre + " added to selected");
            genre = "-" + genre; //adds the selected thing
            selectedVisible.set(i, genre);
          } else { //if the genre was already selected
            genre = genre.substring(1); //removes the - showing that things were selected
            selectedVisible.set(i, genre);
            backend.removeGenre(genre); //removes the genre from backend
          }
        }
      }
      if (response.equals("x")) { //if the user wants to exit exit
        mode = BASEMODE;
        return;
      }
      if(response.equals("s")){ //if the user wants to scroll the movies
        if(backend.getNumberOfMovies()<=3){
          System.out.println("There are only 3 or less movies to see");
        }
        else{
          System.out.println("There are " + backend.getNumberOfMovies() + " movies"); //tells the user how many movies they have
          System.out.println("Enter a number to move to");
          response = scanner.nextLine();
          try {
            if (Integer.parseInt(response) > backend.getNumberOfMovies()) { //checks that the number isn't too high
              System.out.println("We don't have a movie numbered that high");
            } else {
              if(Integer.parseInt(response)<=backend.getNumberOfMovies()) { //second check that the number is reasonable
                List<MovieInterface> Movies = backend.getThreeMovies(Integer.parseInt(response));
                for (int i = 0; i < Movies.size(); i++) {
                  System.out.println(Movies.get(i).getTitle() + " - " + Movies.get(i).getAvgVote()); //returns the movie
                }
              }
            }
          }catch(Exception e){ //if the response is not actually a number
            System.out.println("That is not a number");
          }
        }
      }
      for (int i = 0; i < selectedVisible.size(); i++) {
        System.out.println(i + " " + selectedVisible.get(i)); //shows the user what the selection currently is
      }


      System.out.println("Are you done selecting? if so hit x");
    }
  }


  private void ratingsMode(List<String> ratings) {
    boolean selecting = true;
    while (selecting) {
      response = scanner.nextLine();
      for (int i = 0; i < ratings.size(); i++) { //for all the ratings possible
        if (response.equals(Integer.toString(i))){ //changed to be similar to the genre mode
          String rating = ratings.get(i);
          if (!rating.contains("-")) { //same as genre mode, if the - is there that means its selected, absence means that it is not selected
            backend.addAvgRating(rating); //add to backend
            System.out.println(rating + " added");
            rating = "-" + rating;
            ratings.set(i, rating);
          } else { //if it has already been selected
            rating = rating.substring(1);
            ratings.set(i, rating);
            System.out.println(rating + " removed");
            backend.removeAvgRating(rating); //remove from backend
          }
        }
      }

      System.out.println("Are you done selecting? if so hit x");
      if (response.equals("x")) { //if the user decides to quit
        mode = BASEMODE;
        return;
      }


      if(response.equals("s")){ //if scrolling is selected
        if(backend.getNumberOfMovies()<=3){
          System.out.println("There are only 3 or less movies to see");
        }
        else{
          System.out.println("There are " + backend.getNumberOfMovies() + " movies"); //literally the exact same code as above in genres
          System.out.println("Enter a number to move to");
          response = scanner.nextLine();
          try {
            if (Integer.parseInt(response) > backend.getNumberOfMovies()) {
              System.out.println("We don't have a movie numbered that high");
            } else {
              if(Integer.parseInt(response)+2<backend.getNumberOfMovies()) {
                List<MovieInterface> Movies = backend.getThreeMovies(Integer.parseInt(response));
                for (int i = 0; i < Movies.size(); i++) {
                  System.out.println(Movies.get(i).getTitle() + " - " + Movies.get(i).getAvgVote());
                }
              }
            }
          }catch(Exception e){
            System.out.println("That is not a number"); //catch if it is not a number
          }
        }
      }

      for (int i = 0; i < ratings.size(); i++) { //show the visible ratings
        System.out.println(ratings.get(i));
      }
    }
  }

  private void init() { //initalizes the front end
    System.out.println("Welcome to the Movie Mapper");
    scanner = new Scanner(System.in);
    System.out.println("You can input x to go back to base mode, r to go to ratings mode, or g for genres mode");
    System.out.println("Hit x twice to exit");
    System.out.println("When selecting hit s to scroll");//opening messages and says how to navigate the movie mapper
    List<MovieInterface> movieList;
    for (int i = 0; i < 11; i++) {
       backend.addAvgRating(Integer.toString(i)); //initially selects all the reatings
    }
    movieList = backend.getThreeMovies(0);
    for (int i = 0; i < movieList.size(); i++) {
      System.out.println(movieList.get(i).getTitle() + " - " + movieList.get(i).getAvgVote()); //shows the 3 highest rated movies
    }
  }


}
