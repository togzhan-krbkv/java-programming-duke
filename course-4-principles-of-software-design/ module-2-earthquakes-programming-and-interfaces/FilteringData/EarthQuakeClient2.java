/**
 * EarthQuakeClient2 filters earthquake data using the Filter interface,
 * including combining multiple filters together with MatchAllFilter.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class EarthQuakeClient2
{
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData)
        {
            if (f.satisfies(qe))
            {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesWithFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        Location japan = new Location(35.42, 139.43);
        Filter f = new DistanceFilter(japan, 10000000, "Distance");
        ArrayList<QuakeEntry> result = filter(list, f);

        f = new PhraseFilter("end", "Japan", "Phrase");
        result = filter(result, f);

        System.out.println("Result of Distance Filter and Phrase Filter:");
        for (QuakeEntry qe : result)
        {
            System.out.println(qe);
        }
    }

    public void testMatchAllFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "a", "Phrase"));

        ArrayList<QuakeEntry> result = filter(list, maf);

        System.out.println("Match all filter result:");
        for (QuakeEntry qe : result)
        {
            System.out.println(qe);
        }
        System.out.println("Filters used are " + maf.getName());
    }

    public void testMatchAllFilter2()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0, "Magnitude"));
        maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "Ca", "Phrase"));

        ArrayList<QuakeEntry> result = filter(list, maf);

        System.out.println("Match all filter 2 result:");
        for (QuakeEntry qe : result)
        {
            System.out.println(qe);
        }
        System.out.println("Filters used are " + maf.getName());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list)
    {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list)
        {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

    public void createCSV()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public static void main(String[] args)
    {
        EarthQuakeClient2 client = new EarthQuakeClient2();
        client.quakesWithFilter();
        client.testMatchAllFilter();
        client.testMatchAllFilter2();
    }
}
