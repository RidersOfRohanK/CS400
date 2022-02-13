// --== CS400 File Header Information ==--
// Name: <Rohan Kale>
// Email: <rkale2@wisc.edu>
// Team: <ED Red>
// Role: <Data Wrangler>
// TA: <Yelun BAO>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.io.IOException;

public class DataReaderTester {
  DataReader reader;
  List<Node> data;

  //Initializes everything
  @BeforeEach
  void setup() throws IOException {
    reader = new DataReader();
    data = reader.readData("C:\\Users\\gamem\\IdeaProjects\\Project 3\\src\\uk12_name.txt",
            "C:\\Users\\gamem\\IdeaProjects\\Project 3\\src\\uk12_dist.txt");
  }

  //checks that all the data is loaded into the list correctly
  @Test
  void testLoadData() {
    assertEquals(12, data.size());
  }

  //Test that the names arraylist is initialized
  @Test
  void testReadName() {
    Node p = data.get(0);
    assertEquals("Aberystwyth", p.getName());
  }

  //check that that the distances are all in there correctly
  @Test
  void testReadEdge() {
    Node p = data.get(0);
    assertEquals("Brighton", p.getTargets().get(1));
    assertEquals(p.getDistances().get(1), 300);
  }
}