public class DataPoint implements Comparable<DataPoint> {
    public String name;
    public Date formed;
    public Date dissipated;
    public int mph;
    public String damage;
    public int deaths;

    @Override
    public int compareTo(DataPoint o) {
        if (o.formed.year == formed.year) {
            if (o.formed.month == formed.month) {
                return o.formed.day - formed.day;
            } else {
                return o.formed.month - formed.month;
            }
        } else {
            return o.formed.year - formed.year;
        }
    }
}
