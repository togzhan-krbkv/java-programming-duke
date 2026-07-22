/**
 * LogAnalyzer — reads a web server log file and answers questions about
 * unique IP addresses: how many unique visitors there are, which requests
 * had a status code above a threshold, which IPs visited on a given day,
 * and how many unique IPs fall within a status code range.
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

    public int countUniqueIPs()
    {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr))
            {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num)
    {
        for (LogEntry le : records)
        {
            int statusCode = le.getStatusCode();
            if (statusCode > num)
            {
                System.out.println("Status code greater than " + num + ": " + statusCode);
            }
        }
    }

    // Returns the unique IP addresses that accessed the site on someday
    // (a day string in the format "MMM dd", e.g. "Sep 30")
    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
        ArrayList<String> myIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            String dateString = le.getAccessTime().toString();
            String ipAddr = le.getIpAddress();
            if (dateString.contains(someday) && !myIPs.contains(ipAddr))
            {
                myIPs.add(ipAddr);
            }
        }
        return myIPs;
    }

    public int countUniqueIPsInRange(int low, int high)
    {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            int statusCode = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if (statusCode >= low && statusCode <= high)
            {
                if (!uniqueIPs.contains(ipAddr))
                {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }

    public void printAll()
    {
        for (LogEntry le : records)
        {
            System.out.println(le);
        }
    }
}
