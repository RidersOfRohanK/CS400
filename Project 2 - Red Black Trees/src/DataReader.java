import java.util.List;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.ArrayList;

public class DataReader implements DataReaderInterface {
    public Date toDate(String date) {
        String[] tokens = date.split("/");
        Date d = new Date();
        d.year = Integer.parseInt(tokens[2]);
        d.month = Integer.parseInt(tokens[0]);
        d.day = Integer.parseInt(tokens[1]);
        return d;
    }

    public List<DataPoint> readDataSet(Reader inputFileReader) throws 
        FileNotFoundException, IOException, DataFormatException {
        Scanner s = new Scanner(inputFileReader);
        s.nextLine();
        ArrayList<DataPoint> result = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] tokens = s.nextLine().split(",");
            DataPoint p = new DataPoint();
            p.name = tokens[0];
            p.formed = toDate(tokens[1]);
            p.dissipated = toDate(tokens[2]);
            p.mph = Integer.parseInt(tokens[3]);
            p.damage = tokens[4];
            p.deaths = Integer.parseInt(tokens[5]);
            result.add(p);
        }
        return result;
    }
}