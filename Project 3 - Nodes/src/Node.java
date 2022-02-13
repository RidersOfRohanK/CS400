import java.util.ArrayList;

// --== CS400 File Header Information ==--
// Name: <Rohan Kale>
// Email: <rkale2@wisc.edu>
// Team: <ED red>
// Role: <Frontend>
// TA: <Yelun BAO>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
public class Node {
  private String name;
  private ArrayList<String> targets;
  private ArrayList<Integer> distances;

  public Node(String name){
    this.name = name;
    targets = new ArrayList<>();
    distances = new ArrayList<>();
  }

  public ArrayList<Integer> getDistances() {
    return distances;
  }

  public ArrayList<String> getTargets() {
    return targets;
  }

  public void addTarget(String newTarget) {
    targets.add(newTarget);
  }
  public void addDistance(Integer newDistance) {
    distances.add(newDistance);
  }

  public String getName() {
    return name;
  }
}
