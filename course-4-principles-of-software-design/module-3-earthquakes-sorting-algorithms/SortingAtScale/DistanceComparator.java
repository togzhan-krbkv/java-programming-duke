/**
 * DistanceComparator, orders QuakeEntry objects by their distance from a
 * given Location, from closest to farthest.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry>
{
    Location fromWhere;

    public DistanceComparator(Location where)
    {
        fromWhere = where;
    }

    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2.getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }
}
