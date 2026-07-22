/**
 * LogAnalyzer — reads a web server log file into LogEntry records and
 * prints them.
 *
 * @author Togzhan K.
 */
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer()
    {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename)
    {
        FileResource resource = new FileResource(filename);
        for (String line : resource.lines())
        {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public void printAll()
    {
        for (LogEntry le : records)
        {
            System.out.println(le);
        }
    }
}
