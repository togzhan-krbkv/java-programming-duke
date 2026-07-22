/**
 * CountTester — tests LogAnalyzer's website-visit-counting methods.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class CountTester
{
    public HashMap<String, Integer> testCounts()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        return counts;
    }

    public void testMostNumberVisitsByIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxValue = la.mostNumberVisitsByIP(counts);
        System.out.println("Max value in the HashMap: " + maxValue);
    }

    public void testIPsMostVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> maximumIPs = la.iPsMostVisits(counts);
        for (String ip : maximumIPs)
        {
            System.out.println("IP address with the maximum number of visits: " + ip);
        }
    }

    public void testIPsForDays()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        HashMap<String, ArrayList<String>> dayToIPs = la.iPsForDays();
        for (String day : dayToIPs.keySet())
        {
            System.out.println(day + " maps to\t" + dayToIPs.get(day));
        }
    }

    public void testDayWithMostIPVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        HashMap<String, ArrayList<String>> dayToIPs = la.iPsForDays();
        String dayMostIP = la.dayWithMostIPVisits(dayToIPs);
        System.out.println("The day with the most IP addresses: " + dayMostIP);
    }

    public void testIPsWithMostVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        ArrayList<String> mostAccessesDay = la.iPsWithMostVisitsOnDay("Sep 29");
        for (String ip : mostAccessesDay)
        {
            System.out.println("IP with the most visits on Sep 29: " + ip);
        }
    }

    public static void main(String[] args)
    {
        CountTester ct = new CountTester();
        ct.testCounts();
        ct.testMostNumberVisitsByIP();
        ct.testIPsMostVisits();
        ct.testIPsForDays();
        ct.testDayWithMostIPVisits();
        ct.testIPsWithMostVisitsOnDay();
    }
}
