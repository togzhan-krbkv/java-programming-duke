/**
 * TitleAndDepthComparator, orders QuakeEntry objects by title in
 * alphabetical order, breaking ties by depth from smallest to largest.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>
{
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        int titleCompare = q1.getInfo().compareTo(q2.getInfo());
        if (titleCompare != 0)
        {
            return titleCompare;
        }
        return Double.compare(q1.getDepth(), q2.getDepth());
    }
}
