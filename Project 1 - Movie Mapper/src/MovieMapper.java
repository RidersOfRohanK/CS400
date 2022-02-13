import java.io.File;
import java.io.FileReader;
import java.util.List;

public class MovieMapper {

  public static void main(String[] args) {
    try {
      MovieDataReader read = new MovieDataReader();
      File filepath = new File("src/movies.csv");
      List<MovieInterface> r = read.readDataSet(new FileReader(filepath));

      Frontend f = new Frontend(new Backend(r));
    } catch (Exception e) {
      System.out.println("This doesn't work");
      e.printStackTrace();
    }
  }
}