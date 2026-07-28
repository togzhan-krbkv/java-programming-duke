/**
 * LargestQuakes — finds the earthquakes with the largest magnitude by
 * repeatedly selecting the largest remaining entry (selection-style search).
 *
 * @author Togzhan K.
 */
import java.util.*;

public class LargestQuakes
{
    public void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size());

        ArrayList<QuakeEntry> highestMagnitude = getLargest(list, 5);
        System.out.println("Data with highest magnitude:");
        for (QuakeEntry qe : highestMagnitude)
        {
            System.out.println(qe);
        }
    }

    private int indexOfLargest(ArrayList<QuakeEntry> data)
    {
        double maxMagnitude = Double.NEGATIVE_INFINITY;
        int maxIndex = -1;

        for (int i = 0; i < data.size(); i++)
        {
            double currMagnitude = data.get(i).getMagnitude();
            if (currMagnitude > maxMagnitude)
            {
                maxMagnitude = currMagnitude;
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);

        for (int i = 0; i < howMany; i++)
        {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }

        return ret;
    }

    public static void main(String[] args)
    {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }
}
