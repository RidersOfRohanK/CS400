// --== CS400 File Header Information ==--
// Name: <Rohan Kale>
// Email: <rkale2@wisc.edu>
// Team: <ED red>
// Role: <Backend>
// TA: <Yelun BAO>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class Backend implements BackendInterface {
  private List<DataPoint> dataFromCSV;
  private RedBlackTree<DataPoint> dataByDateFormed;



  public Backend(Reader r) {
    DataReader reader = new DataReader();
    try {
      dataFromCSV = reader.readDataSet(r);
    }catch(Exception e){
      System.out.println("The reader passed in doesn't work");
    }
    initialize();
  }

  /**
   *  Gets the hurricanes in a date range and returns the name and the deaths due to the hurricane
   * @param startDate of range
   * @param endDate of range
   * @return formatted String
   */
  public String getDeathsOverTime(int startDate, int endDate) {
    RedBlackTree<DataPoint> dataByDeaths = getDataOverTimePeriod(startDate,endDate);
    Iterator<DataPoint> treeNodeIterator = dataByDeaths.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    while (treeNodeIterator.hasNext()) {
      DataPoint data = treeNodeIterator.next();
      sb.append("(");
      sb.append(data.name);
      sb.append(",");
      sb.append(data.deaths);
      sb.append("), ");
    }
    if(sb.length()>2)
      sb.deleteCharAt(sb.length()-1); //removes the whitespace at the end
    sb.append("]");


    return sb.toString();

  }

  /**
   *  Gets the hurricanes in a date range and returns the name and the MPH of the hurricane
   * @param startDate of range
   * @param endDate of range
   * @return formatted String
   */
  public String getMPHOverTime(int startDate, int endDate) {
    RedBlackTree<DataPoint> databyMPH = getDataOverTimePeriod(startDate,endDate);
    Iterator<DataPoint> treeNodeIterator = databyMPH.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    while (treeNodeIterator.hasNext()) {
      DataPoint data = treeNodeIterator.next();
      sb.append("(");
      sb.append(data.name);
      sb.append(",");
      sb.append(data.mph);
      sb.append("), ");

    }
    if(sb.length()>2)
      sb.deleteCharAt(sb.length()-1);//removes the whitespace at the end
    sb.append("]");

    return sb.toString();

  }


  private void initialize() {
    dataByDateFormed = new RedBlackTree<>();
    for (int i = 0; i < dataFromCSV.size(); i++) {
       dataByDateFormed.insert(dataFromCSV.get(i));
    }
  }

  /**
   *  Returns the names of all the hurricanes
   * @return a formatted String that displays the name of all the hurricanes
   */
  public String getAllHurricanes(){
    Iterator<DataPoint> treeNodeIterator = dataByDateFormed.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[");

    while (treeNodeIterator.hasNext()) {
      DataPoint data = treeNodeIterator.next();
      sb.append(data.name);
      sb.append(", ");
    }
    if(sb.length()>2)
      sb.deleteCharAt(sb.length()-1);//removes the whitespace at the end
    sb.append("]");
    return sb.toString();
  }

  /**
   * returns the names of all hurricanes in range
   * @param startDate date to start at
   * @param endDate date to end at
   * @return formatted String with all the names of the hurricanes in the date range specified
   */
  public String getAllHurricanesInRange(int startDate, int endDate){
    RedBlackTree<DataPoint> allHurricanes = getDataOverTimePeriod(startDate,endDate);
    Iterator<DataPoint> treeNodeIterator = allHurricanes.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[");

    while (treeNodeIterator.hasNext()) {
      DataPoint data = treeNodeIterator.next();

      sb.append(data.name);
      sb.append(", ");
    }
    if(sb.length()>2)
      sb.deleteCharAt(sb.length()-1); //removes the whitespace at the end

    sb.append("]");
    return sb.toString();
  }

  /**
   *  Takes the date range and converts the string to format YYYYMMDD in int format to make it easier
   *  to check if its in range or not, after that it adds it to the returned Red Black Tree
   * @param startDate of range
   * @param endDate of range
   * @return the red black tree produced as a result of the date range
   */
  private RedBlackTree getDataOverTimePeriod(int startDate, int endDate){
    RedBlackTree<DataPoint> dataInDateRange = new RedBlackTree<>();
    for (int i = 0; i < dataFromCSV.size(); i++) {
      Date formed = dataFromCSV.get(i).formed;
       int weightedDate = formed.year*10000; //Converting the date to an int so we can compare it
       weightedDate += formed.month * 100; //Converting the date to an int so we can compare it
       weightedDate+=formed.day; //Converting the date to an int so we can compare it
       if(weightedDate>=startDate&&weightedDate<=endDate){
         dataInDateRange.insert(dataFromCSV.get(i));
       }
    }
      return dataInDateRange;
  }

  //For Testing
  public RedBlackTree<DataPoint> getDataByDateFormed() {
    return dataByDateFormed;
  }
}
