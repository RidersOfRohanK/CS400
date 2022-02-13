public interface BackendInterface {

  //  public DataPoint getData(String date);
  String getAllHurricanes();
  String getAllHurricanesInRange(int startDate, int endDate);
  public String getMPHOverTime(int startDate, int endDate);
  public String getDeathsOverTime(int startDate, int endDate);



}
