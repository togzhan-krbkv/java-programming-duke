/**
 * QuakeSortWithTwoArrayLists, a selection sort that builds a new sorted
 * list by repeatedly removing the smallest remaining element from the
 * input list and appending it to the output list. This is the approach
 * shown in the lesson video, using two ArrayLists instead of sorting in
 * place. Note that it empties the input list as a side effect.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class QuakeSortWithTwoArrayLists
{
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes)
    {
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q : quakes)
        {
            if (q.getMagnitude() < min.getMagnitude())
            {
                min = q;
            }
        }
        return min;
    }

    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in)
    {
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while (!in.isEmpty())
        {
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }
        return out;
    }

    public void testSort()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        list = sortByMagnitude(list);
        for (QuakeEntry qe : list)
        {
            System.out.println(qe);
        }
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

    public static void main(String[] args)
    {
        QuakeSortWithTwoArrayLists qs = new QuakeSortWithTwoArrayLists();
        qs.testSort();
    }
}
