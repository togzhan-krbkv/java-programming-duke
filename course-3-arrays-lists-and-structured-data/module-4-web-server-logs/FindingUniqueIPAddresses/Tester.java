/**
 * Tester — tests LogAnalyzer's unique-IP-address methods.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class Tester
{
    public void testLogEntry()
    {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        la.printAll();
    }

    public void testUniqueIPs()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs");
    }

    public void testPrintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        ArrayList<String> ips = la.uniqueIPVisitsOnDay("Mar 17");
        System.out.println("Unique IPs on Mar 17:");
        for (String ip : ips)
        {
            System.out.println(ip);
        }
        System.out.println("Count: " + ips.size());
    }

    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        int countInRange = la.countUniqueIPsInRange(200, 299);
        System.out.println("There are " + countInRange + " unique IPs with status codes 200-299");
    }

    public static void main(String[] args)
    {
        Tester t = new Tester();
        t.testLogAnalyzer();
        t.testUniqueIPs();
        t.testPrintAllHigherThanNum();
        t.testUniqueIPVisitsOnDay();
        t.testCountUniqueIPsInRange();
    }
}
