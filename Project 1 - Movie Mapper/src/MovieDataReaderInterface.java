// --== CS400 File Header Information ==--
// Name: <Aayush Agarwal>
// Email: <agarwal65@wisc.edu>
// Team: <Red>
// Group: <ED>
// TA: <Yelun>
// Lecturer: <Gary Dahl>
// Notes to Grader: <n/a>
import java.util.List;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
 
public interface MovieDataReaderInterface {
 
        public List<MovieInterface> readDataSet(Reader inputFileReader) throws
FileNotFoundException, IOException, DataFormatException;
}
