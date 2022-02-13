import java.util.List;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public interface DataReaderInterface {
        public List<DataPoint> readDataSet(Reader inputFileReader) throws 
FileNotFoundException, IOException, DataFormatException;
}