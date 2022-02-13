// --== CS400 File Header Information ==--
// Name: <Rohan Kale>
// Email: <rkale2@wisc.edu>
// Team: <ED Red>
// Role: <Data Wrangler>
// TA: <Yelun BAO>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader implements DataReaderInterface{

  /**
   *
   * @param filePathForNames takes the file path for the names of the cities
   * @param filePathForWeights takes the file path for the distances between the cities
   * @return A list of all the different Nodes
   * @throws FileNotFoundException if the file cannot be found
   */
  public List<Node> readData(String filePathForNames, String filePathForWeights) throws FileNotFoundException {
    ArrayList<Node> allData = new ArrayList<>();
    Reader fileForNames = new FileReader(filePathForNames);
    Reader fileForWeights = new FileReader(filePathForWeights);

    Reader fileForNames2 = new FileReader(filePathForNames); //reader 2 is because the reader cant be reset on the
    // other's computers so this was the best way to do it


    Scanner names = new Scanner(fileForNames); //creates a new scanner
    Scanner weights = new Scanner(fileForWeights);

    ArrayList<String> nodeNames = new ArrayList<>(); //the nodes to be returned

    while (names.hasNextLine()) { //initializes the array of names
      String nextLine = names.nextLine();
      if (!nextLine.contains("#")) { //skips the beginning lines denoted with #
        nodeNames.add(nextLine);
      }
    }


    names = new Scanner(fileForNames2); //rewinds names to the beginning


    while (names.hasNextLine() && weights.hasNextLine()) { // should be the same as new Names
      String nextLineWeights = weights.nextLine();
      String nextLineNames = names.nextLine();
      if (!nextLineWeights.contains("#") && !nextLineNames.contains("#")) {//skips the lines with # because
        // they should both be equal and contain useless info


        Node newNode = new Node(nextLineNames);
        for (int i = 0; i < nodeNames.size(); i++) {
          newNode.addTarget(nodeNames.get(i)); //for each node fill the names of all the cities
        }

        String[] weightsFromFile = nextLineWeights.split("\\s+");//splits the numbers by the spaces
        for (int i = 0; i < weightsFromFile.length; i++) {
          String weight = weightsFromFile[i]; //weight from the line is seperated into the array by cities
          if (weight.length() > 0)
            newNode.addDistance(Integer.parseInt(weight)); //if there is no weight to be found don't add anything
        }

        allData.add(newNode); //add the new node
      }
    }

    return allData;
  }
}
