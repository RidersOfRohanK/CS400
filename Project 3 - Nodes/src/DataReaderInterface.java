// --== CS400 File Header Information ==--
// Name: <Rohan Kale>
// Email: <rkale2@wisc.edu>
// Team: <ED Red>
// Role: <Data Wrangler>
// TA: <Yelun BAO>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Takes string input and returns a list of all the Nodes, throws a file not found execption if not found
 */
public interface DataReaderInterface {
  public List<Node> readData(String filePathForNames, String filePathForWeights) throws FileNotFoundException;
}
