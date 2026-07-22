/**
 * LogAnalyzer reads a web server log file and answers questions about
 * visit counts: how many times each IP visited, which IPs visited the
 * most, which days had the most unique visitors, and which IPs visited
 * most often on a particular day.
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

    // Returns the day portion ("MMM dd", e.g. "Sep 30") of a LogEntry's access time
    private String dayOf(LogEntry le)
    {
        return le.getAccessTime().toString().substring(4, 10);
    }

    // Returns the unique IP addresses that accessed the site on someday
    // (a day string in the format "MMM dd", e.g. "Sep 30")
    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
        ArrayList<String> myIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            String ipAddr = le.getIpAddress();
            if (dayOf(le).equals(someday) && !myIPs.contains(ipAddr))
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

    // Maps each IP address to the total number of times it appears in the log
    public HashMap<String, Integer> countVisitsPerIP()
    {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records)
        {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip))
            {
                counts.put(ip, 1);
            }
            else
            {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> myCounts)
    {
        int max = 0;
        for (String s : myCounts.keySet())
        {
            int currentNum = myCounts.get(s);
            if (currentNum > max)
            {
                max = currentNum;
            }
        }
        return max;
    }

    // Returns all IP addresses tied for the maximum value in addressNumberTime
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> addressNumberTime)
    {
        ArrayList<String> maxIps = new ArrayList<String>();
        int greatest = mostNumberVisitsByIP(addressNumberTime);
        for (String s : addressNumberTime.keySet())
        {
            if (addressNumberTime.get(s) == greatest)
            {
                maxIps.add(s);
            }
        }
        return maxIps;
    }

    // Maps each day ("MMM dd") to the ArrayList of unique IPs that visited that day
    public HashMap<String, ArrayList<String>> iPsForDays()
    {
        HashMap<String, ArrayList<String>> dayToIPs = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records)
        {
            String day = dayOf(le);
            if (!dayToIPs.containsKey(day))
            {
                dayToIPs.put(day, uniqueIPVisitsOnDay(day));
            }
        }
        return dayToIPs;
    }

    // Returns the day with the most unique IP visits
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIPs)
    {
        String maxDay = null;
        int maxCount = -1;
        for (String day : dayIPs.keySet())
        {
            int count = dayIPs.get(day).size();
            if (count > maxCount)
            {
                maxCount = count;
                maxDay = day;
            }
        }
        System.out.println("Day with most IP visits: " + maxDay + " (" + maxCount + " unique IPs)");
        return maxDay;
    }

    // Returns the IP addresses that visited the most times on the given day
    // (counting repeat visits by the same IP on that day, not just uniqueness)
    public ArrayList<String> iPsWithMostVisitsOnDay(String aDay)
    {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records)
        {
            if (dayOf(le).equals(aDay))
            {
                String ip = le.getIpAddress();
                if (!counts.containsKey(ip))
                {
                    counts.put(ip, 1);
                }
                else
                {
                    counts.put(ip, counts.get(ip) + 1);
                }
            }
        }
        return iPsMostVisits(counts);
    }

    public void printAll()
    {
        for (LogEntry le : records)
        {
            System.out.println(le);
        }
    }
}
