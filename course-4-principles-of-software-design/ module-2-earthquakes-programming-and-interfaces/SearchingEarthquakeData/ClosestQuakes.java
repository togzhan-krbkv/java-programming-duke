/**
 * ClosestQuakes — finds the earthquakes closest to a given location by
 * repeatedly selecting the nearest remaining entry (selection-style search).
 *
 * @author Togzhan K.
 */
import java.util.*;

public class ClosestQuakes
{
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany)
    {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);

        for (int i = 0; i < howMany; i++)
        {
            double minDistance = Double.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < copy.size(); j++)
            {
                double currDistance = copy.get(j).getLocation().distanceTo(current);
                if (currDistance < minDistance)
                {
                    minDistance = currDistance;
                    minIndex = j;
                }
            }

            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }

        return ret;
    }

    public void findClosestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());

        Location jakarta = new Location(-6.211, 106.845);
        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 3);

        for (int k = 0; k < close.size(); k++)
        {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters / 1000, entry);
        }
        System.out.println("number found: " + close.size());
    }

    public static void main(String[] args)
    {
        ClosestQuakes cq = new ClosestQuakes();
        cq.findClosestQuakes();
    }
}
