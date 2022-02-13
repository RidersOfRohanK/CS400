// --== CS400 File Header Information ==--
// Name: <Rohan Kale>
// Email: <rkale2@wisc.edu>
// Team: <ED red>
// Role: <Backend>
// TA: <Yelun BAO>
// Lecturer: <Gary Dahl>
// Notes to Grader: <Had to change the tests due to the way the data wrangler structured his code>
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackendDeveloperTests {

  @Test
  //Tests that all the correct inputs are loaded
  public void TestInsertion() throws FileNotFoundException { //tests that insertion to the tree works right


    Reader read = new FileReader("src/hurricanes.csv");
    Backend backend = new Backend(read);

    assertTrue(backend.getDataByDateFormed().size != 0);


  }

  @Test
  //Tests the red black trees size function works correctly
  public void TestSize() throws FileNotFoundException { //tests the size method works properly

    Reader read = new FileReader("src/hurricanes.csv");
    Backend backend = new Backend(read);
    assertTrue(backend.getDataByDateFormed().size != 0);

    read = new FileReader("src/Tester.csv");
    Backend backend2 = new Backend(read);

    assertEquals(4, backend2.getDataByDateFormed().size);


  }

  @Test
  //Tests the getAll functionality and that it is in the correct format
  public void TestGetAll() throws FileNotFoundException{ //tests that you can get by name
  Reader read = new FileReader("src/Tester.csv");
  Backend backend = new Backend(read);
  assertEquals("[Frederic, Eloise, Camille, Baker,]",backend.getAllHurricanes());


  }

  @Test
  //Tests the getWithin range to check that the date checking is accurate
  public void TestGetWithinRange() throws FileNotFoundException{ //tests that you can go toString
    Reader read = new FileReader("src/Tester.csv");
    Backend backend = new Backend(read);

    assertEquals("[Camille, Baker,]",backend.getAllHurricanesInRange(19500001,19700001));

  }

  @Test
  //Tests the getDeaths function and makes sure that the correct amount of deaths are shown
  //Also shows that they are in the correct format
  public void TestgetDeaths() throws FileNotFoundException{ //tests that you can go toString
    Reader read = new FileReader("src/Tester.csv");
    Backend backend = new Backend(read);

    assertEquals("[(Camille,259), (Baker,38),]",backend.getDeathsOverTime(19500001,19700001));

  }

  @Test
  //Tests that the get MPH returns  the correct data fields and that its in the right format
  public void TestgetMPH() throws FileNotFoundException{ //tests that you can go toString
    Reader read = new FileReader("src/Tester.csv");
    Backend backend = new Backend(read);

    assertEquals("[(Frederic,130), (Eloise,125),]",backend.getMPHOverTime(19700001,19800001));

  }
}




